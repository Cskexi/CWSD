package com.example.cwsd.Dictype.controller;

import com.example.cwsd.Dictype.entity.Dictype;
import com.example.cwsd.Dictype.service.DictypeService;
import com.example.cwsd.other.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 字典类型表 前端控制器,表的注释
 * </p>
 *
 * @author Csk
 * @since 2024-05-14
 */
@RestController
@RequestMapping("/Dictype/")
public class DictypeController {
    @Autowired
    private DictypeService dictypeService;

    @ApiOperation(value="dictype实体新增和修改")
    @RequestMapping(method = RequestMethod.POST,value = "/addOrUpdate")
    public Result addOrUpdate(@RequestBody Dictype dictype){
        Result result = new Result();
        dictypeService.addOrUpdate(dictype);
        return result;
    }


    @ApiOperation(value = "批量删除记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids",required = true,paramType = "query",value = "需要删除的多个id，用逗号,隔开")
    })
    @RequestMapping(method = RequestMethod.POST,value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        dictypeService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }


    @ApiOperation(value = "查询所有记录")
    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result list(String name){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(dictypeService.list(name));
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
        result.setData(dictypeService.page(pageNum,pageSize));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/pageByUsername")
    public Result pageByUsername(Integer pageNum,Integer pageSize,String name){
        Result result = new Result();
        result.setData(dictypeService.pageByUsername(pageNum,pageSize,name));
        return result;
    }
}
