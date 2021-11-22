package com.lcx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcx.dao.StudentDao;
import com.lcx.domain.Student;
import com.lcx.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {
}
