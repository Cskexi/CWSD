package com.example.cwsd.Notice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.third.zhgd.Notice.entity.Notice;

import java.util.List;

/**
* <p>
    *  服务类接口
    * </p>
*
* @author Csk
* @since 2024-05-21
*/
public interface NoticeService extends IService<Notice> {


    Boolean addOrUpdate(Notice notice);
    void deleteByIds(String ids);
    List<Notice> list(String state);
    Page<Notice> page(Integer pageNum,Integer pageSize,String state);

}