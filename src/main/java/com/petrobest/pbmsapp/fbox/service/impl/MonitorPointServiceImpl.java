package com.petrobest.pbmsapp.fbox.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.petrobest.pbmsapp.fbox.dao.MonitorPointDao;
import com.petrobest.pbmsapp.fbox.domain.MonitorPointDO;
import com.petrobest.pbmsapp.fbox.service.MonitorPointService;
import org.springframework.stereotype.Service;

@Service
public class MonitorPointServiceImpl extends ServiceImpl<MonitorPointDao,MonitorPointDO> implements MonitorPointService {

}
