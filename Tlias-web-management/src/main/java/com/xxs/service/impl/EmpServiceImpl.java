package com.xxs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxs.mapper.EmpExprMapper;
import com.xxs.mapper.EmpMapper;
import com.xxs.pojo.*;
import com.xxs.service.EmpLogService;
import com.xxs.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    //分页查询——————————原始方式
    //page——页码；pageSize——每页展示记录数
/*    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        //1.调用mapper接口，查询总记录数
        long total = empMapper.count();

        //2.调用mapper接口，查询结果列表
        Integer start = (page - 1) * pageSize;  //通过页码来计算起始索引
        List<Emp> rows = empMapper.list(start,pageSize);

        //3.封装结果 PageResult
        return new PageResult<Emp>(total,rows);

    }*/

//PageHelper实现分页查询
//page——页码；pageSize——每页展示记录数
/*    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize,
                                String name, Integer gender,
                                LocalDate begin, LocalDate end) {
        //1.设置分页参数(PageHelper)
        PageHelper.startPage(page,pageSize);

        //2.执行查询
        List<Emp> empList = empMapper.list(name,gender,begin,end);

        //3.解析查询结果，并封装参数
//        PageInfo<Emp> pageInfo = new PageInfo<>(empList);
//        return new PageResult<Emp>(pageInfo.getTotal(),pageInfo.getList());
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }*/

    //分页查询优化
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());

        List<Emp> empList = empMapper.list(empQueryParam);

        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<>(p.getTotal(),p.getResult());
    }

    //新增员工
    @Transactional  //当业务方法多次对数据库进行增删改操作时，需要为其添加事务
    @Override
    public void save(Emp emp) {
        try {
            //1. 保存员工基本信息
            //1.1 补全基础属性
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            //1.2 保存信息
            empMapper.insert(emp);

            //2. 保存员工工作经历信息
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)) {  //判断list是否为空
                //遍历集合，为empId赋值
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志
            EmpLog emplog = new EmpLog(null, LocalDateTime.now(),"新增员工:" + emp);
            empLogService.insertLog(emplog);
        }


    }

    //批量删除员工信息
    @Transactional
    @Override
    public void delete(List<Integer> ids) {
        //1.批量删除员工基本信息
        empMapper.deleteByIds(ids);
        //2.批量删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

}
