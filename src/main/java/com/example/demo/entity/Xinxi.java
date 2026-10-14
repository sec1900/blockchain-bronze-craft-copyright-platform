package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "user")
public class Xinxi {
    private Integer id;
    private String name;
    private Float grade;
    private Integer num;
    private String doctor;
    private String main;
    private Integer year;
    private String country;
    private String type;
}
