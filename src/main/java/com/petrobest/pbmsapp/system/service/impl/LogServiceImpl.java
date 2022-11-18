package com.petrobest.pbmsapp.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.petrobest.pbmsapp.system.dao.LogDao;
import com.petrobest.pbmsapp.system.domain.LogDO;
import com.petrobest.pbmsapp.system.service.LogService;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl extends ServiceImpl<LogDao, LogDO> implements LogService {
}
