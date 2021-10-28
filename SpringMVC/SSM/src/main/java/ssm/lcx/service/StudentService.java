package ssm.lcx.service;

import ssm.lcx.domain.Student;

import java.util.List;

public interface StudentService {
    int addStudent(Student student);
    List<Student> findStudent();
}
