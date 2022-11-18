package com.petrobest.pbmsapp.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.petrobest.pbmsapp.common.domain.ResponseBo;
import com.petrobest.pbmsapp.project.domain.ProjectDO;
import com.petrobest.pbmsapp.project.domain.ProjectDeviceDO;
import com.petrobest.pbmsapp.project.service.ProjectDeviceService;
import com.petrobest.pbmsapp.project.service.ProjectService;
import com.petrobest.pbmsapp.project.vo.ProjectVO;
import com.petrobest.pbmsapp.system.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/project/project")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectDeviceService projectDeviceService;

    /**
     * 新增
     */
    @PostMapping("/save")
    @Transactional
    public Object save(@RequestBody ProjectDO project) {
        boolean save = projectService.save(project);
        if (project.getProjectDeviceDOs().size() > 0) {
            boolean savepd = projectDeviceService.saveBatch(project.getProjectDeviceDOs());
            save = save && savepd;
        }
        if (save) {
            return ResponseBo.ok("保存成功");
        }
        return ResponseBo.error("保存失败");
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @Transactional
    public Object update(@RequestBody ProjectDO project) {
        boolean update = projectService.updateById(project);
        projectDeviceService.remove(new UpdateWrapper<ProjectDeviceDO>().eq(ProjectDeviceDO.PRO_ID, project.getProId()));
        if (project.getProjectDeviceDOs().size() > 0) {
            boolean saveBatch = projectDeviceService.saveBatch(project.getProjectDeviceDOs());
            update = update && saveBatch;
        }
        if (update) {
            return ResponseBo.ok("修改成功");
        }
        return ResponseBo.error("修改失败");
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @Transactional
    public Object delete(@RequestBody ProjectDO project) {
        boolean remove = projectDeviceService.remove(new QueryWrapper<ProjectDeviceDO>().eq(ProjectDeviceDO.PRO_ID, project.getProId()));
        boolean delete = projectService.removeById(project.getId());
        if (delete && remove) {
            return ResponseBo.ok("删除成功");
        } else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手動回滾
            return ResponseBo.error("没有找到该对象");
        }
    }

    /**
     * 批量删除
     */
    @PostMapping("/deleteBatch")
    @Transactional
    public Object deleteBatch(@RequestBody Map<String, List<String>> params) {

        Set<Map.Entry<String, List<String>>> entries = params.entrySet();
        Iterator<Map.Entry<String, List<String>>> it = entries.iterator();
        List<String> ids = it.next().getValue();

        if (ids == null || ids.size() <= 0) {
            return ResponseBo.error("参数有误！");
        }

        Collection<ProjectDO> projectDOS = projectService.listByIds(ids);
        List<String> proIds = new ArrayList<>();
        projectDOS.forEach((projectDO -> {
            proIds.add(projectDO.getProId());
        }));
        boolean remove = projectDeviceService.remove(new QueryWrapper<ProjectDeviceDO>().in(ProjectDeviceDO.PRO_ID, proIds));

        boolean deleteBatch = projectService.removeByIds(ids);
        if (deleteBatch&&remove) {
            return ResponseBo.ok("删除成功");
        } else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手動回滾
            return ResponseBo.error("没有找到该对象");
        }
    }

    /**
     * 查询
     */
    @PostMapping("/find")
    public Object find(@RequestBody ProjectDO project) {
//        ProjectDO find = projectService.getOne(new QueryWrapper<ProjectDO>().eq(ProjectDO.ID, project.getId()));
        ProjectDO find = projectService.findOne(project);
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
    public Object list(@RequestBody ProjectVO projectVO) {
        //分页构造器
        Page<ProjectDO> page = new Page<>(projectVO.getCurrPage(), projectVO.getPageSize());
        //条件构造器
        QueryWrapper<ProjectDO> queryWrapper = new QueryWrapper<>(projectVO.getEntity());
        //执行分页
        IPage<ProjectDO> pageList = projectService.page(page, queryWrapper);
        //返回结果
        return ResponseBo.ok(pageList);
    }

    /**
     * 全部查询
     */
    @PostMapping("/listAll")
    public Object listAll() {
        //执行分页
        List<ProjectDO> list = projectService.list();
        //返回结果
        return ResponseBo.ok(list);
    }

}
