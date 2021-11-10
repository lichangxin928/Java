package com.lcx.service.impl;

import com.lcx.dao.StudentDao;
import com.lcx.domain.Student;
import com.lcx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }
}
