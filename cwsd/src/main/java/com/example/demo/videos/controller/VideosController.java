package com.example.demo.videos.controller;

import com.example.demo.springboot2023.utils.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.demo.videos.entity.Videos;
import com.example.demo.videos.service.VideosService;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 视频表 前端控制器,表的注释
 * </p>
 *
 * @author Csk
 * @since 2024-07-08
 */
@RestController
@RequestMapping("/videos/")
public class VideosController {
    @Autowired
    private VideosService videosService;

    @ApiOperation(value = "保存修改Videos信息")
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result addOrUpdate(@RequestBody Videos videos) {
        Result result = new Result();

        //交给业务去处理，service
        videosService.addOrUpdate(videos);
        return result;
    }


    @ApiOperation(value = "批量删除记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids",required = true,paramType = "query",value = "需要删除的多个id，用逗号,隔开")
    })
    @RequestMapping(method = RequestMethod.POST,value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        videosService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }


    @ApiOperation(value = "查询所有记录")
    @RequestMapping(method = RequestMethod.GET,value = "/list")
    public Result list(String productId){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(videosService.list(productId));
        return result;
    }

    @ApiOperation(value = "分页获取记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum",required = true,paramType = "query",value = "当前页码"),
        @ApiImplicitParam(name = "pageSize",required = true,paramType = "query",value = "每页记录数")
    })
    @RequestMapping(method = RequestMethod.POST,value = "/page")
    public Result page( Integer pageNum,Integer pageSize,String categoryId ){
        Result result = new Result();
        result.success("分页获取成功");
        result.setData(videosService.page(pageNum,pageSize,categoryId));
        return result;
    }

    @ApiOperation(value = "查询所有记录")
    @RequestMapping(method = RequestMethod.GET,value = "/addCount")
    public Result addCount(String id){
        Result result = new Result();
        result.success("获取list成功");
        Videos videos = videosService.getById(id);
        videos.setViewsCount(videos.getViewsCount()+1);
        videosService.updateById(videos);
        return result;
    }


}
