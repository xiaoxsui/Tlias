package com.xxs.service.impl;

import com.xxs.mapper.DeptMapper;
import com.xxs.pojo.Dept;
import com.xxs.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
