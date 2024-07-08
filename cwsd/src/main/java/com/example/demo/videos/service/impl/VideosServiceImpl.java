package com.example.demo.videos.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.videos.mapper.VideosMapper;
import com.example.demo.videos.entity.Videos;
import com.example.demo.videos.service.VideosService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
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
    public List<Videos> list() {
        QueryWrapper<Videos> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Videos::getDelFlag,ConstantsUtils.GL_NORMAL);
        queryWrapper.lambda().orderByDesc(Videos::getCreateTime);
        List<Videos> list =this.list(queryWrapper);
        return this.list();
    }


    @Override
    public Page<Videos> page(Integer pageNum,Integer pageSize) {
        Page<Videos> page = new Page(pageNum,pageSize);
        LambdaQueryWrapper<Videos> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Videos::getDelFlag, ConstantsUtils.GL_NORMAL);
        lambdaQueryWrapper.orderByDesc(Videos::getCreateTime);
        page = this.page(page, lambdaQueryWrapper);
        return page;
    }

}