package com.lcx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcx.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentDao extends BaseMapper<Student> {
}
