package com.petrobest.pbmsapp.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.petrobest.pbmsapp.system.domain.DictionaryItemDO;
import com.petrobest.pbmsapp.system.domain.DictionaryTypeDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DictionaryItemDao extends BaseMapper<DictionaryItemDO> {
    IPage<DictionaryItemDO> listItemsWithType(Page page, @Param("itemDO") DictionaryItemDO itemDO);

    List<DictionaryItemDO> listItemsByType(@Param("type") DictionaryTypeDO type);
}
