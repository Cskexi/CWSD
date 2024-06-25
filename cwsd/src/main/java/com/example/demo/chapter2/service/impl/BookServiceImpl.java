package com.example.demo.chapter2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.chapter2.dto.BookPageDto;
import com.example.demo.chapter2.entity.Book;
import com.example.demo.chapter2.mapper.BookMapper;
import com.example.demo.chapter2.service.BookService;
import com.example.demo.chapter3.entity.User;
import com.example.demo.chapter3.service.UserService;
import com.example.demo.chapter4.vo.DictypeUserVO;
import com.example.demo.springboot2023.utils.ConstantsUtils;
import com.example.demo.springboot2023.utils.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {

    @Autowired
    private UserService userService;
    @Override
    public void addOrUpdate(Book book){
        if(StringUtils.isBlank(book.getId())){
            book.setCreateTime(DateTool.getCurrTime());
            this.save(book);
        }else {
            this.updateById(book);
        }
    }

    @Override
    public boolean exist(Book book){
        LambdaQueryWrapper<Book> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Book::getName,book.getName());
        Book bookExit = this.getOne(lambdaQueryWrapper);

//        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("name",book.getName());
//        Book bookExit = this.getOne(queryWrapper);
        if(bookExit!=null&& !bookExit.getId().equals(book.getId())){
            return true;
        }
        return false;
    }

    @Override
    public List<Book> list(String name) {

        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(name)){
            queryWrapper.lambda().like(Book::getName,name);
        }
        queryWrapper.lambda().eq(Book::getDelFlag,ConstantsUtils.GL_NORMAL);
        //queryWrapper.like("name",name);
        queryWrapper.lambda().orderByDesc(Book::getCreateTime);
        //queryWrapper.orderByDesc("create_time");
        List<Book> list =this.list(queryWrapper);
        return list;
    }

    @Override
    public void deleteById(String ids) {
        String[] aryIds = ids.split(",");
//        List<String> listIds = Arrays.asList(aryIds);
//        this.removeByIds(listIds);
        for(String id :aryIds){
            Book book = this.getById(id);
            book.setDelFlag(ConstantsUtils.GL_DEL);
            this.updateById(book);
        }
    }

    @Override
    public Page<Book> page(BookPageDto bookPageDto) {
        Page<Book> page =new Page<>(bookPageDto.getPageNum(),bookPageDto.getPageSize());

        LambdaQueryWrapper<Book> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(bookPageDto.getName())){
            lambdaQueryWrapper.like(Book::getName,bookPageDto.getName());
        }
        if(ObjectUtils.isNotEmpty(bookPageDto.getState())){
            lambdaQueryWrapper.like(Book::getState,bookPageDto.getState());
        }
        lambdaQueryWrapper.eq(Book::getDelFlag,ConstantsUtils.GL_NORMAL);
        lambdaQueryWrapper.orderByDesc(Book::getCreateTime);
        return this.page(page,lambdaQueryWrapper);
    }

    @Override
    public Page<Book> pageByUsername(Integer pageNum, Integer pageSize, String username) {
        List<String> listUserIds = new ArrayList<>();
        if(StringUtils.isNotBlank(username)){
            LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLambdaQueryWrapper.like(User::getUsername,username);
            List<User> userList = userService.list(userLambdaQueryWrapper);
            for(User user:userList){
                listUserIds.add(user.getId());
            }
        }
        Page<Book> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Book> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            if(listUserIds.isEmpty()){
                return new Page<>(pageNum, pageSize);
            }
            lambdaQueryWrapper.in(Book::getUserId,listUserIds);
        }
        lambdaQueryWrapper.eq(Book::getDelFlag, ConstantsUtils.GL_NORMAL);
        lambdaQueryWrapper.orderByDesc(Book::getCreateTime);
        // 执行分页查询
       page = this.page(page, lambdaQueryWrapper);
        for(Book book : page.getRecords()){
            User user = userService.getById(book.getUserId());
            book.put("user",new User());
            if(user!=null){
                book.put("user",user);
            }
        }
        return page;
    }

    @Override
    public Page<DictypeUserVO> pageByUsername2(Integer pageNum, Integer pageSize, String username) {
        Page<DictypeUserVO> page = new Page<>(pageNum, pageSize);
        return baseMapper.getPageByUsername2(page,username);
    }
}
