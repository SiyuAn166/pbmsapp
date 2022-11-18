package com.petrobest.pbmsapp.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.petrobest.pbmsapp.system.domain.UserDO;
import com.petrobest.pbmsapp.system.vo.UserPasswordVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService extends IService<UserDO> {

    boolean saveUser(UserDO user);

    IPage<Map<String, Object>> listUserWithRole(Page page, UserDO userDO);

    void modifyUserPassword(UserPasswordVO userPasswordVO) throws Exception;

    IPage<UserDO> listUserWithRoleDO(Page page, UserDO userDO);
}
