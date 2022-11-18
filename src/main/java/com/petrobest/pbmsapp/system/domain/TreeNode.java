package com.petrobest.pbmsapp.system.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeNode implements Comparable<TreeNode> {

    private String id;
    private String parentId;
    private String label; //节点显示名称
    private String permission; //节点权限标识
    private Integer orderNum; //序号
    private List<TreeNode> children = new ArrayList<>();  //子节点

    @Override
    public int compareTo(TreeNode o) {
        return getOrderNum() - o.getOrderNum();
    }
}
