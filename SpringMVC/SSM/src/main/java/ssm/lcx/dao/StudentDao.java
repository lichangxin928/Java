package ssm.lcx.dao;

import ssm.lcx.domain.Student;

import java.util.List;

public interface StudentDao {
    int insertStudent(Student student);
    List<Student> selectStudent();


}
