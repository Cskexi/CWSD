package com.example.demo.chapter3.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.chapter3.dto.UserLoginDto;
import com.example.demo.chapter3.entity.User;
import com.example.demo.chapter4.entity.Dictype;
import com.example.demo.order.entity.Order;

import java.util.List;


public interface UserService extends IService<User> {

    User addOrUpdate(User user);
    User login(UserLoginDto userLoginDto) throws Exception;

    void register(User user) throws Exception;

    boolean exit(String username);

    void trans(String ids,Double ballance);

    List<User> list(String username);

    Page<User> page(Integer pageNum, Integer pageSize, String username);

    void deleteById(String ids);
    void changeState(String id,Integer state);

    void typeState(String id, Integer type);
}
