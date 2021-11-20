package com.lcx.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Value("${country}")
    private String contry;

    @Value("#{myDataSource}")
    private MyDataSource source;

    @RequestMapping
    public String getBooks(){
        System.out.println(source);
        return contry;
    }
}
