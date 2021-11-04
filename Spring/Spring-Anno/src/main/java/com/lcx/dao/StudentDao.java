package com.lcx.dao;

import com.lcx.domain.Student;

import java.util.List;

public interface StudentDao {
    List<Student> findAll();
    Student selectById(int id );
    int insertStudent(Student student);
    void update(Student student);
}
