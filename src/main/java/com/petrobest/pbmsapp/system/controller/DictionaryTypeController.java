package com.petrobest.pbmsapp.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.petrobest.pbmsapp.common.domain.ResponseBo;
import com.petrobest.pbmsapp.system.domain.DictionaryItemDO;
import com.petrobest.pbmsapp.system.domain.DictionaryTypeDO;
import com.petrobest.pbmsapp.system.service.DictionaryItemService;
import com.petrobest.pbmsapp.system.service.DictionaryTypeService;
import com.petrobest.pbmsapp.system.vo.DictionaryTypeVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/system/dictionaryType")
public class DictionaryTypeController extends BaseController {

    @Autowired
    private DictionaryTypeService dictionaryTypeService;

    @Autowired
    private DictionaryItemService dictionaryItemService;

    /**
     * 新增
     */
    @PostMapping("/save")
    public Object save(@RequestBody DictionaryTypeDO dictionaryType) {
        boolean save = false;
        try {
            save = dictionaryTypeService.save(dictionaryType);
        } catch (Exception e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                return ResponseBo.error("编码已存在");
            }
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
    public Object update(@RequestBody DictionaryTypeDO dictionaryType) {
        boolean update = dictionaryTypeService.updateById(dictionaryType);
        if (update) {
            return ResponseBo.ok("修改成功");
        }
        return ResponseBo.error("修改失败");
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    public Object delete(@RequestBody DictionaryTypeDO dictionaryType) {
        boolean delete = dictionaryTypeService.removeById(dictionaryType.getId());
        boolean remove = dictionaryItemService.remove(new QueryWrapper<DictionaryItemDO>().eq(DictionaryItemDO.TYPE_ID, dictionaryType.getId()));
        if (delete && remove) {
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
        boolean deleteBatch = dictionaryTypeService.removeByIds(ids);
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
    public Object find(@RequestBody DictionaryTypeDO dictionaryType) {
        DictionaryTypeDO find = dictionaryTypeService.getOne(new QueryWrapper<DictionaryTypeDO>().eq(DictionaryTypeDO.ID, dictionaryType.getId()));
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
    public Object list(@RequestBody DictionaryTypeVO dictionaryTypeVO) {
        //分页构造器
        Page<DictionaryTypeDO> page = new Page<>(dictionaryTypeVO.getCurrPage(), dictionaryTypeVO.getPageSize());
        //条件构造器
        QueryWrapper<DictionaryTypeDO> queryWrapper = new QueryWrapper<>(dictionaryTypeVO.getEntity());
        //执行分页
        IPage<DictionaryTypeDO> pageList = dictionaryTypeService.page(page, queryWrapper);
        //返回结果
        return ResponseBo.ok(pageList);
    }

    /**
     * 全部查询
     */
    @PostMapping("/listAll")
    @ResponseBody
    public Object listAll(@RequestBody DictionaryTypeDO dictionaryTypeDO) {
        QueryWrapper<DictionaryTypeDO> wrapper = new QueryWrapper<>();

        if (StringUtils.isNotBlank(dictionaryTypeDO.getText())) {
            wrapper.like(DictionaryTypeDO.TEXT, dictionaryTypeDO.getText());
        }

        List<DictionaryTypeDO> list = dictionaryTypeService.list(wrapper);
        //返回结果
        return ResponseBo.ok(list);
    }

}
