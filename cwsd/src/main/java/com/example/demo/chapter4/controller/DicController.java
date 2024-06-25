package com.example.demo.chapter4.controller;

import com.example.demo.chapter4.entity.Dic;
import com.example.demo.chapter4.service.DicService;
import com.example.demo.springboot2023.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("chapter4/dic")
@Api(tags="dic实体的Controller")
public class DicController {

    @Autowired
    private DicService dicService;

    @ApiOperation(value="dic实体新增和修改")
    @RequestMapping(method = RequestMethod.POST,value = "/addOrUpdate")
    public Result addOrUpdate(@RequestBody Dic dic){
        Result result = new Result();

        if(dicService.exist(dic)){
            result.fail("书籍名已存在");
            return result;
        }
        dicService.addOrUpdate(dic);
        return result;
    }

    @ApiOperation(value="获得dic的list列表信息支持name搜索")
    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result list(String name){
        Result result = new Result();
        result.setData(dicService.list(name));
        return result;
    }

    @ApiOperation(value="通过ids，批量删除dic记录,ids中根据;进行分割")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ids",value="id",paramType = "query",required = true),
            })
    @RequestMapping(method = RequestMethod.POST,value = "/deleteByIds")
    public Result deleteByIds(String ids){
        Result result = new Result();
        dicService.deleteById(ids);
        return result;
    }
    @ApiOperation(value="获得dic的分页信息，支持name搜索")
    @RequestMapping(method = RequestMethod.POST,value = "/page")
    public Result page(Integer pageNum,Integer pageSize,String name ,String dictypeId){
        Result result = new Result();
        result.setData(dicService.page(pageNum,pageSize,name,dictypeId));
        return result;
    }
}
