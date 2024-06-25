package com.example.demo.chapter4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.chapter4.entity.Dictype;
import com.example.demo.chapter4.vo.DictypeUserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface DictypeMapper extends BaseMapper<Dictype> {

    @Select("select dictype.*, user.username from tb_user user,tb_dictype dictype where user.id = dictype.user_id and dictype.name like '%${name}%' and user.username like '%${username}%'")
    Page<DictypeUserVO> getPageByUsername(@Param("page")Page<DictypeUserVO> page,@Param("name") String name,@Param("username") String username);
}
