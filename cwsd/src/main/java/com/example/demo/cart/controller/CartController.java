package com.example.demo.cart.controller;

import com.example.demo.springboot2023.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.service.CartService;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 购物车表 前端控制器,表的注释
 * </p>
 *
 * @author Csk
 * @since 2024-07-03
 */
@RestController
@RequestMapping("/cart/")
public class CartController {
    @Autowired
    private CartService cartService;

    @ApiOperation(value = "添加或更新购物车")
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result addOrUpdate(@RequestBody Cart cart) {
        Result result = new Result();
        //交给业务去处理，service
        cartService.addOrUpdate(cart);

        return result;
    }



    @ApiOperation(value = "伤处购物车商品")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids",required = true,paramType = "query",value = "需要删除的多个id，用逗号,隔开")
    })
    @RequestMapping(method = RequestMethod.POST,value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        cartService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }


    @ApiOperation(value = "查询所有记录")
    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result list(){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(cartService.list());
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
        result.setData(cartService.page(pageNum,pageSize));
        return result;
    }

    @ApiOperation(value = "获取购物车内容")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",required = true,paramType = "query",value = "用户id")
    })
    @RequestMapping(method = RequestMethod.POST,value = "/getById")
    public Result getById(String id){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(cartService.getById(id));
        return result;
    }

}
