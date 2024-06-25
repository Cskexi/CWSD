package com.example.demo.chapter2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.chapter2.entity.Book;
import com.example.demo.chapter4.vo.DictypeUserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface BookMapper extends BaseMapper<Book> {
   @Select("select book.*, user.username from tb_user user,tb_book book where user.id = book.user_id  and user.username like '%${username}%'")
   Page<DictypeUserVO> getPageByUsername2(@Param("page")Page<DictypeUserVO> page,@Param("username") String username);

}
