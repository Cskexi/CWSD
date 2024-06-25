package com.example.demo.chapter5.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.chapter5.mapper.StudentMapper;
import com.example.demo.chapter5.entity.Student;
import com.example.demo.chapter5.service.StudentService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
/**
* <p>
    * 用户表 服务层实现类
    * </p>
*
* @author Csk
* @since 2023-12-03
*/
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper,Student> implements StudentService {

    @Override
    public Boolean addOrUpdate(Student student) {
        if(StringUtils.isBlank(student.getId())){
            //add
            student.setCreateTime(DateTool.getCurrTime());
            this.save(student);
        }else{
            //update
            this.updateById(student);
        }
        return true;
    }

    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id: aryIds){
            listIds.add(id);
        }
        this.removeByIds(listIds);
    }


    @Override
    public List<Student> list() {
        return this.list();
    }


    @Override
    public Page<Student> page(Integer pageNum,Integer pageSize) {
        Page<Student> page = new Page(pageNum,pageSize);

        return this.page(page);
    }

    @Override
    public Student myGetById(String id) {
        return this.getById(id);
    }
}