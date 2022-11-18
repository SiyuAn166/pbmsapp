package com.petrobest.pbmsapp.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.petrobest.pbmsapp.system.dao.RoleResourceDao;
import com.petrobest.pbmsapp.system.domain.RoleDO;
import com.petrobest.pbmsapp.system.domain.RoleResourceDO;
import com.petrobest.pbmsapp.system.service.RoleResourceService;
import com.petrobest.pbmsapp.system.utils.ListUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@Transactional
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceDao, RoleResourceDO> implements RoleResourceService {
    @Override
    public List<String> listResourceIds(String roleId) {
        List<RoleResourceDO> roleResourceDOS = list(new QueryWrapper<RoleResourceDO>().eq(RoleDO.ROLE_ID, roleId));
        List<String> resourceIds = getResourceIds(roleResourceDOS);
        return resourceIds;
    }

    private List<String> getResourceIds(List<RoleResourceDO> roleResourceDOS) {
        if (!ListUtils.isEmpty(roleResourceDOS)) {
            List<String> resourceIds = new ArrayList<>();
            for (RoleResourceDO rr : roleResourceDOS) {
                resourceIds.add(rr.getResourceId());
            }
            return resourceIds;
        }
        return null;
    }

    @Override
    public boolean saveRoleResource(String roleId, List<String> resourceIds) {

        //先删除该角色关联的resource
        remove(new QueryWrapper<RoleResourceDO>().eq(RoleDO.ROLE_ID, roleId));
        //resourceIds为空 直接返回，不进行角色-资源映射
        if (resourceIds == null || resourceIds.size() <= 0) {
            return true;
        }
        List<RoleResourceDO> roleResourceDOs = new LinkedList<>();
        for (String resourceId : resourceIds) {
            RoleResourceDO roleResourceDO = new RoleResourceDO();
            roleResourceDO.setRoleId(roleId);
            roleResourceDO.setResourceId(resourceId);
            roleResourceDOs.add(roleResourceDO);
        }
        return saveBatch(roleResourceDOs);
    }
}
