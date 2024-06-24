package com.example.cwsd.Apply.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.third.zhgd.Apply.entity.Apply;
import com.third.zhgd.Apply.mapper.ApplyMapper;
import com.third.zhgd.Apply.service.ApplyService;
import com.third.zhgd.other.utils.DateTool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
* <p>
    *  服务层实现类
    * </p>
*
* @author Csk
* @since 2024-05-03
*/
@Service
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper,Apply> implements ApplyService {

    @Override
    public Boolean addOrUpdate(Apply apply) {
        if(StringUtils.isBlank(apply.getId())){
            //add
            apply.setCreateTime(DateTool.getCurrTime());
            apply.setState(0);
            this.save(apply);
        }else{
            //update
            this.updateById(apply);
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
    public List<Apply> list() {
        return this.list();
    }


    @Override
    public Page<Apply> page(Integer pageNum,Integer pageSize) {
        Page<Apply> page = new Page(pageNum,pageSize);

        return this.page(page);
    }

    @Override
    public Page<Apply> myPage(Integer pageNum, Integer pageSize, String userId) {
        Page<Apply> page =new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<Apply> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(Apply::getUserId,userId);
        lambdaQueryWrapper.orderByDesc(Apply::getCreateTime);
        return this.page(page,lambdaQueryWrapper);
    }

}