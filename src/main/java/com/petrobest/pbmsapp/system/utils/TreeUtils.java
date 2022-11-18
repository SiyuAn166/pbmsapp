package com.petrobest.pbmsapp.system.utils;

import com.petrobest.pbmsapp.system.domain.ResourceDO;
import com.petrobest.pbmsapp.system.domain.TreeNode;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TreeUtils {


    public static List<TreeNode> buildTree(List<ResourceDO> resourceDOS) {
        List<TreeNode> treeNodeList = getTreeNodeList(resourceDOS);
        List<TreeNode> parent = new ArrayList<>();
        for (TreeNode node : treeNodeList) {
            if (node.getParentId().equals(ResourceDO.TOP_MENU)) {
                parent.add(node);
            }
        }
        //节点中不存在顶级节点 parentId=0，直接返回
        if (parent.size() == 0 && treeNodeList.size() > 0) {
            parent = treeNodeList;
            return parent;
        }

        List<TreeNode> childList = (List<TreeNode>) CollectionUtils.subtract(treeNodeList, parent);
        buildTreeNodeChildren(parent, childList);
        return parent;
    }


    private static void buildTreeNodeChildren(List<TreeNode> parent, List<TreeNode> allNodes) {
        for (TreeNode parentNode : parent) {
            List<TreeNode> children = new ArrayList<>();
            for (TreeNode node : allNodes) {
                if (parentNode.getId().equals(node.getParentId())) {
                    children.add(node);
                }
            }
            if (!ListUtils.isEmpty(children)) {
                Collections.sort(children);//按orderNum排序
                parentNode.setChildren(children);
                buildTreeNodeChildren(children, allNodes);
            }

        }
    }

    private static List<TreeNode> getTreeNodeList(List<ResourceDO> resourceDOS) {
        if (ListUtils.isEmpty(resourceDOS)) {
            return null;
        }
        List<TreeNode> treeNodeList = new ArrayList<>();
        for (ResourceDO re : resourceDOS) {
            TreeNode node = new TreeNode();
            node.setId(re.getResourceId());  //id
            node.setParentId(re.getParentId()); //pid
            node.setLabel(re.getResourceName());//label
            node.setPermission(re.getPerms());//perms
            node.setOrderNum(re.getOrderNum());//order
            treeNodeList.add(node);
        }
        return treeNodeList;
    }


    public static List<ResourceDO> buildResourceTree(List<ResourceDO> resourceDOS) {
        List<ResourceDO> parent = new ArrayList<>();

        for (ResourceDO r : resourceDOS) {
            if (r.getParentId().equals(ResourceDO.TOP_MENU)) {
                parent.add(r);
            }
        }

        if (parent.size() == 0) {
            parent = resourceDOS;
            return parent;
        }
        List<ResourceDO> childList = (List<ResourceDO>) CollectionUtils.subtract(resourceDOS, parent);
        buildResourceChildren(parent, childList);

        return parent;
    }

    private static void buildResourceChildren(List<ResourceDO> parent, List<ResourceDO> childList) {
        for (ResourceDO parentNode : parent) {
            List<ResourceDO> children = new ArrayList<>();
            for (ResourceDO childNode : childList) {
                if (childNode.getParentId().equals(parentNode.getResourceId())) {
                    children.add(childNode);
                }
            }

            if (!ListUtils.isEmpty(children)) {
                Collections.sort(childList);//按orderNum排序
                parentNode.setChildren(children);
                buildResourceChildren(children, childList);
            }
        }
    }
}
