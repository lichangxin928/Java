package com.lcx.dao;

import com.lcx.domain.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentDao {
    @Select("select * from student")
    List<Student> findAll();
}
