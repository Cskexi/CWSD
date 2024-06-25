package com.example.demo.aop.controller;

import com.example.demo.aop.entity.SysLog;
import com.example.demo.aop.service.SysLogService;
import com.example.demo.springboot2023.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 操作日志表 前端控制器
 * </p>
 *
 * @author Chrimer
 * @since 2023-12-08
 */
@RestController
@RequestMapping("/aop/sys-log")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @ApiOperation(value = "保存修改SysLog信息")
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result addOrUpdate(@RequestBody SysLog sysLog) {
        Result result = new Result();

        //交给业务去处理，service
        sysLogService.addOrUpdate(sysLog);

        return result;
    }



    @ApiOperation(value = "批量删除记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids",required = true,paramType = "query",value = "需要删除的多个id，用逗号,隔开")
    })
    @RequestMapping(method = RequestMethod.POST,value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        sysLogService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }


    @ApiOperation(value = "查询所有记录")
    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result list(){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(sysLogService.list());
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
        result.setData(sysLogService.page(pageNum,pageSize));
        return result;
    }

    @ApiOperation(value = "通过id获取其对应的实体")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "id",required = true,paramType = "query",value = "需要查询的id")
    })
    @RequestMapping(method = RequestMethod.GET,value = "/getById")
    public Result getById(String id) {
        Result result = new Result();
        SysLog sysLog = sysLogService.myGetById(id);
        result.setData(sysLog);
        result.success("删除成功");
        return result;
    }
}
