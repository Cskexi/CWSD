package com.example.demo.springboot2023.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {
    @Bean
    public Docket chapter2Api() {
        return new Docket(DocumentationType.SWAGGER_2) // v2 不同
                .groupName("chapter2")
                .apiInfo(
                    new ApiInfoBuilder()
                            //页面标题
                            .title("chapter2模块 API")
                            //创建人
                           //.contact(new Contact("Bryan", "http://blog.bianxh.top/", ""))
                            //版本号
                            // .version("1.0")
                            //描述
                            // .description("API 描述")
                            .build()
                ).select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.chapter2.controller"))
                //所有的controller
                //.apis(RequestHandlerSelectors.any())
                .build();
    }



}
