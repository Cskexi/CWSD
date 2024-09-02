package com.example.demo.userAction.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Goods.entity.Goods;
import com.example.demo.chapter3.entity.User;
import com.example.demo.products.entity.Products;
import com.example.demo.userAction.entity.UserAction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * <p>
 * 用户操作记录表 Mapper 接口
 * </p>
 *
 * @author Csk
 * @since 2024-08-22
 */
public interface UserActionMapper extends BaseMapper<UserAction> {


    /**
     * 查询所有用户数量（id）
     */
    @Select("SELECT id from tb_user")
    public List<User> getALLUserIds();

    /**
     * 根据指定用户id查询浏览过的商品数量
     */
    @Select("SELECT user_id from tb_user_action where user_id=#{id}")
    public List<UserAction> getProductsByUserId(User user);

    /**
     * 优化方法，只读取用户关注关系表，减少IO操作，剩下全部由程序在内存中执行
     * 由于只有ids集合，所以对内存占用有限，不用担心性能，建议使用map操作集合
     * @fans_fo_id 被关注id
     * @fo_id 关注id
     */
    @Select("select product_id,user_id from tb_user_action")
    public List<UserAction> getALLProductUsers();

}
