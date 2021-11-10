package com.lcx.dao;

import com.lcx.domain.Student;

import java.util.List;

public interface StudentDao {
    List<Student> findAll();
}
