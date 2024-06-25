package com.example.demo.chapter1.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.springboot2023.utils.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/index")
    public String index(){
        return "Hello, world!";

    }
}


