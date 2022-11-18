package com.petrobest.pbmsapp.fbox.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.petrobest.pbmsapp.fbox.dao.DeviceDao;
import com.petrobest.pbmsapp.fbox.domain.DeviceDO;
import com.petrobest.pbmsapp.fbox.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceDao, DeviceDO> implements DeviceService {
}
