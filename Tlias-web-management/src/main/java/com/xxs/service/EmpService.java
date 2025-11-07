package com.xxs.service;

import com.xxs.pojo.Emp;
import com.xxs.pojo.PageResult;

public interface EmpService {
    //分页查询
    //page——页码；pageSize——每页展示记录数
    PageResult<Emp> page(Integer page, Integer pageSize);
}
