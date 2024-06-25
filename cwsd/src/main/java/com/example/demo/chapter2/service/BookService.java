package com.example.demo.chapter2.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.chapter2.dto.BookPageDto;
import com.example.demo.chapter2.entity.Book;
import com.example.demo.chapter4.vo.DictypeUserVO;

import java.util.List;


public interface BookService extends IService<Book> {
    void addOrUpdate(Book book);
    boolean exist(Book book);

    List<Book> list(String name);

    void deleteById(String ids);

    Page<Book> page(BookPageDto bookPageDto);

    Page<Book> pageByUsername(Integer pageNum, Integer pageSize, String username);
    Page<DictypeUserVO> pageByUsername2(Integer pageNum, Integer pageSize, String username);
}
