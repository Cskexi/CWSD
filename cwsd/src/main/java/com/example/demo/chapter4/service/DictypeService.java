package com.example.demo.chapter4.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.chapter2.dto.BookPageDto;
import com.example.demo.chapter4.entity.Dic;
import com.example.demo.chapter4.entity.Dictype;
import com.example.demo.chapter4.vo.DictypeUserVO;

import java.util.List;
import java.util.Map;


public interface DictypeService extends IService<Dictype> {
    void addOrUpdate(Dictype book);
    boolean exist(Dictype book);

    List<Dictype> list(String name);

    void deleteById(String ids);

    Page<Dictype> page(Integer pageNum,Integer pageSize,String name,String username);

    Page<DictypeUserVO> pageByUsername(Integer pageNum, Integer pageSize, String name, String username);

    Map<String,List<Dic>> getAllMap();
}
