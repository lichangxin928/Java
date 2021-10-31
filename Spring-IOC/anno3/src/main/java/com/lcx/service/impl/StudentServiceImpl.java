package com.lcx.service.impl;

import com.lcx.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Override
    public void saveStudent() {
        System.out.println("save Student");
    }
}
