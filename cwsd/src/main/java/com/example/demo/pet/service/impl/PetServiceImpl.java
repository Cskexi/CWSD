package com.example.demo.pet.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.pet.mapper.PetMapper;
import com.example.demo.pet.entity.Pet;
import com.example.demo.pet.service.PetService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
/**
* <p>
    * 商品表 服务层实现类
    * </p>
*
* @author Csk
* @since 2024-06-25
*/
@Service
public class PetServiceImpl extends ServiceImpl<PetMapper,Pet> implements PetService {

    @Override
    public Boolean addOrUpdate(Pet pet) {
        if(StringUtils.isBlank(pet.getId())){
            //add
            pet.setCreateTime(DateTool.getCurrTime());
            this.save(pet);
        }else{
            //update
            this.updateById(pet);
        }
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
        Pet pet = this.getById(id);
        pet.setDelFlag(ConstantsUtils.GL_DEL);
        this.updateById(pet);
        }

    }


    @Override
    public List<Pet> list() {
        return this.list();
    }


    @Override
    public Page<Pet> page(Integer pageNum,Integer pageSize) {
        Page<Pet> page = new Page(pageNum,pageSize);
        LambdaQueryWrapper<Pet> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Pet::getDelFlag, ConstantsUtils.GL_NORMAL);
        lambdaQueryWrapper.orderByDesc(Pet::getCreateTime);
        page = this.page(page, lambdaQueryWrapper);
        return page;
    }

}