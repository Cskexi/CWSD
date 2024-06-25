package com.example.demo.Goods.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Goods.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Store.vo.StoreUserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author Csk
 * @since 2023-12-19
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    //@Select("select goods.* from tb_goods goods join tb_store store on goods.store_id = store.id where store.name like CONCAT('%', #{name}, '%') or goods.name like CONCAT('%', #{name}, '%') and goods.del_flag <> 0")

    @Select("select goods.* from tb_goods goods JOIN tb_store store ON goods.store_id = store.id where goods.del_flag <> 0  and  store.name like '%${name}%' or goods.name like '%${name}%' ")
        //@Select("select store.*, user.username from tb_user user,tb_store store where user.id = store.user_id")
    Page<Goods> getPage(@Param("page")Page<Goods> page, @Param("name") String name);
}
