package com.petrobest.pbmsapp.project.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.petrobest.pbmsapp.project.domain.ProjectDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProjectDao extends BaseMapper<ProjectDO> {
    ProjectDO findOne(@Param("projectDO") ProjectDO projectDO);
}
