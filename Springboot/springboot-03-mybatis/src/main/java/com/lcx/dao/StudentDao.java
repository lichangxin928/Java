package com.lcx.dao;

import com.lcx.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentDao {

    @Select("select * from student where id = #{id}")
    Student findById(Integer id);
}
