package com.example.demo.aop.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.aop.entity.SysLog;
import com.example.demo.aop.mapper.SysLogMapper;
import com.example.demo.aop.service.SysLogService;
import com.example.demo.springboot2023.utils.DateTool;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
/**
* <p>
    * 操作日志表 服务层实现类
    * </p>
*
* @author Chrimer
* @since 2023-12-08
*/
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper,SysLog> implements SysLogService {


    @Override
    public Boolean addOrUpdate(SysLog sysLog) {
        if(StringUtils.isBlank(sysLog.getId())){
            //add
            sysLog.setCreateTime(DateTool.getCurrTime());
            this.save(sysLog);
        }else{
            //update
            this.updateById(sysLog);
        }
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
            listIds.add(id);
        }
        this.removeByIds(listIds);
    }


    @Override
    public List<SysLog> list() {
        return this.list();
    }


    @Override
    public Page<SysLog> page(Integer pageNum, Integer pageSize) {
        Page<SysLog> page = new Page(pageNum,pageSize);

        return this.page(page);
    }

    @Override
    public SysLog myGetById(String id) {
        return this.getById(id);
    }
}