package com.example.demo.chapter2.dto;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class BookPageDto {
    private Integer pageNum;
    private Integer pageSize;
    private String name;
    private Integer state;
}
