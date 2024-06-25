package com.example.demo.order.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Store.vo.StoreUserVO;
import com.example.demo.order.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author Csk
 * @since 2023-12-21
 */
public interface OrderMapper extends BaseMapper<Order> {
//    SELECT tb_order.*
//    FROM tb_order tb_order
//    JOIN tb_order_item item ON tb_order.id = item.order_id
//    JOIN tb_goods goods ON item.goods_id = goods.id
//    JOIN tb_store store ON goods.store_id = store.id
//    WHERE tb_order.del_flag <> 0 AND  store.user_id = 'df334aa632978eee383e4faa091e5d5e'
    @Select("select tb_order.* from tb_order tb_order JOIN tb_order_item item ON tb_order.id = item.order_id" +
            " JOIN tb_goods goods ON item.goods_id = goods.id " +
            " JOIN tb_store store ON goods.store_id = store.id " +
            "where store.user_id like '%${id}%' and store.del_flag <> 0 ")
        //@Select("select store.*, user.username from tb_user user,tb_store store where user.id = store.user_id")
    List<Order> getList(@Param("list")List<Order> list, @Param("id") String name);
}
