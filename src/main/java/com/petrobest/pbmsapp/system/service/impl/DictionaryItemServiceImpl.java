package com.petrobest.pbmsapp.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.petrobest.pbmsapp.system.dao.DictionaryItemDao;
import com.petrobest.pbmsapp.system.domain.DictionaryItemDO;
import com.petrobest.pbmsapp.system.domain.DictionaryTypeDO;
import com.petrobest.pbmsapp.system.service.DictionaryItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryItemServiceImpl extends ServiceImpl<DictionaryItemDao, DictionaryItemDO> implements DictionaryItemService {
    @Override
    public IPage<DictionaryItemDO> listItemsWithType(Page page, DictionaryItemDO itemDO) {
        return baseMapper.listItemsWithType(page, itemDO);
    }

    @Override
    public List<DictionaryItemDO> listItemsByType(DictionaryTypeDO type) {
        return baseMapper.listItemsByType(type);
    }
}
