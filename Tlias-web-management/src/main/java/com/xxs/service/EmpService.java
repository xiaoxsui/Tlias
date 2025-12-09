package com.xxs.service;

import com.xxs.pojo.Emp;
import com.xxs.pojo.EmpQueryParam;
import com.xxs.pojo.PageResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface EmpService {
    //分页查询
    //page——页码；pageSize——每页展示记录数
//    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);
    PageResult<Emp> page(EmpQueryParam empQueryParam);


    //新增员工
    void save(Emp emp);

    //批量删除员工信息
    void delete(List<Integer> ids);
}
