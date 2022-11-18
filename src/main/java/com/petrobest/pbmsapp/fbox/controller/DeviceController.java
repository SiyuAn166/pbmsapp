package com.petrobest.pbmsapp.fbox.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.petrobest.pbmsapp.common.annotation.Log;
import com.petrobest.pbmsapp.common.domain.ResponseBo;
import com.petrobest.pbmsapp.fbox.domain.DeviceDO;
import com.petrobest.pbmsapp.fbox.service.DeviceService;
import com.petrobest.pbmsapp.fbox.vo.DeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/devicemgr/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    /**
     * 新增
     */
    @PostMapping("/save")
    public Object save(@RequestBody DeviceDO device) {
        boolean save = deviceService.save(device);
        if (save) {
            return ResponseBo.ok("保存成功");
        }
        return ResponseBo.error("保存失败");
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public Object update(@RequestBody DeviceDO device) {
        boolean update = deviceService.updateById(device);
        if (update) {
            return ResponseBo.ok("修改成功");
        }
        return ResponseBo.error("修改失败");
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public Object delete(@RequestBody DeviceDO device) {
        boolean delete = deviceService.removeById(device.getBoxId());
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
        boolean deleteBatch = deviceService.removeByIds(ids);
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
    public Object find(@RequestBody DeviceDO device) {
        DeviceDO find = deviceService.getOne(new QueryWrapper<DeviceDO>().eq(DeviceDO.BOX_ID, device.getBoxId()));
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
    public Object list(@RequestBody DeviceVO deviceVO) {
        //分页构造器
        Page<DeviceDO> page = new Page<>(deviceVO.getCurrPage(), deviceVO.getPageSize());
        //条件构造器
        QueryWrapper<DeviceDO> queryWrapper = new QueryWrapper<>(deviceVO.getEntity());
        //执行分页
        IPage<DeviceDO> pageList = deviceService.page(page, queryWrapper);
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
        List<DeviceDO> list = deviceService.list();
        //返回结果
        return ResponseBo.ok(list);
    }


}
