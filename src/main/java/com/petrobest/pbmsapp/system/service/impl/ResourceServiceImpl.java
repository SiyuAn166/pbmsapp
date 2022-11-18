package com.petrobest.pbmsapp.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.petrobest.pbmsapp.system.dao.ResourceDao;
import com.petrobest.pbmsapp.system.domain.ResourceDO;
import com.petrobest.pbmsapp.system.domain.TreeNode;
import com.petrobest.pbmsapp.system.service.ResourceService;
import com.petrobest.pbmsapp.system.utils.TreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.ListUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@Transactional
public class ResourceServiceImpl extends ServiceImpl<ResourceDao, ResourceDO> implements ResourceService {
    @Override
    public boolean saveResource(ResourceDO resourceDO) {
        resourceDO.setType(ResourceDO.TYPE_RESOURCE);
        boolean save = save(resourceDO);
        return save;
    }

    @Override
    public List<ResourceDO> listByUserId(String userId) {
        return this.baseMapper.listByUserId(userId);
    }

    @Override
    public List<TreeNode> getTree() {
        List<ResourceDO> list = list();
        List<TreeNode> tree = TreeUtils.buildTree(list);
        return tree;
    }

    @Override
    public List<TreeNode> getTreeByUserId(String userId) {
        List<ResourceDO> list = listByUserId(userId);
        List<TreeNode> tree = TreeUtils.buildTree(list);
        return tree;
    }

    @Override
    public List<TreeNode> getTreeByUsername(String username) {

        List<ResourceDO> list = this.baseMapper.listByUsername(username);
        List<TreeNode> tree = TreeUtils.buildTree(list);
        return tree;
    }

    @Override
    @Transactional
    public boolean batchRemoveChild(List<String> parentIds) {
        List<ResourceDO> list = list();
        Set<String> removingSet = new HashSet<>();
        findChild(parentIds, list, removingSet);
//        log.info(String.format("list: {},\n size: {}"), removingSet, removingSet.size());
        if (removingSet.size() > 0) {
            boolean res = removeByIds(removingSet);//删除所有子级元素
            return res;
        }

        return true;
    }

    @Override
    public List<String> listPermsByUsername(String username) {
        return this.baseMapper.listPermsByUsername(username);
    }

    /**
     * @param parentIds 父级ID
     * @param resources 全部查询结果
     * @param ids       要删除的ID
     */
    protected void findChild(List<String> parentIds, List<ResourceDO> resources, Set<String> ids) {
        List<String> childIds = new ArrayList<>();
        for (String parentId : parentIds) {
            for (ResourceDO r : resources) {
                if (parentId.equals(r.getParentId())) {
                    childIds.add(r.getResourceId());
                }
            }
        }
        //再查询所有子集
        if (childIds.size() > 0) {
            ids.addAll(childIds);
            findChild(childIds, resources, ids);
        }
    }

}
