package com.example.demo.Shopcart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Goods.entity.Goods;
import com.example.demo.Goods.service.GoodsService;
import com.example.demo.Store.entity.Store;
import com.example.demo.Store.service.StoreService;
import com.example.demo.chapter3.entity.User;
import com.example.demo.chapter3.service.UserService;
import com.example.demo.chapter4.entity.Dictype;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.Shopcart.mapper.ShopcartMapper;
import com.example.demo.Shopcart.entity.Shopcart;
import com.example.demo.Shopcart.service.ShopcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
/**
* <p>
    * 订单表 服务层实现类
    * </p>
*
* @author Csk
* @since 2023-12-21
*/
@Service
public class ShopcartServiceImpl extends ServiceImpl<ShopcartMapper,Shopcart> implements ShopcartService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private StoreService storeService;

    @Override
    public Boolean addOrUpdate(Shopcart shopcart) {
        if(StringUtils.isBlank(shopcart.getId())){
            //add
            shopcart.setCreateTime(DateTool.getCurrTime());
            this.save(shopcart);
        }else{
            //update
            this.updateById(shopcart);
        }
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id :aryIds){
            Shopcart shopcart = this.getById(id);
            shopcart.setDelFlag(ConstantsUtils.GL_DEL);
            this.updateById(shopcart);
        }
        //this.removeByIds(listIds);
    }


    @Override
    public List<Shopcart> list(String id) {
        QueryWrapper<Shopcart> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().like(Shopcart::getUserId,id);
        queryWrapper.lambda().eq(Shopcart::getDelFlag, ConstantsUtils.GL_NORMAL);
        queryWrapper.lambda().orderByDesc(Shopcart::getCreateTime);
        List<Shopcart> list =this.list(queryWrapper);

        for (int i = 0; i < list.size(); i++) {
            Shopcart shopcart = list.get(i);
            Goods goods = goodsService.getById(shopcart.getGoodsId());
            shopcart.put("goods", new Goods());
            if (goods != null) {
                shopcart.put("goods", goods);
                Store store = storeService.getById(goods.getStoreId());
                shopcart.put("store", new Store());
                if(store != null){
                    shopcart.put("store",store);
                }
            }
        }

        return list;
    }


    @Override
    public Page<Shopcart> page(Integer pageNum,Integer pageSize) {
        Page<Shopcart> page = new Page(pageNum,pageSize);
        return this.page(page);
    }

}