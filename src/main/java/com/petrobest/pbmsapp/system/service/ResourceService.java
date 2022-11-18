package com.petrobest.pbmsapp.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.petrobest.pbmsapp.system.domain.ResourceDO;
import com.petrobest.pbmsapp.system.domain.TreeNode;

import java.util.List;

public interface ResourceService extends IService<ResourceDO> {
    boolean saveResource(ResourceDO resourceDO);

    List<ResourceDO> listByUserId(String userId);

    List<TreeNode> getTree();

    List<TreeNode> getTreeByUserId(String userId);

    List<TreeNode> getTreeByUsername(String username);

    boolean batchRemoveChild(List<String> parentIds);

    List<String> listPermsByUsername(String username);
}
