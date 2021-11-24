package com.lcx.controller;

import com.lcx.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Controller
public class indexController {

    @GetMapping({"/","/login"})
    public String loginPage(){

        return "login";
    }
    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model){

        return "login";
    }
    @GetMapping("/main.html")
    public String mainPage(HttpSession session){

        return "main";
    }
    @GetMapping("/file")
    public String filePage(){

        return "file";
    }

    /**
     * MultipartFile 自动封装上传的文件
     * @param signalFile
     * @param multipleFile
     * @return
     */
    @PostMapping("/upload")
    public String upload(@RequestPart("signalFile") MultipartFile signalFile,
                         @RequestPart("multipleFile") MultipartFile[] multipleFile) throws IOException {

        signalFile.transferTo(new File("D:\\imgs\\" + signalFile.getOriginalFilename()));
        for (MultipartFile file : multipleFile) {
            file.transferTo(new File("D:\\imgs\\" + file.getOriginalFilename()));
        }
        return "file";
    }
}
