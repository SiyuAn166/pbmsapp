package com.petrobest.pbmsapp.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.petrobest.pbmsapp.common.annotation.Log;
import com.petrobest.pbmsapp.common.domain.ResponseBo;
import com.petrobest.pbmsapp.system.domain.RoleDO;
import com.petrobest.pbmsapp.system.domain.RoleResourceDO;
import com.petrobest.pbmsapp.system.domain.UserDO;
import com.petrobest.pbmsapp.system.domain.UserRoleDO;
import com.petrobest.pbmsapp.system.service.RoleResourceService;
import com.petrobest.pbmsapp.system.service.RoleService;
import com.petrobest.pbmsapp.system.service.UserRoleService;
import com.petrobest.pbmsapp.system.vo.RoleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleResourceService roleResourceService;

    /**
     * 新增
     */
    @Log("新增角色")
    @PostMapping("/save")
    @ResponseBody
    @Transactional
    public Object save(@RequestBody RoleDO role) {
        log.info(role.toString());
        boolean save = roleService.save(role);
        boolean saveRoleResource = roleResourceService.saveRoleResource(role.getRoleId(), role.getResourceIds());
        if (save && saveRoleResource) {
            return ResponseBo.ok("保存角色成功");
        }
        return ResponseBo.error("保存角色失败");
    }

    /**
     * 修改
     */
    @Log("修改角色")
    @PostMapping("/update")
    @ResponseBody
    @Transactional
    public Object update(@RequestBody RoleDO role) {
        log.info(String.format("update role: {}"), role);
        boolean update = roleService.updateById(role);
        boolean saveRoleResource = roleResourceService.saveRoleResource(role.getRoleId(), role.getResourceIds());
        if (update && saveRoleResource) {
            return ResponseBo.ok("修改角色成功");
        }
        return ResponseBo.error("修改角色失败");
    }

    /**
     * 删除
     */
    @Log("删除角色")
    @PostMapping("/delete")
    @ResponseBody
    @Transactional
    public Object delete(@RequestBody RoleDO role) {
        boolean f1 = roleService.removeById(role.getRoleId());
        boolean f2 = roleResourceService.remove(new QueryWrapper<RoleResourceDO>().eq(RoleDO.ROLE_ID, role.getRoleId()));
        boolean f3 = userRoleService.remove(new QueryWrapper<UserRoleDO>().eq(RoleDO.ROLE_ID, role.getRoleId()));
        if (f1 && f2 && f3) {
            return ResponseBo.ok("删除成功");
        } else {
            return ResponseBo.error("没有找到该对象");
        }
    }

    /**
     * 批量删除
     */
    @Log("批量删除角色")
    @PostMapping("/deleteBatch")
    @ResponseBody
    @Transactional
    public Object deleteBatch(@RequestBody Map<String, List<String>> params) {
        Set<Map.Entry<String, List<String>>> roleEntries = params.entrySet();
        Iterator<Map.Entry<String, List<String>>> it = roleEntries.iterator();
        List<String> roleIds = it.next().getValue();

        if (roleIds == null || roleIds.size() <= 0) {
            return ResponseBo.error("参数有误！");
        }
        boolean f1 = roleService.removeByIds(roleIds);
        boolean f2 = roleResourceService.remove(new QueryWrapper<RoleResourceDO>().in(RoleDO.ROLE_ID, roleIds));
        boolean f3 = userRoleService.remove(new QueryWrapper<UserRoleDO>().in(RoleDO.ROLE_ID, roleIds));

        if (f1 && f2 && f3) {
            return ResponseBo.ok("删除成功");
        } else {
            return ResponseBo.error("没有找到该对象");
        }
    }

    /**
     * 查询
     */
    @PostMapping("/find")
    @ResponseBody
    public Object find(@RequestBody RoleDO role) {
        RoleDO find = roleService.getOne(new QueryWrapper<RoleDO>().eq(RoleDO.ROLE_ID, role.getRoleId()));
        if (find != null) {
            List<String> resourceIds = roleResourceService.listResourceIds(role.getRoleId());
            find.setResourceIds(resourceIds);
            return ResponseBo.ok(find);
        } else {
            return ResponseBo.error("没有找到该对象");
        }
    }

    /**
     * 分页查询
     */
    @PostMapping("/list")
    @ResponseBody
    public Object list(@RequestBody RoleVO roleVO) {
        //分页构造器
        Page<RoleDO> page = new Page<>(roleVO.getCurrPage(), roleVO.getPageSize());
        //条件构造器
        QueryWrapper<RoleDO> queryWrapperw = new QueryWrapper<>(roleVO.getEntity());
        //执行分页
        IPage<RoleDO> pageList = roleService.page(page, queryWrapperw);
        //返回结果
        //返回结果
        return ResponseBo.ok(pageList);
    }

    /**
     * 全部查询
     */
    @PostMapping("/listAll")
    @ResponseBody
    public Object listAll() {
        //执行分页
        List<RoleDO> list = roleService.list();
        //返回结果
        return ResponseBo.ok(list);
    }

    /**
     * 查询remarks，用来前端筛选路由列表 roles:[]
     */
    @PostMapping("/listRemarksByUsername")
    @ResponseBody
    public Object listRemarksByUsername(@RequestBody UserDO userDO) {
        //执行分页
        List<String> list = roleService.listRolesRemarkByUsername(userDO.getUsername());
        //返回结果
        return ResponseBo.ok(list);
    }
}
