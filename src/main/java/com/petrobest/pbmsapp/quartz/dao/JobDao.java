package com.petrobest.pbmsapp.quartz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.petrobest.pbmsapp.quartz.domain.Job;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JobDao extends BaseMapper<Job> {
    boolean updateBatchByIds(@Param("job") Job job, @Param("ids") List<String> jobIds);
}
