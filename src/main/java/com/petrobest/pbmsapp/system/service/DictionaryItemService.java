package com.petrobest.pbmsapp.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.petrobest.pbmsapp.system.domain.DictionaryItemDO;
import com.petrobest.pbmsapp.system.domain.DictionaryTypeDO;

import java.util.List;

public interface DictionaryItemService extends IService<DictionaryItemDO> {
    IPage<DictionaryItemDO> listItemsWithType(Page page, DictionaryItemDO itemDO);

    List<DictionaryItemDO> listItemsByType(DictionaryTypeDO type);
}
