package com.petrobest.pbmsapp.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.petrobest.pbmsapp.common.domain.ResponseBo;
import com.petrobest.pbmsapp.system.domain.LogDO;
import com.petrobest.pbmsapp.system.service.LogService;
import com.petrobest.pbmsapp.system.vo.LogVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/system/log")
public class LogController extends BaseController {

    @Autowired
    private LogService logService;

    /**
     * 新增
     */
    @PostMapping("/save")
    public Object save(@RequestBody LogDO log) {
        boolean save = logService.save(log);
        if (save) {
            return ResponseBo.ok("保存成功");
        }
        return ResponseBo.error("保存失败");
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public Object update(@RequestBody LogDO log) {
        boolean update = logService.updateById(log);
        if (update) {
            return ResponseBo.ok("修改成功");
        }
        return ResponseBo.error("修改失败");
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public Object delete(@RequestBody LogDO log) {
        boolean delete = logService.removeById(log.getLogId());
        if (delete) {
            return ResponseBo.ok("删除成功");
        } else {
            return ResponseBo.error("没有找到该对象");
        }
    }

    /**
     * 批量删除
     */
    @PostMapping("/deleteBatch")
    @ResponseBody
    public Object deleteBatch(@RequestBody Map<String, List<String>> params) {

        Set<Map.Entry<String, List<String>>> entries = params.entrySet();
        Iterator<Map.Entry<String, List<String>>> it = entries.iterator();
        List<String> ids = it.next().getValue();

        if (ids == null || ids.size() <= 0) {
            return ResponseBo.error("参数有误！");
        }
        boolean deleteBatch = logService.removeByIds(ids);
        if (deleteBatch) {
            return ResponseBo.ok("删除成功");
        } else {
            return ResponseBo.error("没有找到该对象");
        }
    }

    /**
     * 查询
     */
    @PostMapping("/find")
    public Object find(@RequestBody LogDO log) {
        LogDO find = logService.getOne(new QueryWrapper<LogDO>().eq(LogDO.LOG_ID, log.getLogId()));
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
    public Object list(@RequestBody LogVO logVO) {
        //分页构造器
        Page<LogDO> page = new Page<>(logVO.getCurrPage(), logVO.getPageSize());
        //条件构造器
        QueryWrapper<LogDO> queryWrapper = new QueryWrapper<>();
        //执行分页
        if (StringUtils.isNotEmpty(logVO.getEntity().getUsername())) {
            queryWrapper.likeRight(LogDO.USERNAME, logVO.getEntity().getUsername());
        }
        queryWrapper.orderByDesc(LogDO.CREATE_TIME);
        IPage<LogDO> pageList = logService.page(page, queryWrapper);
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
        List<LogDO> list = logService.list();
        //返回结果
        return ResponseBo.ok(list);
    }

}
