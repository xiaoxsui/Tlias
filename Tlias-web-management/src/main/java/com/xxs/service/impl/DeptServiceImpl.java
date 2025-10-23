package com.xxs.service.impl;

import com.xxs.mapper.DeptMapper;
import com.xxs.pojo.Dept;
import com.xxs.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

//部门信息操作
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    //根据ID删除部门
    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    /*    查询所有的部门数据*/
    @Override
    public List<Dept> findall() {
        return deptMapper.findall();
    }

    //新增部门
    @Override
    public void add(Dept dept) {
        // 1.补全基础属性：createTime、updateTime
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        // 2.调用Mapper接口方法，插入数据
        deptMapper.insert(dept);
    }

    //根据ID查询部门
    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    //根据ID修改部门名称
    @Override
    public void update(Dept dept) {
        //补全基础属性
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.update(dept);
    }
}
