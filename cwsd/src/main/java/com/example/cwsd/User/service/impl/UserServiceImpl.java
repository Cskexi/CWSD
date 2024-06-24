package com.example.cwsd.User.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.third.zhgd.DataMsg.entity.Datamsg;
import com.third.zhgd.DataMsg.service.DatamsgService;
import com.third.zhgd.User.dto.UserLoginDto;
import com.third.zhgd.User.entity.User;
import com.third.zhgd.User.mapper.UserMapper;
import com.third.zhgd.User.service.UserService;
import com.third.zhgd.other.utils.ConstantsUtils;
import com.third.zhgd.other.utils.DateTool;
import com.third.zhgd.other.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
* <p>
    * 用户表 服务层实现类
    * </p>
*
* @author Csk
* @since 2024-04-21
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Autowired
    private DatamsgService datamsgService;

    @Override
    public User login(UserLoginDto userLoginDto) throws Exception{
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getAccount,userLoginDto.getAccount());
        queryWrapper.lambda().eq(User::getDelFlag, ConstantsUtils.GL_NORMAL);
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
    public void add(Integer num) throws Exception{
        System.out.println(1);
        for(int i=0;i<num;i++){
            User user = new User();
            Datamsg datamsg = datamsgService.getNum("user");
            datamsg.setDataNum(datamsg.getDataNum()+1);
            datamsgService.addOrUpdate(datamsg);
            int a = 100000+datamsg.getDataNum();
            user.setAccount(a+"");
            user.setName("用户"+a);
            user.setPassword("123456");
            user.setCreateTime(DateTool.getCurrTime());
            String salt = UUID.randomUUID().toString();
            user.setSalt(salt);
            String pwdMd5 = MD5Util.getEncode(user.getPassword(),salt);
            user.setPassword(pwdMd5);
            this.save(user);
        }

    }

    @Override
    public boolean exit(String account) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getAccount,account);
        if(this.getOne(queryWrapper)!=null){
            return true;
        }
        return false;
    }


    @Override
    public Boolean addOrUpdate(User user) {
        if(StringUtils.isBlank(user.getId())){
            //add
            user.setCreateTime(DateTool.getCurrTime());
            this.save(user);
        }else{
            //update
            this.updateById(user);
        }
        return true;
    }



    @Override
    public void deleteByIds(String ids) {
        List<String> listIds = new ArrayList<>();
        String[] aryIds = ids.split(",");
        for(String id :aryIds){
            User user = this.getById(id);
            user.setDelFlag(ConstantsUtils.GL_DEL);
            this.updateById(user);

        }
//        for(String id: aryIds){
//            listIds.add(id);
//        }
//        this.removeByIds(listIds);
    }


    @Override
    public List<User> list() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getDelFlag, ConstantsUtils.GL_NORMAL);
        queryWrapper.lambda().orderByDesc(User::getCreateTime);
        List<User> list =this.list(queryWrapper);
        return list;
    }


    @Override
    public Page<User> page(Integer pageNum,Integer pageSize,String name) {
        Page<User> page =new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(name)){
            lambdaQueryWrapper.like(User::getName,name);
        }
        lambdaQueryWrapper.eq(User::getDelFlag,ConstantsUtils.GL_NORMAL);
        //lambdaQueryWrapper.orderByDesc(User::getCreateTime);
        lambdaQueryWrapper.orderByDesc(User::getAccount);
        return this.page(page,lambdaQueryWrapper);
    }


}