package com.xxs.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EmpQueryParam {
    Integer page;   //页码
    Integer pageSize;   //每页展示记录数
    String name;    //姓名
    Integer gender; //性别
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate begin;    //入职时间-开始
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate end;      //入职时间-结束
}
