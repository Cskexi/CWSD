package com.example.demo.serve.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.serve.mapper.ServeMapper;
import com.example.demo.serve.entity.Serve;
import com.example.demo.serve.service.ServeService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
/**
* <p>
    * 服务表 服务层实现类
    * </p>
*
* @author Csk
* @since 2024-06-25
*/
@Service
public class ServeServiceImpl extends ServiceImpl<ServeMapper,Serve> implements ServeService {

    @Override
    public Boolean addOrUpdate(Serve serve) {
        if(StringUtils.isBlank(serve.getId())){
            //add
            serve.setCreateTime(DateTool.getCurrTime());
            this.save(serve);
        }else{
            //update
            this.updateById(serve);
        }
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
        Serve serve = this.getById(id);
        serve.setDelFlag(ConstantsUtils.GL_DEL);
        this.updateById(serve);
        }

    }


    @Override
    public List<Serve> list() {
        return this.list();
    }


    @Override
    public Page<Serve> page(Integer pageNum,Integer pageSize) {
        Page<Serve> page = new Page(pageNum,pageSize);
        LambdaQueryWrapper<Serve> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Serve::getDelFlag, ConstantsUtils.GL_NORMAL);
        lambdaQueryWrapper.orderByDesc(Serve::getCreateTime);
        page = this.page(page, lambdaQueryWrapper);
        return page;
    }

}