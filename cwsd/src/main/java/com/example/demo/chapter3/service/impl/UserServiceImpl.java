package com.example.demo.chapter3.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Shopcart.entity.Shopcart;
import com.example.demo.chapter2.entity.Book;
import com.example.demo.chapter3.dto.UserLoginDto;
import com.example.demo.chapter3.entity.User;
import com.example.demo.chapter3.mapper.UserMapper;
import com.example.demo.chapter3.service.UserService;
import com.example.demo.chapter4.entity.Dictype;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.springboot2023.utils.DateTool;
import com.example.demo.springboot2023.utils.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User login(UserLoginDto userLoginDto) throws Exception{
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername,userLoginDto.getUsername());
        User userExit = this.getOne(queryWrapper);
        if(userExit !=null){
            if(userExit.getPassword().equals(MD5Util.getEncode(userLoginDto.getPassword(),userExit.getSalt()))){
                return userExit;
            }
        }
        return userExit;
    }

    @Override
    public void register(User user) throws Exception{
        user.setCreateTime(DateTool.getCurrTime());
        String salt = UUID.randomUUID().toString();
        user.setSalt(salt);

        String pwdMd5 = MD5Util.getEncode(user.getPassword(),salt);
        user.setPassword(pwdMd5);
        this.save(user);
    }

    @Override
    public boolean exit(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername,username);
        if(this.getOne(queryWrapper)!=null){
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void trans(String ids, Double ballance) {
        String[]aryIds = ids.split(",");
        //接收人+
        User user2 = this.getById(aryIds[1]);
        user2.setBallance(user2.getBallance()+ballance);
        this.updateById(user2);

        User user1 = this.getById(aryIds[0]);
        if(user1.getBallance()<ballance){
            throw new RuntimeException(user1.getUsername()+"的余额不足");
        }else{
            user1.setBallance(user1.getBallance()-ballance);
            this.updateById(user1);
        }
        return;
    }

    @Override
    public List<User> list(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(name)){
            queryWrapper.lambda().like(User::getUsername,name);
        }
        queryWrapper.lambda().eq(User::getDelFlag,ConstantsUtils.GL_NORMAL);
        queryWrapper.lambda().orderByDesc(User::getCreateTime);
        List<User> list =this.list(queryWrapper);
        return list;
    }

    @Override
    public Page<User> page(Integer pageNum, Integer pageSize, String username) {
        Page<User> page =new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            lambdaQueryWrapper.like(User::getUsername,username);
        }
        lambdaQueryWrapper.eq(User::getDelFlag,ConstantsUtils.GL_NORMAL);
        lambdaQueryWrapper.orderByDesc(User::getCreateTime);
        return this.page(page,lambdaQueryWrapper);
    }

    @Override
    public void deleteById(String ids) {
        String[] aryIds = ids.split(",");
//        List<String> listIds = Arrays.asList(aryIds);
//        this.removeByIds(listIds);
        for(String id :aryIds){
            User user = this.getById(id);
            user.setDelFlag(ConstantsUtils.GL_DEL);
            this.updateById(user);

        }
    }

    @Override
    public void changeState(String id,Integer state) {
            User user = this.getById(id);
            user.setState(state);
            this.updateById(user);
    }

    @Override
    public void typeState(String id, Integer type) {
        User user = this.getById(id);
        user.setType(type);
        this.updateById(user);
    }

    @Override
    public User addOrUpdate(User user) {
        if(StringUtils.isBlank(user.getId())){
            //add
            user.setCreateTime(DateTool.getCurrTime());
            this.save(user);
        }else{
            //update
            this.updateById(user);
        }
        return user;
    }
    
}
