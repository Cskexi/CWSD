package com.example.demo.test.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.test.entity.Student;

import java.util.List;

/**
* <p>
    * 用户表 服务类接口
    * </p>
*
* @author Csk
* @since 2023-12-11
*/
public interface StudentService extends IService<Student> {


    Boolean addOrUpdate(Student student);
    void deleteByIds(String ids);
    List<Student> list();
    Page<Student> page(Integer pageNum,Integer pageSize);

    Student myGetById(String id);
}