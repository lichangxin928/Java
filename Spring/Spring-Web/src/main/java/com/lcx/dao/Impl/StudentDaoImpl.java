package com.lcx.dao.Impl;

import com.lcx.dao.StudentDao;
import com.lcx.domain.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;


@Repository("studentDao")
public class StudentDaoImpl implements StudentDao {
    @Resource
    JdbcTemplate jdbcTemplate;
    @Override
    public List<Student> findAll() {
        List<Student> students = jdbcTemplate.query("select * from student", new BeanPropertyRowMapper<Student>(Student.class));
        return  students;
    }

    @Override
    public Student selectById(int id) {
        List<Student> student = jdbcTemplate.query("select * from student where id = ?", new BeanPropertyRowMapper<Student>(Student.class), id);
        return student.get(0);
    }

    @Override
    public int insertStudent(Student student) {
        return 0;
    }

    @Override
    public void update(Student student) {
        jdbcTemplate.update("update student set age = ? where id = ?",student.getAge(),student.getId());
    }


}
