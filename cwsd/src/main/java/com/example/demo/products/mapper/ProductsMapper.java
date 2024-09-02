package com.example.demo.products.mapper;

import com.example.demo.products.entity.Products;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author Csk
 * @since 2024-06-25
 */
public interface ProductsMapper extends BaseMapper<Products> {
        // 更新商品表中的浏览次数
        @Insert("CREATE TEMPORARY TABLE IF NOT EXISTS temp_view_counts (product_id VARCHAR(45), view INT)")
        void createTempTable();

        @Insert("INSERT INTO temp_view_counts (product_id, view) SELECT LEFT(product_id, 45), COUNT(*) AS view FROM tb_user_action GROUP BY LEFT(product_id, 45)")
        void insertIntoTempTable();

        @Update("UPDATE tb_products p JOIN temp_view_counts tvc ON p.id = tvc.product_id SET p.view = tvc.view")
        void updateGoodsView();

        @Delete("DROP TEMPORARY TABLE IF EXISTS temp_view_counts")
        void dropTempTable();
}
