package com.petrobest.pbmsapp.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.petrobest.pbmsapp.project.domain.ProjectDO;

public interface ProjectService extends IService<ProjectDO> {
    ProjectDO findOne(ProjectDO projectDO);
}
