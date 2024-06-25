package com.example.demo.chapter4.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.chapter4.entity.Dic;
import com.example.demo.chapter4.entity.Dic;
import com.example.demo.chapter4.entity.Dictype;
import com.example.demo.chapter4.mapper.DicMapper;
import com.example.demo.chapter4.mapper.DicMapper;
import com.example.demo.chapter4.service.DicService;
import com.example.demo.chapter4.service.DictypeService;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.springboot2023.utils.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DicServiceImpl extends ServiceImpl<DicMapper, Dic> implements DicService {
    @Autowired
    private DictypeService dictypeService;
    @Override
    public void addOrUpdate(Dic dic){
        if(StringUtils.isBlank(dic.getId())){
            dic.setCreateTime(DateTool.getCurrTime());
            this.save(dic);
        }else {
            this.updateById(dic);
        }
    }

    @Override
    public boolean exist(Dic dic){
        LambdaQueryWrapper<Dic> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Dic::getName,dic.getName());
        Dic dicExit = this.getOne(lambdaQueryWrapper);
        if(dicExit!=null&& !dicExit.getId().equals(dic.getId())){
            return true;
        }
        return false;
    }

    @Override
    public List<Dic> list(String name) {

        QueryWrapper<Dic> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(name)){
            queryWrapper.lambda().like(Dic::getName,name);
        }
        queryWrapper.lambda().eq(Dic::getDelFlag,ConstantsUtils.GL_NORMAL);
        //queryWrapper.like("name",name);
        queryWrapper.lambda().orderByDesc(Dic::getCreateTime);
        //queryWrapper.orderByDesc("create_time");
        List<Dic> list =this.list(queryWrapper);
        return list;
    }

    @Override
    public void deleteById(String ids) {
        String[] aryIds = ids.split(",");
//        List<String> listIds = Arrays.asList(aryIds);
//        this.removeByIds(listIds);
        for(String id :aryIds){
            Dic dic = this.getById(id);
            dic.setDelFlag(ConstantsUtils.GL_DEL);
            this.updateById(dic);
        }
    }

//    @Override
//    public Page<Dic> page(Integer pageNum,Integer pageSize,String name) {
//        Page<Dic> page =new Page<>(pageNum,pageSize);
//
//        LambdaQueryWrapper<Dic> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//
//        if(StringUtils.isNotBlank(name)){
//            lambdaQueryWrapper.like(Dic::getName,name);
//        }
//
//        lambdaQueryWrapper.eq(Dic::getDelFlag,ConstantsUtils.GL_NORMAL);
//        lambdaQueryWrapper.orderByDesc(Dic::getCreateTime);
//
//
//        return this.page(page,lambdaQueryWrapper);
//    }
    @Override
    public Page<Dic> page(Integer pageNum, Integer pageSize, String name,String dictypeId) {
        Page<Dic> page = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<Dic> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            lambdaQueryWrapper.like(Dic::getName, name);
        }
        if (StringUtils.isNotBlank(dictypeId)) {
            lambdaQueryWrapper.like(Dic::getDictypeId, dictypeId);
        }

        lambdaQueryWrapper.eq(Dic::getDelFlag, ConstantsUtils.GL_NORMAL);
        lambdaQueryWrapper.orderByDesc(Dic::getCreateTime);

        // 执行分页查询
        page = this.page(page, lambdaQueryWrapper);

        // 打印一下总记录数，确保它是正确的
        //System.out.println("Total records: " + page.getTotal());

        //Dic dictype Id => dictype
        //Dic.etc.put(key,dictype)

        for(Dic dic:page.getRecords()){//分页信息记录数
            Dictype dictype = dictypeService.getById(dic.getDictypeId());
            //把dectype放入DIC这个ENTITY的ETC里头
            dic.put("dictype",dictype);
            //dic.put("key","2023");
        }


        return page;
    }

}
