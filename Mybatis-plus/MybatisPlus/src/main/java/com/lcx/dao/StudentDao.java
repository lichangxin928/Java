package com.lcx.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcx.domain.Student;

import java.util.List;

public interface StudentDao extends BaseMapper<Student> {
    List<Student> findAll();
}
