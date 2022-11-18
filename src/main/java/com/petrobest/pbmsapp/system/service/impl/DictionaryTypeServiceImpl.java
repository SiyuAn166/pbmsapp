package com.petrobest.pbmsapp.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.petrobest.pbmsapp.system.dao.DictionaryTypeDao;
import com.petrobest.pbmsapp.system.domain.DictionaryTypeDO;
import com.petrobest.pbmsapp.system.service.DictionaryTypeService;
import org.springframework.stereotype.Service;

@Service
public class DictionaryTypeServiceImpl extends ServiceImpl<DictionaryTypeDao, DictionaryTypeDO> implements DictionaryTypeService {
}
