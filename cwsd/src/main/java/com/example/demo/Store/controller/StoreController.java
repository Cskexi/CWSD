package com.example.demo.Store.controller;

import com.example.demo.springboot2023.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.Store.entity.Store;
import com.example.demo.Store.service.StoreService;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 字典类型表 前端控制器,表的注释
 * </p>
 *
 * @author Csk
 * @since 2023-12-18
 */
@RestController
@RequestMapping("/Store")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @ApiOperation(value = "保存修改Store信息")
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result addOrUpdate(@RequestBody Store store) {
        Result result = new Result();
        //交给业务去处理，service
        storeService.addOrUpdate(store);
        return result;
    }



    @ApiOperation(value = "批量删除记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids",required = true,paramType = "query",value = "需要删除的多个id，用逗号,隔开")
    })
    @RequestMapping(method = RequestMethod.POST,value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        storeService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }


    @ApiOperation(value = "查询所有记录")
    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result list(){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(storeService.list());
        return result;
    }

    @ApiOperation(value = "分页获取记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum",required = true,paramType = "query",value = "当前页码"),
        @ApiImplicitParam(name = "pageSize",required = true,paramType = "query",value = "每页记录数")
    })
    @RequestMapping(method = RequestMethod.POST,value = "/page")
    public Result page( Integer pageNum,Integer pageSize,String userId ){
        Result result = new Result();
        result.success("分页获取成功");
        result.setData(storeService.page(pageNum,pageSize,userId));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/page2")
    public Result page2(Integer pageNum,Integer pageSize,String name){
        Result result = new Result();
        result.setData(storeService.page2(pageNum,pageSize,name));
        //result.setData(storeService.page(pageNum,pageSize));
        result.success("成功得到");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/getMessage")
    public Result getMessage(String id){
        Result result = new Result();
        result.setData(storeService.getById(id));
        result.success("成功得到商店信息");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/getStoreByUserId")
    public Result getStoreByUserId(String userId){
        Result result = new Result();

        result.setData(storeService.GetByUserId(userId));

        result.success("成功得到商店信息");
        return result;
    }

}
