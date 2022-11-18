package com.petrobest.pbmsapp.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.petrobest.pbmsapp.common.domain.ResponseBo;
import com.petrobest.pbmsapp.system.domain.DictionaryItemDO;
import com.petrobest.pbmsapp.system.domain.DictionaryTypeDO;
import com.petrobest.pbmsapp.system.service.DictionaryItemService;
import com.petrobest.pbmsapp.system.vo.DictionaryItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/system/dictionaryItem")
public class DictionaryItemController extends BaseController {

    @Autowired
    private DictionaryItemService dictionaryItemService;

    /**
     * 新增
     */
    @PostMapping("/save")
    public Object save(@RequestBody DictionaryItemDO dictionaryItem) {
        boolean save = dictionaryItemService.save(dictionaryItem);
        if (save) {
            return ResponseBo.ok("保存成功");
        }
        return ResponseBo.error("保存失败");
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public Object update(@RequestBody DictionaryItemDO dictionaryItem) {
        boolean update = dictionaryItemService.updateById(dictionaryItem);
        if (update) {
            return ResponseBo.ok("修改成功");
        }
        return ResponseBo.error("修改失败");
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public Object delete(@RequestBody DictionaryItemDO dictionaryItem) {
        boolean delete = dictionaryItemService.removeById(dictionaryItem.getId());
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
        boolean deleteBatch = dictionaryItemService.removeByIds(ids);
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
    public Object find(@RequestBody DictionaryItemDO dictionaryItem) {
        DictionaryItemDO find = dictionaryItemService.getOne(new QueryWrapper<DictionaryItemDO>().eq(DictionaryItemDO.ID, dictionaryItem.getId()));
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
    public Object list(@RequestBody DictionaryItemVO dictionaryItemVO) {
        //分页构造器
        Page<DictionaryItemDO> page = new Page<>(dictionaryItemVO.getCurrPage(), dictionaryItemVO.getPageSize());
        //条件构造器
        QueryWrapper<DictionaryItemDO> queryWrapper = new QueryWrapper<>(dictionaryItemVO.getEntity());
        //执行分页
        IPage<DictionaryItemDO> pageList = dictionaryItemService.page(page, queryWrapper);
        //返回结果
        return ResponseBo.ok(pageList);
    }

    /**
     * 查询带类型
     */
    @PostMapping("/listItemsWithType")
    public Object listItemsWithType(@RequestBody DictionaryItemVO dictionaryItemVO) {
        //分页构造器
        Page<DictionaryItemDO> page = new Page<>(dictionaryItemVO.getCurrPage(), dictionaryItemVO.getPageSize());
        //执行分页
        IPage<DictionaryItemDO> pageList = dictionaryItemService.listItemsWithType(page, dictionaryItemVO.getEntity());
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
        List<DictionaryItemDO> list = dictionaryItemService.list();
        //返回结果
        return ResponseBo.ok(list);
    }

    /**
     * 查询 by类型
     */
    @PostMapping("/listItemsByType")
    public Object listItemsByType(@RequestBody DictionaryTypeDO dictionaryTypeDO) {
        List<DictionaryItemDO> itemDOS = dictionaryItemService.listItemsByType(dictionaryTypeDO);
        //返回结果
        return ResponseBo.ok(itemDOS);
    }

}
