package com.example.demo.videos.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Store.entity.Store;
import com.example.demo.Store.vo.StoreUserVO;
import com.example.demo.videos.entity.Videos;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 视频表 Mapper 接口
 * </p>
 *
 * @author Csk
 * @since 2024-07-08
 */
public interface VideosMapper extends BaseMapper<Videos> {
//    @Select("SELECT * FROM videos v LEFT JOIN products p ON p.id = v.product_id WHERE p.category_id = #{category}")
//    List<Videos> findVideosByCategory(@Param("categoryId") String categoryId);

    @Select("<script>" +
            "SELECT * FROM tb_videos v LEFT JOIN tb_products p ON p.id = v.product_id " +
            "<if test='categoryId != null and categoryId != \"\"'>" +
            "WHERE p.category_id = #{categoryId}" +
            "</if>" +
            "</script>")
    List<Videos> findVideosByCategory(@Param("categoryId") String categoryId);
}
