package com.example.demo.Store.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Goods.entity.Goods;
import com.example.demo.Store.entity.Store;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.Store.vo.StoreUserVO;
import com.example.demo.chapter4.vo.DictypeUserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 字典类型表 Mapper 接口
 * </p>
 *
 * @author Csk
 * @since 2023-12-18
 */
public interface StoreMapper extends BaseMapper<Store> {


    @Select("select store.*,user.username,user.type from tb_store store,tb_user user where user.id = store.user_id and store.name like '%${name}%' and store.del_flag <> 0 ")
    //@Select("select store.*, user.username from tb_user user,tb_store store where user.id = store.user_id")
    Page<StoreUserVO> getPage(@Param("page")Page<StoreUserVO> page,@Param("name") String name);
//   @Select("select store.*, user.username from tb_user user,tb_store store where user.id = store.user_id  and user.username like '%${username}%'")
//   Page<StoreUserVO> getPage(@Param("page")Page<StoreUserVO> page, @Param("username") String username);

    @Select("select store.* from tb_store store JOIN tb_user user ON store.user_id = user.id where store.del_flag <> 0  ")
        //@Select("select store.*, user.username from tb_user user,tb_store store where user.id = store.user_id")
    Page<Store> getPage2(@Param("page")Page<Store> page, @Param("userId") String userId);
}
