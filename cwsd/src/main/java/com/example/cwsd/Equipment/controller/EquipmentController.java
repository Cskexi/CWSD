package com.example.cwsd.Equipment.controller;

import com.third.zhgd.Equipment.entity.Equipment;
import com.third.zhgd.Equipment.service.EquipmentService;
import com.third.zhgd.other.utils.Result;
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
 * 用户表 前端控制器,表的注释
 * </p>
 *
 * @author Csk
 * @since 2024-05-21
 */
@RestController
@RequestMapping("/Equipment/")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @ApiOperation(value = "保存修改Equipment信息")
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result addOrUpdate(@RequestBody Equipment equipment) {
        Result result = new Result();

        //交给业务去处理，service
        equipmentService.addOrUpdate(equipment);

        return result;
    }

    @RequestMapping(value = "/dataChange", method = RequestMethod.POST)
    public Result dataChange() {
        Result result = new Result();
        try {
            // 调用服务层的方法进行数据更新
            int count = equipmentService.updateValueByRandom();
            result.success("成功更新了" + count + "条数据的随机值");
        } catch (Exception e) {
            result.fail("数据变更失败");
        }
        return result;
    }



    @ApiOperation(value = "批量删除记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids",required = true,paramType = "query",value = "需要删除的多个id，用逗号,隔开")
    })
    @RequestMapping(method = RequestMethod.POST,value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        equipmentService.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }


    @ApiOperation(value = "查询所有记录")
    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result list(String name,String address){
        Result result = new Result();
        result.success("获取list成功");
        result.setData( equipmentService.list(name,address));
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
        result.setData(equipmentService.page(pageNum,pageSize));
        return result;
    }

}
