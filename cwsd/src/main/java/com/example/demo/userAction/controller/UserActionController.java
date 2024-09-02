package com.example.demo.userAction.controller;

import com.example.demo.chapter3.entity.User;
import com.example.demo.springboot2023.utils.Result;
import com.example.demo.userAction.dto.UserActionDto;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.userAction.entity.UserAction;
import com.example.demo.userAction.service.UserActionService;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 用户操作记录表 前端控制器,表的注释
 * </p>
 *
 * @author Csk
 * @since 2024-08-22
 */
@RestController
@RequestMapping("/userAction/")
public class UserActionController {
    @Autowired
    private UserActionService userActionService;

    @ApiOperation(value = "保存修改UserAction信息")
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result addOrUpdate(@RequestBody UserAction userAction) {
        Result result = new Result();

        //交给业务去处理，service
        userActionService.addOrUpdate(userAction);

        return result;
    }



    @ApiOperation(value = "批量删除记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids",required = true,paramType = "query",value = "需要删除的多个id，用逗号,隔开")
    })
    @RequestMapping(method = RequestMethod.POST,value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        userActionService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }


    @ApiOperation(value = "查询所有记录")
    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result list(){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(userActionService.list());
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
        result.setData(userActionService.page(pageNum,pageSize));
        return result;
    }

    @ApiOperation(value = "分页获取记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum",required = true,paramType = "query",value = "当前页码"),
            @ApiImplicitParam(name = "pageSize",required = true,paramType = "query",value = "每页记录数")
    })
    @RequestMapping(method = RequestMethod.POST,value = "/page2")
    public Result page2(@RequestBody UserActionDto userActionDto){
        Result result = new Result();
        result.success("分页获取成功");
        result.setData(userActionService.page2(userActionDto));
        return result;
    }

    @RequestMapping(value = "/getRecommendList", method = RequestMethod.POST, produces = "application/json; utf-8")
    public Result getRecommendUserList(@RequestBody User user) {
        Result result = new Result();

        result.setData(userActionService.recommendProduct(user));
        result.success("推荐商品获取成功");
        return result;
    }
}
