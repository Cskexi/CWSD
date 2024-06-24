package com.example.cwsd.Dic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.third.zhgd.Dic.entity.Dic;
import com.third.zhgd.Dic.mapper.DicMapper;
import com.third.zhgd.Dic.service.DicService;
import com.third.zhgd.Dictype.entity.Dictype;
import com.third.zhgd.Dictype.service.DictypeService;
import com.third.zhgd.other.utils.ConstantsUtils;
import com.third.zhgd.other.utils.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
* <p>
    * 字典表  服务层实现类
    * </p>
*
* @author Csk
* @since 2024-05-14
*/
@Service
public class DicServiceImpl extends ServiceImpl<DicMapper,Dic> implements DicService {

    @Autowired
    private DictypeService dictypeService;

    @Override
    public Boolean addOrUpdate(Dic dic) {
        if(StringUtils.isBlank(dic.getId())){
            //add
            dic.setCreateTime(DateTool.getCurrTime());
            this.save(dic);
        }else{
            //update
            this.updateById(dic);
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
    public List<Dic> list() {
        return this.list();
    }


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