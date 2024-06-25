package ${package.ServiceImpl};

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
<#--import com.cn.xmut.edu.springboot2023.utils.DateTool;-->
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import ${package.Mapper}.${table.mapperName};
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
/**
* <p>
    * ${table.comment!} 服务层实现类
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Service
public class ${table.serviceImplName} extends ServiceImpl<${table.mapperName},${entity}> implements ${table.serviceName} {

    @Override
    public Boolean addOrUpdate(${entity} ${table.entityPath}) {
        if(StringUtils.isBlank(${table.entityPath}.getId())){
            //add
            ${table.entityPath}.setCreateTime(DateTool.getCurrTime());
            this.save(${table.entityPath});
        }else{
            //update
            this.updateById(${table.entityPath});
        }
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
        ${entity} ${table.entityPath} = this.getById(id);
        ${table.entityPath}.setDelFlag(ConstantsUtils.GL_DEL);
        this.updateById(${table.entityPath});
<#--            listIds.add(id);-->
        }
<#--        this.removeByIds(listIds);-->

    }

<#--    @Override-->
<#--    public void updateUsefulByIds(String ids, Boolean useful) {-->
<#--        //ids  若干个id 用逗号隔开-->
<#--        String[] aryIds = ids.split(",");-->
<#--        for(String id: aryIds){-->

<#--            //修改的value-->
<#--            ${entity} ${table.entityPath} = new ${entity}();-->
<#--            ${table.entityPath}.setUseful(useful);-->

<#--            //修改的条件-->
<#--            UpdateWrapper<${entity}> updateWrapper = new UpdateWrapper<>();-->
<#--            updateWrapper.eq("id",id);-->

<#--            //执行-->
<#--            this.update(${table.entityPath},updateWrapper);-->
<#--        }-->
<#--    }-->

    @Override
    public List<${entity}> list() {
        return this.list();
    }


    @Override
    public Page<${entity}> page(Integer pageNum,Integer pageSize) {
        Page<${entity}> page = new Page(pageNum,pageSize);
        LambdaQueryWrapper<${entity}> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(${entity}::getDelFlag, ConstantsUtils.GL_NORMAL);
        lambdaQueryWrapper.orderByDesc(${entity}::getCreateTime);
        page = this.page(page, lambdaQueryWrapper);
        return page;
<#--        return this.page(page);-->
    }

<#--    @Override-->
<#--    public ${entity} myGetById(String id) {-->
<#--        return this.getById(id);-->
<#--    }-->
}