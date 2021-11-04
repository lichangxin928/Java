package com.lcx.dao;

import com.lcx.domain.Student;

import java.util.List;

public interface studentDao {
    List<Student> findAll();
    Student findStudentById(int id);
    int insertStudent(Student student);
}
