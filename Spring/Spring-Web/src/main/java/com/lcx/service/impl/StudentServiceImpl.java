package com.lcx.service.impl;

import com.lcx.dao.StudentDao;
import com.lcx.domain.Student;
import com.lcx.event.MyApplicationEvent;
import com.lcx.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Resource
    StudentDao studentDao;
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;
    @Override
    public Student getStudentById(int id) {
        System.out.println("返回查询的student");
        return studentDao.selectById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        System.out.println("返回查询到的所有student");
        return studentDao.findAll();
    }

    @Override
    public int addStudent(Student student) {
        System.out.println("添加student");
        int count = studentDao.insertStudent(student);
        return count;
    }

    @Transactional
    @Override
    public void transfer(int sourceId, int targetId, int num) {
        Student source = getStudentById(sourceId);
        Student target = getStudentById(targetId);
        try {

            int sourceAge = source.getAge();
            source.setAge(--sourceAge);
            int targetAge = target.getAge();
            target.setAge(++targetAge);
            studentDao.update(source);
            studentDao.update(target);
        }finally {
            applicationEventPublisher.publishEvent(new MyApplicationEvent(source));
        }

    }

}
