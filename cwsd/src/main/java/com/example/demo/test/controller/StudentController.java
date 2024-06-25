package com.example.demo.test.controller;

import com.example.demo.springboot2023.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.test.entity.Student;
import com.example.demo.test.service.StudentService;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 用户表 前端控制器,表的注释
 * </p>
 *
 * @author Csk
 * @since 2023-12-11
 */
@RestController
@RequestMapping("/test/student")
public class StudentController {
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
        Student student = studentService.myGetById(id);
        result.setData(student);
        result.success("成功得到");
        return result;
    }
}
