package com.example.demo.chapter1.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.aop.OperLog;
import com.example.demo.chapter1.entity.User;
import com.example.demo.springboot2023.utils.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class Test {

    //@OperLog(operModul = "hello模块",operType = "GET")
    @RequestMapping(method = RequestMethod.GET,value = "testAOP")
    public Result testAOP(String name){
        if(StringUtils.isBlank(name)){
            throw new RuntimeException("name 不能为空");
        }else{
            Result result = new Result();
            result.success("success ok!");
            return result;
        }
    }


    @RequestMapping(method = RequestMethod.GET,value = "/get")
    public Result get(){
        Result result = new Result();
        result.setData("123");
        //result.unauthorized(id);
        return result;
    }

    @RequestMapping(method =RequestMethod.POST,value = "/post")
    public Result post( User user){
        Result result = new Result();
        result.setData(user);
        return result;
    }

    @RequestMapping(method =RequestMethod.POST,value = "/postJson")
    public Result postJson(@RequestBody User user){
        Result result = new Result();
        //result.unauthorized(user.getUsername()+":"+user.getPassword());
        result.setData(user);
        return result;
    }
}
