package com.example.demo.chapter4.controller;

import com.example.demo.chapter4.entity.Dictype;
import com.example.demo.chapter4.service.DictypeService;
import com.example.demo.springboot2023.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("chapter4/dictype")
@Api(tags="dictype实体的Controller")
public class DictypeController {

    @Autowired
    private DictypeService dictypeService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ApiOperation(value="获取所以字典")
    @RequestMapping(method = RequestMethod.GET,value = "/getAllMap")
    public Result getAllMap(){
        Result result = new Result();
        result.setData(dictypeService.getAllMap());
        return result;
    }


    @ApiOperation(value="dictype实体新增和修改")
    @RequestMapping(method = RequestMethod.POST,value = "/addOrUpdate")
    public Result addOrUpdate(@RequestBody Dictype dictype){
        Result result = new Result();

        if(dictypeService.exist(dictype)){
            result.fail("书籍名已存在");
            return result;
        }
        dictypeService.addOrUpdate(dictype);
        return result;
    }

    @ApiOperation(value="获得dictype的list列表信息支持name搜索")
    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result list(String name){
        Result result = new Result();
        result.setData(dictypeService.list(name));
        return result;
    }

    @ApiOperation(value="通过ids，批量删除dictype记录,ids中根据;进行分割")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ids",value="id",paramType = "query",required = true),
            })
    @RequestMapping(method = RequestMethod.POST,value = "/deleteByIds")
    public Result deleteByIds(String ids){
        Result result = new Result();
        dictypeService.deleteById(ids);
        return result;
    }
    @ApiOperation(value="获得dictype的分页信息，支持name搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageNum",value="当前页码",paramType = "query",required = true),
            @ApiImplicitParam(name="pageSize",value="每页显示条数",paramType = "query",required = true),
            @ApiImplicitParam(name="name",value="搜索关键字",paramType = "query")
    })
    @RequestMapping(method = RequestMethod.POST,value = "/page")
    public Result page(Integer pageNum,Integer pageSize,String name,String username){
        //trace  debug info warn < error 日志等级

        Result result = new Result();
        result.setData(dictypeService.page(pageNum,pageSize,name,username));
        return result;
    }



    @RequestMapping(method = RequestMethod.POST,value = "/pageByUsername")
    public Result pageByUsername(Integer pageNum,Integer pageSize,String name,String username){
        Result result = new Result();
//        logger.trace("this is trace");
//        logger.debug("this is debug");
//        logger.info("this is info");
//        logger.warn("this is warn");
//        logger.error("this is error");
        result.setData(dictypeService.pageByUsername(pageNum,pageSize,name,username));
        return result;
    }
}
