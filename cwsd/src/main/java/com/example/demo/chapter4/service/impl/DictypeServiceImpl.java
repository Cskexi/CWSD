package com.example.demo.chapter4.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.chapter2.dto.BookPageDto;
import com.example.demo.chapter3.entity.User;
import com.example.demo.chapter3.service.UserService;
import com.example.demo.chapter4.entity.Dic;
import com.example.demo.chapter4.entity.Dictype;
import com.example.demo.chapter4.mapper.DictypeMapper;
import com.example.demo.chapter4.service.DicService;
import com.example.demo.chapter4.service.DictypeService;
import com.example.demo.chapter4.vo.DictypeUserVO;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.springboot2023.utils.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DictypeServiceImpl extends ServiceImpl<DictypeMapper, Dictype> implements DictypeService {

    @Autowired
    private DicService dicService;

    @Autowired
    private UserService userService;
    @Override
    public void addOrUpdate(Dictype book){
        if(StringUtils.isBlank(book.getId())){
            book.setCreateTime(DateTool.getCurrTime());
            this.save(book);
        }else {
            this.updateById(book);
        }
    }

    @Override
    public boolean exist(Dictype book){
        LambdaQueryWrapper<Dictype> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Dictype::getName,book.getName());
        Dictype bookExit = this.getOne(lambdaQueryWrapper);

//        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("name",book.getName());
//        Book bookExit = this.getOne(queryWrapper);
        if(bookExit!=null&& !bookExit.getId().equals(book.getId())){
            return true;
        }
        return false;
    }

    @Override
    public List<Dictype> list(String name) {
        QueryWrapper<Dictype> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(name)){
            queryWrapper.lambda().like(Dictype::getName,name);
        }
        queryWrapper.lambda().eq(Dictype::getDelFlag,ConstantsUtils.GL_NORMAL);
        //queryWrapper.like("name",name);
        queryWrapper.lambda().orderByDesc(Dictype::getCreateTime);
        //queryWrapper.orderByDesc("create_time");
        List<Dictype> list =this.list(queryWrapper);
        return list;
    }

    @Override
    public void deleteById(String ids) {
        String[] aryIds = ids.split(",");
//        List<String> listIds = Arrays.asList(aryIds);
//        this.removeByIds(listIds);
        for(String id :aryIds){
            Dictype book = this.getById(id);
            book.setDelFlag(ConstantsUtils.GL_DEL);
            this.updateById(book);
        }
    }

    @Override
    public Page<Dictype> page(Integer pageNum, Integer pageSize, String name,String username) {
        List<String> listUserIds = new ArrayList<>();
        if(StringUtils.isNotBlank(username)){
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLambdaQueryWrapper.like(User::getUsername,username);
            List<User> userList = userService.list(userLambdaQueryWrapper);
            for(User user:userList){
                listUserIds.add(user.getId());
            }
        }
        Page<Dictype> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Dictype> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            lambdaQueryWrapper.like(Dictype::getName, name);
        }
        if(StringUtils.isNotBlank(username)){
            lambdaQueryWrapper.in(Dictype::getUserId,listUserIds);
        }
        lambdaQueryWrapper.eq(Dictype::getDelFlag, ConstantsUtils.GL_NORMAL);
        lambdaQueryWrapper.orderByDesc(Dictype::getCreateTime);
        // 执行分页查询
        page = this.page(page, lambdaQueryWrapper);


        for(Dictype dictype : page.getRecords()){
            User user = userService.getById(dictype.getUserId());
            dictype.put("user",new User());
            if(user!=null){
                dictype.put("user",user);
            }
        }
        return page;
    }

    @Override
    public Page<DictypeUserVO> pageByUsername(Integer pageNum, Integer pageSize, String name, String username) {
        Page<DictypeUserVO> page = new Page<>(pageNum, pageSize);
        return baseMapper.getPageByUsername(page,name,username);
    }

    @Override
    public Map<String, List<Dic>> getAllMap() {
        QueryWrapper<Dictype> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Dictype::getDelFlag,ConstantsUtils.GL_NORMAL);
        List<Dictype> dictypeList = this.list(queryWrapper);
        Map<String,List<Dic>> map = new HashMap<String,List<Dic>>();
        for(Dictype dictype : dictypeList){
            QueryWrapper<Dic> dicQueryWrapper = new QueryWrapper<>();
            dicQueryWrapper.lambda().eq(Dic::getDictypeId,dictype.getId());
            dicQueryWrapper.lambda().eq(Dic::getDelFlag,ConstantsUtils.GL_NORMAL);
            List<Dic> dicList = dicService.list(dicQueryWrapper);
            map.put(dictype.getValue(),dicList);
        }
        return map;
    }

}
