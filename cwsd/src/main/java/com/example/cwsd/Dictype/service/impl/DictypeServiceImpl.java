package com.example.cwsd.Dictype.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.third.zhgd.Dictype.entity.Dictype;
import com.third.zhgd.Dictype.mapper.DictypeMapper;
import com.third.zhgd.Dictype.service.DictypeService;
import com.third.zhgd.other.utils.ConstantsUtils;
import com.third.zhgd.other.utils.DateTool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
* <p>
    * 字典类型表 服务层实现类
    * </p>
*
* @author Csk
* @since 2024-05-14
*/
@Service
public class DictypeServiceImpl extends ServiceImpl<DictypeMapper,Dictype> implements DictypeService {

    @Override
    public Boolean addOrUpdate(Dictype dictype) {
        if(StringUtils.isBlank(dictype.getId())){
            //add
            dictype.setCreateTime(DateTool.getCurrTime());
            this.save(dictype);
        }else{
            //update
            this.updateById(dictype);
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
    public List<Dictype> list(String name)
    {
        QueryWrapper<Dictype> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Dictype::getDelFlag, ConstantsUtils.GL_NORMAL);
        queryWrapper.lambda().orderByDesc(Dictype::getCreateTime);
        if(StringUtils.isNotBlank(name)){
            queryWrapper.lambda().like(Dictype::getName,name);
        }

        List<Dictype> list =this.list(queryWrapper);
        return list;
    }


    @Override
    public Page<Dictype> page(Integer pageNum,Integer pageSize) {
        Page<Dictype> page = new Page(pageNum,pageSize);

        return this.page(page);
    }

    @Override
    public Page<Dictype> pageByUsername(Integer pageNum, Integer pageSize, String name) {
        Page<Dictype> page = new Page<>(pageNum, pageSize);
        return baseMapper.getPageByUsername(page,name);
    }

}