package com.example.demo.videos.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Store.entity.Store;
import com.example.demo.categories.entity.Categories;
import com.example.demo.chapter3.entity.User;
import com.example.demo.chapter3.service.UserService;
import com.example.demo.products.entity.Products;
import com.example.demo.products.service.ProductsService;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.videos.mapper.VideosMapper;
import com.example.demo.videos.entity.Videos;
import com.example.demo.videos.service.VideosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
/**
* <p>
    * 视频表 服务层实现类
    * </p>
*
* @author Csk
* @since 2024-07-08
*/
@Service
public class VideosServiceImpl extends ServiceImpl<VideosMapper,Videos> implements VideosService {

    @Autowired
    private ProductsService productsService;

    @Override
    public Boolean addOrUpdate(Videos videos) {
        if(StringUtils.isBlank(videos.getId())){
            //add
            videos.setCreateTime(DateTool.getCurrTime());
            this.save(videos);
        }else{
            //update
            this.updateById(videos);
        }
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
        Videos videos = this.getById(id);
        videos.setDelFlag(ConstantsUtils.GL_DEL);
        this.updateById(videos);
        }

    }


    @Override
    public List<Videos> list(String productId) {
        QueryWrapper<Videos> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Videos::getDelFlag,ConstantsUtils.GL_NORMAL);
        if (StringUtils.isNotBlank(productId)) {
            queryWrapper.lambda().like(Videos::getProductId, productId);
        }
        queryWrapper.lambda().orderByDesc(Videos::getCreateTime);
        List<Videos> list =this.list(queryWrapper);
        return list;
    }


    @Override
    public Page<Videos> page(Integer pageNum,Integer pageSize,String categoryId) {
        Page<Videos> page = new Page<>(pageNum, pageSize);
        List<Videos> videos = baseMapper.findVideosByCategory(categoryId);
        System.out.println(categoryId);
        page.setRecords(videos);
        page.setTotal(videos.size());
        for(Videos video:page.getRecords()){//分页信息记录数
            Products products = productsService.getById(video.getProductId());
            video.put("products",products);
        }
        // 注意：上述设置total和records的方式在数据量大时可能不准确，
        // 因为我们没有使用真正的分页查询，只是简单地将列表长度作为总记录数。
        // 为了正确实现分页，你需要在SQL中使用LIMIT和OFFSET，或者使用MyBatis Plus的分页查询。

//        QueryWrapper<Videos> queryWrapper = new QueryWrapper<>();
//        queryWrapper.lambda().eq(Videos::getDelFlag,ConstantsUtils.GL_NORMAL);
//        queryWrapper.lambda().orderByDesc(Videos::getCreateTime);
//        List<Videos> list =this.list(queryWrapper);
//        // 过滤列表中products的category
//        List<Videos> filteredList = list.stream()
//                .filter(videos -> {
//                    Products products = productsService.getById(videos.getProductId());
//                    return products != null && category.equals(products.getCategoryId());
//                })
//                .collect(Collectors.toList());

        // 手动分页

//        Page<Videos> page = new Page(pageNum,pageSize);
//        LambdaQueryWrapper<Videos> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper.eq(Videos::getDelFlag, ConstantsUtils.GL_NORMAL);
//        lambdaQueryWrapper.orderByDesc(Videos::getCreateTime);
//        page = this.page(page, lambdaQueryWrapper);
//        for(Videos videos:page.getRecords()){//分页信息记录数
//            Products products = productsService.getById(videos.getProductId());
//            videos.put("products",products);
//        }
        return page;
    }

}