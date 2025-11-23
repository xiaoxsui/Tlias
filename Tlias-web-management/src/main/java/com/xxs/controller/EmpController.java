package com.xxs.controller;

import com.xxs.pojo.Emp;
import com.xxs.pojo.EmpQueryParam;
import com.xxs.pojo.PageResult;
import com.xxs.pojo.Result;
import com.xxs.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

//员工管理Controller
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

/*    //分页查询
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,      //使用注解为参数指定默认值
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Integer gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin, //指定日期类型数据默认格式
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end){
        log.info("分页查询：{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        PageResult<Emp> pageResult = empService.page(page, pageSize,name,gender,begin,end);
        return Result.success(pageResult);
    }*/

    //条件分页查询，代码优化
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){    //使用实体类封装全部形参
        log.info("分页查询：{}",empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }
}
