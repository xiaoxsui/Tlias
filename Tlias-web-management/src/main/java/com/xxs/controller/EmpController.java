package com.xxs.controller;

import com.xxs.pojo.Emp;
import com.xxs.pojo.EmpQueryParam;
import com.xxs.pojo.PageResult;
import com.xxs.pojo.Result;
import com.xxs.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

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

    //新增员工
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工：{}",emp);
        empService.save(emp);
        return Result.success();
    }

    //删除员工 -- 数组实现
/*    @DeleteMapping
    public Result delete(Integer[] ids){
        log.info("删除员工：{}", Arrays.toString(ids));  //需要使用Arrays工具类输出数组元素

        return Result.success();
    }*/

    //删除员工 -- list实现（推荐使用，方便操作list元素）
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    //根据id查询员工信息
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询员工信息：{}",id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }
}
