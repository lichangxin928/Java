package com.lcx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcx.domain.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentDao extends BaseMapper<Student> {
}
