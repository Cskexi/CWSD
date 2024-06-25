package com.example.demo.chapter4.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.chapter4.entity.Dic;

import java.util.List;


public interface DicService extends IService<Dic> {
    void addOrUpdate(Dic dic);
    boolean exist(Dic dic);

    List<Dic> list(String name);

    void deleteById(String ids);

    Page<Dic> page(Integer pageNum, Integer pageSize, String name,String dictypeId);
}
