package com.petrobest.pbmsapp.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.petrobest.pbmsapp.common.utils.MD5Utils;
import com.petrobest.pbmsapp.system.dao.UserDao;
import com.petrobest.pbmsapp.system.domain.UserDO;
import com.petrobest.pbmsapp.system.exception.BadUserParametersException;
import com.petrobest.pbmsapp.system.exception.InconsistentPassword;
import com.petrobest.pbmsapp.system.exception.IncorrectOldPasswordException;
import com.petrobest.pbmsapp.system.exception.UserNotFoundException;
import com.petrobest.pbmsapp.system.service.UserRoleService;
import com.petrobest.pbmsapp.system.service.UserService;
import com.petrobest.pbmsapp.system.vo.UserPasswordVO;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserDao, UserDO> implements UserService {

    @Autowired
    UserRoleService userRoleService;

    @Override
    public boolean saveUser(UserDO user) {
        user.setStatus(UserDO.STATUS_VALID);
        user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
        boolean save = save(user);
        return save;
    }


    @Override
    public IPage<Map<String, Object>> listUserWithRole(Page page, UserDO userDO) {
        return this.baseMapper.listUserWithRole(page, userDO);
    }

    @Override
    public void modifyUserPassword(UserPasswordVO userPasswordVO) throws Exception {

        String username = userPasswordVO.getUsername();
        String oldPassword = userPasswordVO.getOldPassword();

        if (StringUtils.isAnyEmpty(username, oldPassword)) {   //用户基础数据参数错误
            throw new BadUserParametersException();
        }

        //username  oldPassword 都不为空
        UserDO currentUser = getOne(new QueryWrapper<UserDO>().eq(UserDO.USERNAME, userPasswordVO.getUsername()));
        if (currentUser == null) {
            throw new UserNotFoundException();   //用户未找到
        }

        //校验旧密码
        String encrypt_old = MD5Utils.encrypt(username, oldPassword);
        String password = currentUser.getPassword(); //当前账号密码
        if (!StringUtils.equals(encrypt_old, password)) {  //旧密码与当前密码不同
            throw new IncorrectOldPasswordException();    //旧密码不正确
        }

        //修改为新密码
        String newPassword = userPasswordVO.getNewPassword();
        String confirmPassword = userPasswordVO.getConfirmPassword();

        if (StringUtils.isAnyEmpty(newPassword, confirmPassword) || !StringUtils.equals(newPassword, confirmPassword)) {
            throw new InconsistentPassword();   //密码不一致
        }

        //都不为空，且相等
        String encrypt_new = MD5Utils.encrypt(username, newPassword);
        //构造修改参数
        UserDO userDO = new UserDO();
        userDO.setUsername(username);
        userDO.setPassword(encrypt_new);

        update(userDO, new UpdateWrapper<UserDO>().eq(UserDO.USERNAME, username));   //修改数据库记录

    }

    @Override
    public IPage<UserDO> listUserWithRoleDO(Page page, UserDO userDO) {
        return this.baseMapper.listUserWithRoleDO(page, userDO);
    }


}
