package com.example.demo.Goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Store.entity.Store;
import com.example.demo.Store.service.StoreService;
import com.example.demo.Store.vo.StoreUserVO;
import com.example.demo.chapter4.entity.Dic;
import com.example.demo.chapter4.entity.Dictype;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.Goods.mapper.GoodsMapper;
import com.example.demo.Goods.entity.Goods;
import com.example.demo.Goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
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
* @since 2023-12-19
*/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper,Goods> implements GoodsService {

    @Autowired
    private StoreService storeService;

    @Override
    public Boolean addOrUpdate(Goods goods) {
        if(StringUtils.isBlank(goods.getId())){
            //add
            goods.setCreateTime(DateTool.getCurrTime());
            this.save(goods);
        }else{
            //update
            this.updateById(goods);
        }
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id :aryIds){
            Goods goods = this.getById(id);
            goods.setDelFlag(ConstantsUtils.GL_DEL);
            this.updateById(goods);
        }
    }


    @Override
    public List<Goods> list() {
        return this.list();
    }


    @Override
    public Page<Goods> page(Integer pageNum,Integer pageSize, String name, String storeId) {
        Page<Goods> page = new Page(pageNum,pageSize);
        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            lambdaQueryWrapper.like(Goods::getName, name);
        }
        if (StringUtils.isNotBlank(storeId)) {
            lambdaQueryWrapper.like(Goods::getStoreId, storeId);
        }
        lambdaQueryWrapper.eq(Goods::getDelFlag, ConstantsUtils.GL_NORMAL);
        lambdaQueryWrapper.orderByDesc(Goods::getCreateTime);
        // 执行分页查询
        page = this.page(page, lambdaQueryWrapper);

        for(Goods Goods:page.getRecords()){//分页信息记录数
            Store store = storeService.getById(Goods.getStoreId());
            //把dectype放入Goods这个ENTITY的ETC里头
            Goods.put("store",store);
            //Goods.put("key","2023");
        }
        return page;
        //return this.page(page);
    }
    @Override
    public Page<Goods> page2(Integer pageNum,Integer pageSize, String name, String storeId) {
        Page<Goods> page = new Page(pageNum,pageSize);

        LambdaQueryWrapper<Goods> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(storeId)) {
            lambdaQueryWrapper.like(Goods::getStoreId, storeId);
        }
        lambdaQueryWrapper.orderByDesc(Goods::getCreateTime);
        // 执行分页查询
        page = this.page(page, lambdaQueryWrapper);

        page = baseMapper.getPage(page,name);
        for(Goods Goods:page.getRecords()){//分页信息记录数
            Store store = storeService.getById(Goods.getStoreId());
            Goods.put("store",store);
        }
        return page;
    }
}