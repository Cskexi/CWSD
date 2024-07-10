package com.example.demo.videos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.videos.entity.Videos;

import java.util.List;

/**
* <p>
    * 视频表 服务类接口
    * </p>
*
* @author Csk
* @since 2024-07-08
*/
public interface VideosService extends IService<Videos> {


    Boolean addOrUpdate(Videos videos);
    void deleteByIds(String ids);
    List<Videos> list(String productId);
    Page<Videos> page(Integer pageNum,Integer pageSize,String category);

}