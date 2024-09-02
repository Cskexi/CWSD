package com.example.demo.userAction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.chapter3.entity.User;
import com.example.demo.products.entity.Products;
import com.example.demo.userAction.dto.UserActionDto;
import com.example.demo.userAction.entity.UserAction;

import java.util.List;

/**
* <p>
    * 用户操作记录表 服务类接口
    * </p>
*
* @author Csk
* @since 2024-08-22
*/
public interface UserActionService extends IService<UserAction> {

    public UserAction exit(UserAction userAction);
    Boolean addOrUpdate(UserAction userAction);
    void deleteByIds(String ids);
    List<UserAction> list(UserActionDto userActionDto);
    Page<UserAction> page(Integer pageNum,Integer pageSize);
    Page<Products> page2(UserActionDto userActionDto);

    List<Products> recommendProduct(User user);

}