package com.example.cwsd.Equipment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.cwsd.Equipment.entity.Equipment;
import com.example.cwsd.Equipment.mapper.EquipmentMapper;
import com.example.cwsd.Equipment.service.EquipmentService;
import com.example.cwsd.other.utils.ConstantsUtils;
import com.example.cwsd.other.utils.DateTool;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
* <p>
    * 用户表 服务层实现类
    * </p>
*
* @author Csk
* @since 2024-05-21
*/
@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper,Equipment> implements EquipmentService {

    @Override
    public Boolean addOrUpdate(Equipment equipment) {
        if(StringUtils.isBlank(equipment.getId())){
            //add
            equipment.setCreateTime(DateTool.getCurrTime());
            this.save(equipment);
        }else{
            //update
            this.updateById(equipment);
        }
        return true;
    }

    @Override
    public void deleteByIds(String ids) {

        String[] aryIds = ids.split(",");
        for(String id :aryIds){
            Equipment monitor = this.getById(id);
            monitor.setDelFlag(ConstantsUtils.GL_DEL);
            this.updateById(monitor);

        }
    }


    @Override
    public List<Equipment> list(String name, String address) {
        QueryWrapper<Equipment> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Equipment::getDelFlag, ConstantsUtils.GL_NORMAL);
        if(StringUtils.isNotBlank(name)){
            queryWrapper.lambda().like(Equipment::getName,name);
        }
        if(StringUtils.isNotBlank(address)){
            queryWrapper.lambda().like(Equipment::getAddress,address);
        }
        List<Equipment> list =this.list(queryWrapper);
        return list;
    }


    @Override
    public Page<Equipment> page(Integer pageNum,Integer pageSize) {
        Page<Equipment> page = new Page(pageNum,pageSize);

        return this.page(page);
    }

    @Override
    public int updateValueByRandom() {
            // 假设我们要更新的字段是value，并且我们有一个随机值生成的逻辑
        Random random = new Random();

        QueryWrapper<Equipment> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Equipment::getDelFlag, ConstantsUtils.GL_NORMAL);

        List<Equipment> list =this.list(queryWrapper);
        for (int i = 0; i < list.size(); i++) {
            Equipment equipment = list.get(i);
            // 在这里处理equipment对象
            int lowerBound = Integer.parseInt(equipment.getStart());
            int upperBound = Integer.parseInt(equipment.getEnd());
            int randomValue = lowerBound + random.nextInt(upperBound - lowerBound + 1);
            equipment.setValue(""+randomValue);
            this.updateById(equipment);
        }
        return list.size();
    }

}