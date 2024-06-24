package com.example.cwsd.Dictype.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.cwsd.Dictype.entity.Dictype;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 字典类型表 Mapper 接口
 * </p>
 *
 * @author Csk
 * @since 2024-05-14
 */
public interface DictypeMapper extends BaseMapper<Dictype> {
    @Select("select dictype.* from tb_dictype dictype where dictype.name like '%${name}%' ")
    Page<Dictype> getPageByUsername(@Param("page")Page<Dictype> page, @Param("name") String name);

}
