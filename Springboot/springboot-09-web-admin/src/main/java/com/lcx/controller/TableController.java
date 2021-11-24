package com.lcx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TableController {

    @GetMapping("/basic_table.html")
    public String basic_table(){

        return "/table/basic_table";
    }

    @GetMapping("/dynamic_table.html")
    public String dynamic_table(){

        return "/table/dynamic_table";
    }

    @GetMapping("/responsive_table.html")
    public String responsive_table(){

        return "/table/responsive_table";
    }

    @GetMapping("/editable_table.html")
    public String editable_table(){

        return "/table/editable_table";
    }
}
