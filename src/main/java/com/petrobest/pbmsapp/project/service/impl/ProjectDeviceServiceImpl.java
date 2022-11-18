package com.petrobest.pbmsapp.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.petrobest.pbmsapp.project.dao.ProjectDeviceDao;
import com.petrobest.pbmsapp.project.domain.ProjectDeviceDO;
import com.petrobest.pbmsapp.project.service.ProjectDeviceService;
import org.springframework.stereotype.Service;

@Service
public class ProjectDeviceServiceImpl extends ServiceImpl<ProjectDeviceDao, ProjectDeviceDO> implements ProjectDeviceService {
}