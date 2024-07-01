package com.example.demo.navigation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.chapter4.entity.Dictype;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.navigation.mapper.NavigationMapper;
import com.example.demo.navigation.entity.Navigation;
import com.example.demo.navigation.service.NavigationService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
/**
* <p>
    * 地址表 服务层实现类
    * </p>
*
* @author Csk
* @since 2024-06-30
*/
@Service
public class NavigationServiceImpl extends ServiceImpl<NavigationMapper,Navigation> implements NavigationService {

    @Override
    public Boolean addOrUpdate(Navigation navigation) {
        if(StringUtils.isBlank(navigation.getId())){
            //add
            navigation.setCreateTime(DateTool.getCurrTime());
            this.save(navigation);
        }else{
            //update
            this.updateById(navigation);
        }
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
        Navigation navigation = this.getById(id);
        navigation.setDelFlag(ConstantsUtils.GL_DEL);
        this.updateById(navigation);
        }

    }


    @Override
    public List<Navigation> list() {
        QueryWrapper<Navigation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Navigation::getDelFlag,ConstantsUtils.GL_NORMAL);
        queryWrapper.lambda().orderByDesc(Navigation::getCreateTime);
        List<Navigation> list =this.list(queryWrapper);
        return list;
    }


    @Override
    public Page<Navigation> page(Integer pageNum,Integer pageSize) {
        Page<Navigation> page = new Page(pageNum,pageSize);
        LambdaQueryWrapper<Navigation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Navigation::getDelFlag, ConstantsUtils.GL_NORMAL);
        lambdaQueryWrapper.orderByDesc(Navigation::getCreateTime);
        page = this.page(page, lambdaQueryWrapper);
        return page;
    }

}