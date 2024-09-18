package com.example.demo.reviews.controller;

import com.example.demo.springboot2023.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.reviews.entity.Reviews;
import com.example.demo.reviews.service.ReviewsService;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 评论表 前端控制器,表的注释
 * </p>
 *
 * @author Csk
 * @since 2024-08-22
 */
@RestController
@RequestMapping("/reviews/")
public class ReviewsController {
    @Autowired
    private ReviewsService reviewsService;

    @ApiOperation(value = "保存修改Reviews信息")
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result addOrUpdate(@RequestBody Reviews reviews) {
        Result result = new Result();

        //交给业务去处理，service
        result.setData(reviewsService.addOrUpdate(reviews));

        return result;
    }



    @ApiOperation(value = "批量删除记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids",required = true,paramType = "query",value = "需要删除的多个id，用逗号,隔开")
    })
    @RequestMapping(method = RequestMethod.POST,value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        reviewsService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }


    @ApiOperation(value = "查询所有记录")
    @RequestMapping(method = RequestMethod.GET,value = "/list")
    public Result list(String productId){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(reviewsService.list(productId));
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
        result.setData(reviewsService.page(pageNum,pageSize));
        return result;
    }

}
