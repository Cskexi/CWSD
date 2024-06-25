package com.example.demo.chapter5.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.chapter5.entity.Student;

import java.util.List;

/**
* <p>
    * 用户表 服务类接口
    * </p>
*
* @author Csk
* @since 2023-12-03
*/
public interface StudentService extends IService<Student> {


    Boolean addOrUpdate(Student student);
    void deleteByIds(String ids);
    List<Student> list();
    Page<Student> page(Integer pageNum,Integer pageSize);

    Student myGetById(String id);
}