package ssm.lcx.service.impl;

import org.springframework.stereotype.Service;
import ssm.lcx.dao.StudentDao;
import ssm.lcx.domain.Student;
import ssm.lcx.service.StudentService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    // 引用类型的自动注入
    @Resource
    private StudentDao studentDao;
    @Override
    public int addStudent(Student student) {
        int num = studentDao.insertStudent(student);
        return num;
    }

    @Override
    public List<Student> findStudent() {
        List<Student> list = studentDao.selectStudent();
        return list;
    }
}
