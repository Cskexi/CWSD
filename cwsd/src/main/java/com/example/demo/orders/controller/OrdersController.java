package com.example.demo.orders.controller;

import com.example.demo.springboot2023.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.orders.entity.Orders;
import com.example.demo.orders.service.OrdersService;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 订单表 前端控制器,表的注释
 * </p>
 *
 * @author Csk
 * @since 2024-07-03
 */
@RestController
@RequestMapping("/orders/")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @ApiOperation(value = "订单创建")
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result addOrUpdate(@RequestBody Orders orders) {
        Result result = new Result();

        //交给业务去处理，service
        ordersService.addOrUpdate(orders);

        return result;
    }



    @ApiOperation(value = "批量删除记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids",required = true,paramType = "query",value = "需要删除的多个id，用逗号,隔开")
    })
    @RequestMapping(method = RequestMethod.POST,value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        ordersService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }


    @ApiOperation(value = "查询所有记录")
    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result list(){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(ordersService.list());
        return result;
    }

    @ApiOperation(value = "获取订单列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum",required = true,paramType = "query",value = "当前页码"),
        @ApiImplicitParam(name = "pageSize",required = true,paramType = "query",value = "每页记录数"),
            @ApiImplicitParam(name = "id",required = true,paramType = "query",value = "用户id")
    })
    @RequestMapping(method = RequestMethod.POST,value = "/page")
    public Result page( Integer pageNum,Integer pageSize ,String id){
        Result result = new Result();
        result.success("分页获取成功");
        result.setData(ordersService.page(pageNum,pageSize,id));
        return result;
    }

}
