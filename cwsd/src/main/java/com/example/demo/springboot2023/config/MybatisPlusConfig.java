package com.example.demo.springboot2023.config;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;




//@ComponentScan(basePackages = "com.example.demo.chapter2.service")
@Configuration
//@MapperScan("com.example.demo.**.service")
@MapperScan("com.example.demo.**.mapper")
public class MybatisPlusConfig {

//    @Bean
//    public PaginationInnerInterceptor paginationInterceptor() {
//        PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor();
//        // 设置为 true 时，会将分页操作优化为 count 查询一次，不设置时会进行 count 查询
//        paginationInterceptor.setOptimizeJoin(true);
//        System.out.println("Setting optimizeJoin to true");
//        return paginationInterceptor;
//    }
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.H2));
        return interceptor;
    }

}
