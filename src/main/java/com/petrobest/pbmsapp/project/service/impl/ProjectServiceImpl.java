package com.petrobest.pbmsapp.project.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.petrobest.pbmsapp.project.dao.ProjectDao;
import com.petrobest.pbmsapp.project.domain.ProjectDO;
import com.petrobest.pbmsapp.project.service.ProjectService;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectDao, ProjectDO> implements ProjectService {
    @Override
    public ProjectDO findOne(ProjectDO projectDO) {
        return this.baseMapper.findOne(projectDO);
    }

}
