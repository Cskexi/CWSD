<#--包路径-->
package ${package.Controller};

import com.example.demo.springboot2023.utils.Result;
<#--import com.cn.xmut.edu.springboot2023.utils.Result;-->
<#if swagger2>
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
 </#if>

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
<#--实体-->
import ${package.Entity}.${entity};
<#--service-->
import ${package.Service}.${table.serviceName};

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>


/**
 * <p>
 * ${table.comment!} 前端控制器,表的注释
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
<#--@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")-->
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/")
<#if kotlin>
class ${table.controllerName}
<#else>
public class ${table.controllerName} {
    @Autowired
    private ${table.serviceName} ${table.entityPath}Service;

    <#if swagger2>
    @ApiOperation(value = "保存修改${entity}信息")
    </#if>
    @RequestMapping(method = RequestMethod.POST, value = "/save")
    public Result addOrUpdate(@RequestBody ${entity} ${table.entityPath}) {
        Result result = new Result();

        //交给业务去处理，service
        ${table.entityPath}Service.addOrUpdate(${table.entityPath});

        return result;
    }



    <#if swagger2>
    @ApiOperation(value = "批量删除记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "ids",required = true,paramType = "query",value = "需要删除的多个id，用逗号,隔开")
    })
    </#if>
    @RequestMapping(method = RequestMethod.POST,value = "/deleteByIds")
    public Result deleteByIds(String ids) {
        Result result = new Result();
        ${table.entityPath}Service.deleteByIds(ids);
        result.success("删除成功");
        return result;
    }

<#--    <#if swagger2>-->
<#--    @ApiOperation(value = "批量更新记录的状态")-->
<#--    @ApiImplicitParams({-->
<#--        @ApiImplicitParam(name = "ids",required = true,paramType = "query",value = "需要更新的多个id，用逗号,隔开"),-->
<#--        @ApiImplicitParam(name = "useful",required = true,paramType = "query",value = "是否有用，0-不可用 1-可用")-->
<#--    })-->
<#--    </#if>-->
<#--    @RequestMapping(method = RequestMethod.POST,value = "/updateUsefulByIds")-->
<#--    public Result updateUsefulByIds(String ids,Boolean useful) {-->
<#--        Result result = new Result();-->
<#--        ${table.entityPath}Service.updateUsefulByIds(ids,useful);-->
<#--        result.success("更新成功");-->
<#--        return result;-->
<#--    }-->

    <#if swagger2>
    @ApiOperation(value = "查询所有记录")
    </#if>
    @RequestMapping(method = RequestMethod.POST,value = "/list")
    public Result list(){
        Result result = new Result();
        result.success("获取list成功");
        result.setData(${table.entityPath}Service.list());
        return result;
    }

    <#if swagger2>
    @ApiOperation(value = "分页获取记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum",required = true,paramType = "query",value = "当前页码"),
        @ApiImplicitParam(name = "pageSize",required = true,paramType = "query",value = "每页记录数")
    })
    </#if>
    @RequestMapping(method = RequestMethod.POST,value = "/page")
    public Result page( Integer pageNum,Integer pageSize ){
        Result result = new Result();
        result.success("分页获取成功");
        result.setData(${table.entityPath}Service.page(pageNum,pageSize));
        return result;
    }

<#--    <#if swagger2>-->
<#--    @ApiOperation(value = "通过id获取其对应的实体")-->
<#--        @ApiImplicitParams({-->
<#--        @ApiImplicitParam(name = "id",required = true,paramType = "query",value = "需要查询的id")-->
<#--    })-->
<#--    </#if>-->
<#--    @RequestMapping(method = RequestMethod.GET,value = "/getById")-->
<#--    public Result getById(String id) {-->
<#--        Result result = new Result();-->
<#--        ${entity} ${table.entityPath} = ${table.entityPath}Service.myGetById(id);-->
<#--        result.setData(${table.entityPath});-->
<#--        result.success("成功得到");-->
<#--        return result;-->
<#--    }-->
}
</#if>
