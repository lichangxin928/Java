package com.lcx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcx.domain.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentDao extends BaseMapper<Student> {
    @Select("select * from student")
    List<Student> findAll();
}
