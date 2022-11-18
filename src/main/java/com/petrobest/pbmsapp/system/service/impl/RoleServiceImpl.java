package com.petrobest.pbmsapp.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.petrobest.pbmsapp.system.dao.RoleDao;
import com.petrobest.pbmsapp.system.domain.RoleDO;
import com.petrobest.pbmsapp.system.service.RoleResourceService;
import com.petrobest.pbmsapp.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleDao, RoleDO> implements RoleService {

    @Autowired
    RoleResourceService roleResourceService;


    @Override
    public List<RoleDO> listRolesByUsername(String username) {
        return this.baseMapper.listRolesByUsername(username);
    }

    @Override
    public List<RoleDO> listRolesByUserId(String userId) {
        return this.baseMapper.listRolesByUserId(userId);
    }

    @Override
    public List<String> listRolesRemarkByUsername(String username) {
        return this.baseMapper.listRolesRemarkByUsername(username);
    }


}
