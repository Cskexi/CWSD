package com.example.demo.chapter5.controller;

import com.example.demo.springboot2023.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.chapter5.entity.Student;
import com.example.demo.chapter5.service.StudentService;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 用户表 前端控制器,表的注释
 * </p>
 *
 * @author Csk
 * @since 2023-12-03
 */
@Slf4j
@RestController
@RequestMapping("/chapter5/student")
public class StudentController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "保存修改Student信息")
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result addOrUpdate(@RequestBody Student student) {
        Result result = new Result();

        //交给业务去处理，service
        studentService.addOrUpdate(student);

        return result;
    }



    @ApiOperation(value = "批量删除记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids",required = true,paramType = "query",value = "需要删除的多个id，用逗号,隔开")
    })
    @RequestMapping(method = RequestMethod.POST,value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        studentService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }


    @ApiOperation(value = "查询所有记录")
    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result list(){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(studentService.list());
        return result;
    }

    @ApiOperation(value = "分页获取记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum",required = true,paramType = "query",value = "当前页码"),
        @ApiImplicitParam(name = "pageSize",required = true,paramType = "query",value = "每页记录数")
    })
    @RequestMapping(method = RequestMethod.POST,value = "/page")
    public Result page( Integer pageNum,Integer pageSize ){
        System.out.println("pageNum = " + pageNum);



        Result result = new Result();
        result.success("分页获取成功");
        result.setData(studentService.page(pageNum,pageSize));
        return result;
    }

    @ApiOperation(value = "通过id获取其对应的实体")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required = true,paramType = "query",value = "需要查询的id")
    })
    @RequestMapping(method = RequestMethod.GET,value = "/getById")
    public Result getById(String id) {
        Result result = new Result();
        logger.trace("this is trace");
        logger.debug("this is debug");
        logger.info("this is info");
        logger.warn("this is warn");
        logger.error("this is error");
        log.trace("this is trace");
        log.debug("this is debug");
        log.info("this is info");
        log.warn("this is warn");
        log.error("this is error");
        Student student = studentService.myGetById(id);
        result.setData(student);
        result.success("删除成功");
        return result;
    }
}
