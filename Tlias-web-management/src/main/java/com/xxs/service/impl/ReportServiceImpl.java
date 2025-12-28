package com.xxs.service.impl;

import com.xxs.mapper.EmpMapper;
import com.xxs.pojo.JobOption;
import com.xxs.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    //统计员工职位人数
    @Override
    public JobOption getEmpJobData() {
        //1. 调用Mapper接口，获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();   //map: pos=教研主管,num=1

        //2. 组装结果，并返回
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> numList = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(jobList,numList);
    }

    //统计员工性别人数
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }
}
