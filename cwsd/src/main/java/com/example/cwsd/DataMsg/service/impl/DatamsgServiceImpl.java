package com.example.cwsd.DataMsg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.third.zhgd.DataMsg.entity.Datamsg;
import com.third.zhgd.DataMsg.mapper.DatamsgMapper;
import com.third.zhgd.DataMsg.service.DatamsgService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
* <p>
    *  服务层实现类
    * </p>
*
* @author Csk
* @since 2024-05-01
*/
@Service
public class DatamsgServiceImpl extends ServiceImpl<DatamsgMapper,Datamsg> implements DatamsgService {

    @Override
    public Boolean addOrUpdate(Datamsg datamsg) {
        if(StringUtils.isBlank(datamsg.getId())){
            //add
            this.save(datamsg);
        }else{
            //update
            this.updateById(datamsg);
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
    public List<Datamsg> list() {
        return this.list();
    }


    @Override
    public Page<Datamsg> page(Integer pageNum,Integer pageSize) {
        Page<Datamsg> page = new Page(pageNum,pageSize);

        return this.page(page);
    }

    @Override
    public Datamsg getNum(String Name) {
        LambdaQueryWrapper<Datamsg> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Datamsg::getDataName, Name);
        Datamsg result = this.getOne(queryWrapper);
        return result;
    }

}