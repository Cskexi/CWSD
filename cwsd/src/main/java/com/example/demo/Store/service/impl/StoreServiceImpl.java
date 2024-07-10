package com.example.demo.Store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Goods.entity.Goods;
import com.example.demo.Store.vo.StoreUserVO;
import com.example.demo.chapter3.entity.User;
import com.example.demo.chapter3.service.UserService;
import com.example.demo.products.entity.Products;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.Store.mapper.StoreMapper;
import com.example.demo.Store.entity.Store;
import com.example.demo.Store.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
/**
* <p>
    * 字典类型表 服务层实现类
    * </p>
*
* @author Csk
* @since 2023-12-18
*/
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper,Store> implements StoreService {

    @Autowired
    private UserService userService;

    @Override
    public Boolean addOrUpdate(Store store) {
        if(StringUtils.isBlank(store.getId())){
            //add
            store.setCreateTime(DateTool.getCurrTime());
            this.save(store);
        }else{
            //update
            this.updateById(store);
        }
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        String[] aryIds = ids.split(",");
        for(String id :aryIds){
            Store store = this.getById(id);
            store.setDelFlag(ConstantsUtils.GL_DEL);
            this.updateById(store);
        }
    }


    @Override
    public List<Store> list() {
        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Store::getDelFlag,ConstantsUtils.GL_NORMAL);
        queryWrapper.lambda().orderByDesc(Store::getCreateTime);
        List<Store> list =this.list(queryWrapper);
        return list;
    }



    @Override
    public Page<Store> page(Integer pageNum, Integer pageSize, String userId) {
        Page<Store> page = new Page(pageNum,pageSize);

        LambdaQueryWrapper<Store> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(userId)) {
            lambdaQueryWrapper.like(Store::getUserId, userId);
        }
        lambdaQueryWrapper.eq(Store::getDelFlag, ConstantsUtils.GL_NORMAL);
        lambdaQueryWrapper.orderByDesc(Store::getCreateTime);
        // 执行分页查询
        page = this.page(page, lambdaQueryWrapper);
        for(Store Store:page.getRecords()){//分页信息记录数
            User user = userService.getById(Store.getUserId());
            Store.put("user",user);
        }
        return page;
    }
    @Override
    public Page<StoreUserVO> page2(Integer pageNum, Integer pageSize,String name) {
        
        
        Page<StoreUserVO> page = new Page<>(pageNum, pageSize);
        page =baseMapper.getPage(page,name);
        return page;

    }
    @Override
    public Store myGetById(String id) {
        return this.getById(id);
    }

    @Override
    public Store GetByUserId(String userId) {
        QueryWrapper<Store> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(userId)){
            queryWrapper.lambda().like(Store::getUserId,userId);
        }
        queryWrapper.lambda().eq(Store::getDelFlag,ConstantsUtils.GL_NORMAL);
        queryWrapper.lambda().orderByDesc(Store::getCreateTime);

        Store store = this.getOne(queryWrapper, false);

        return store;
    }

}