package com.petrobest.pbmsapp.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.petrobest.pbmsapp.common.annotation.Log;
import com.petrobest.pbmsapp.common.domain.ResponseBo;
import com.petrobest.pbmsapp.system.domain.ResourceDO;
import com.petrobest.pbmsapp.system.domain.RoleResourceDO;
import com.petrobest.pbmsapp.system.domain.TreeNode;
import com.petrobest.pbmsapp.system.service.ResourceService;
import com.petrobest.pbmsapp.system.service.RoleResourceService;
import com.petrobest.pbmsapp.system.utils.TreeUtils;
import com.petrobest.pbmsapp.system.vo.ResourceVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/system/resource")
public class ResourceController extends BaseController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private RoleResourceService roleResourceService;

    /**
     * 新增
     */
    @Log("新增资源")
    @PostMapping("/save")
    public Object save(@RequestBody ResourceDO resource) {
        boolean save = resourceService.save(resource);
        if (save) {
            return ResponseBo.ok("保存成功");
        }
        return ResponseBo.error("保存失败");
    }

    /**
     * 修改
     */
    @Log("修改资源")
    @PostMapping("/update")
    public Object update(@RequestBody ResourceDO resource) {
        boolean update = resourceService.updateById(resource);
        if (update) {
            return ResponseBo.ok("修改成功");
        }
        return ResponseBo.error("修改失败");
    }

    /**
     * 删除
     */
    @Log("删除资源")
    @PostMapping("/delete")
    @Transactional
    public Object delete(@RequestBody ResourceDO resource) {
        boolean delete = resourceService.removeById(resource.getResourceId());
        boolean deleteChild = resourceService.batchRemoveChild(Arrays.asList(resource.getResourceId()));
        boolean remove = roleResourceService.remove(new QueryWrapper<RoleResourceDO>().eq(ResourceDO.RESOURCE_ID, resource.getResourceId()));

        if (delete && deleteChild && remove) {
            return ResponseBo.ok("删除成功");
        } else {
            return ResponseBo.error("删除失败");
        }
    }

    /**
     * 批量删除
     */
    @Log("批量删除资源")
    @PostMapping("/deleteBatch")
    @Transactional
    public Object deleteBatch(@RequestBody Map<String, List<String>> params) {
        Set<Map.Entry<String, List<String>>> entries = params.entrySet();
        Iterator<Map.Entry<String, List<String>>> it = entries.iterator();
        List<String> resourceIds = it.next().getValue();

        if (resourceIds == null || resourceIds.size() <= 0) {
            return ResponseBo.error("参数有误！");
        }
        boolean deleteBatch = resourceService.removeByIds(resourceIds);
        boolean deleteChild = resourceService.batchRemoveChild(resourceIds);
        boolean remove = roleResourceService.remove(new QueryWrapper<RoleResourceDO>().in(ResourceDO.RESOURCE_ID, resourceIds));
        if (deleteBatch && deleteChild && remove) {
            return ResponseBo.ok("删除成功");
        } else {
            return ResponseBo.error("删除失败");
        }
    }

    /**
     * 查询
     */
    @PostMapping("/find")
    public Object find(@RequestBody ResourceDO resource) {
        ResourceDO find = resourceService.getOne(new QueryWrapper<ResourceDO>().eq(ResourceDO.RESOURCE_ID, resource.getResourceId()));
        if (find != null) {
            return ResponseBo.ok(find);
        } else {
            return ResponseBo.error("没有找到该对象");
        }
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    public Object list(@RequestBody ResourceVO resourceVO) {
        log.info(String.format("Class: ResourceController, Method: list, resourceVO: {}"), resourceVO.toString());
        //分页构造器
        Page<ResourceDO> page = new Page<>(resourceVO.getCurrPage(), resourceVO.getPageSize());
        //条件构造器
        QueryWrapper<ResourceDO> queryWrapper = new QueryWrapper<>(resourceVO.getEntity());
        //执行分页
        IPage<ResourceDO> pageList = resourceService.page(page, queryWrapper);
        //返回结果
        return ResponseBo.ok(pageList);
    }

    /**
     * 全部查询
     */
    @PostMapping("/listAll")
    public Object listAll() {
        //执行分页
        List<ResourceDO> list = resourceService.list();
        List<ResourceDO> tree = TreeUtils.buildResourceTree(list);

        //返回结果
        return ResponseBo.ok(tree);
    }

    /**
     * 树形菜单(获取所有节点)
     */
    @PostMapping("/getTree")
    public Object getTree() {
        List<TreeNode> tree = resourceService.getTree();
        return ResponseBo.ok(tree);
    }

    /**
     * 树形菜单（根据角色ID获取对应节点）
     */
    @PostMapping("/getTreeByUserId")
    public Object getTreeByUserId(@RequestBody Map<String, String> params) {
        String userId = params.get("userId");
        List<TreeNode> tree = resourceService.getTreeByUserId(userId);
        return ResponseBo.ok(tree);
    }

    /**
     * 树形菜单（根据username获取对应节点）
     */
    @PostMapping("/getTreeByUsername")
    public Object getTreeByUsername(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        List<TreeNode> tree = resourceService.getTreeByUsername(username);
        return ResponseBo.ok(tree);
    }

}

