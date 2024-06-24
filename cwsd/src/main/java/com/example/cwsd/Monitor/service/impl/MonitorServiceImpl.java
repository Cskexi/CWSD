package com.example.cwsd.Monitor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.cwsd.Monitor.entity.Monitor;
import com.example.cwsd.Monitor.mapper.MonitorMapper;
import com.example.cwsd.Monitor.service.MonitorService;
import com.example.cwsd.other.utils.ConstantsUtils;
import org.springframework.stereotype.Service;

import java.util.List;
/**
* <p>
    *  服务层实现类
    * </p>
*
* @author Csk
* @since 2024-05-14
*/
@Service
public class MonitorServiceImpl extends ServiceImpl<MonitorMapper,Monitor> implements MonitorService {

    @Override
    public Boolean addOrUpdate(Monitor monitor) {
        if(StringUtils.isBlank(monitor.getId())){
            //add
            this.save(monitor);
        }else{
            //update
            this.updateById(monitor);
        }
        return true;
    }

    @Override
    public void deleteByIds(String ids) {

        String[] aryIds = ids.split(",");
        for(String id :aryIds){
            Monitor monitor = this.getById(id);
            monitor.setDelFlag(ConstantsUtils.GL_DEL);
            this.updateById(monitor);

        }
    }


    @Override
    public List<Monitor> list(String name,String address) {
        QueryWrapper<Monitor> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Monitor::getDelFlag, ConstantsUtils.GL_NORMAL);
        if(StringUtils.isNotBlank(name)){
            queryWrapper.lambda().like(Monitor::getName,name);
        }
        if(StringUtils.isNotBlank(address)){
            queryWrapper.lambda().like(Monitor::getAddress,address);
        }
        List<Monitor> list =this.list(queryWrapper);
        return list;
    }


    @Override
    public Page<Monitor> page(Integer pageNum,Integer pageSize) {
        Page<Monitor> page = new Page(pageNum,pageSize);

        return this.page(page);
    }

}