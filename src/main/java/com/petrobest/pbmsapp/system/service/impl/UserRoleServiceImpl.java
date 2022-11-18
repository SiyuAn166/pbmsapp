package com.petrobest.pbmsapp.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.petrobest.pbmsapp.system.dao.UserRoleDao;
import com.petrobest.pbmsapp.system.domain.UserDO;
import com.petrobest.pbmsapp.system.domain.UserRoleDO;
import com.petrobest.pbmsapp.system.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRoleDO> implements UserRoleService {
    @Override
    public List<String> listRoleIds(String userId) {

        List<UserRoleDO> userRoleDOS = list(new QueryWrapper<UserRoleDO>().eq(UserDO.USER_ID, userId));
        List<String> roleIds = getRoleIds(userRoleDOS);

        return roleIds;
    }


    @Override
    public boolean saveUserRole(String userId, List<String> roleIds) {
        //新用户不修改角色
        if (roleIds == null || roleIds.size() <= 0) {
            return true;
        }
        List<UserRoleDO> userRoleDOS = new LinkedList<>();
        for (String roleId : roleIds) {
            UserRoleDO userRoleDO = new UserRoleDO();
            userRoleDO.setUserId(userId);
            userRoleDO.setRoleId(roleId);
            userRoleDOS.add(userRoleDO);
        }
        remove(new QueryWrapper<UserRoleDO>().eq(UserDO.USER_ID, userId));
        boolean saveOrUpdateBatch = saveBatch(userRoleDOS);
        return saveOrUpdateBatch;
    }

    private List<String> getRoleIds(List<UserRoleDO> userRoleDOS) {
        if (userRoleDOS != null && userRoleDOS.size() > 0) {
            List<String> roleIds = new ArrayList<>();
            for (UserRoleDO ur : userRoleDOS) {
                roleIds.add(ur.getRoleId());
            }
            return roleIds;
        }
        return null;
    }
}
