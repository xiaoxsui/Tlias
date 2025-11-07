package com.xxs.service.impl;

import com.xxs.mapper.EmpMapper;
import com.xxs.pojo.Emp;
import com.xxs.pojo.PageResult;
import com.xxs.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    //分页查询
    //page——页码；pageSize——每页展示记录数
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        //1.调用mapper接口，查询总记录数
        long total = empMapper.count();

        //2.调用mapper接口，查询结果列表
        Integer start = (page - 1) * pageSize;  //通过页码来计算起始索引
        List<Emp> rows = empMapper.list(start,pageSize);

        //3.封装结果 PageResult
        return new PageResult<Emp>(total,rows);

    }
}
