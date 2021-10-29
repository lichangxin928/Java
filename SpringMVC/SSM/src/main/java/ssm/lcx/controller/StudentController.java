package ssm.lcx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ssm.lcx.domain.Student;
import ssm.lcx.service.StudentService;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Resource
    private StudentService service;
    // 注册学生
    @RequestMapping("/addStudent.do")
    public ModelAndView addStudent(Student student){
        System.out.println("开始注册");
        String tips = "注册失败";
        ModelAndView mv = new ModelAndView();
        int nums = service.addStudent(student);
        if (nums > 0) {
            // 注册成功
            tips = "学生 " +student.getName()+ " 注册成功";
        }
        mv.addObject("tips",tips);
        mv.setViewName("result");
        System.out.println("注册结束");
        return mv;
    }
    // 处理查询，响应 ajax
    @RequestMapping("/queryStudent.do")
    @ResponseBody
    public List<Student> queryStudent(){
        System.out.println("开始查询");
        List<Student> students = service.findStudent();
        System.out.println("查询结束");
        return students;

    }
}
