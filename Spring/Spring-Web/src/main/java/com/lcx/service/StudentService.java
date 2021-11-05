package com.lcx.service;

import com.lcx.domain.Student;

import java.util.List;

public interface StudentService {
    Student getStudentById(int id);
    List<Student> getAllStudents();
    int addStudent(Student student);
    public void transfer(int sourceId,int targetId,int num);
}
