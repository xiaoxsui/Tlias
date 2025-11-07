package com.xxs.mapper;

import com.xxs.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//操作员工基本信息
@Mapper
public interface EmpMapper {

    //查询总记录数
    @Select("select count(*) from emp left join dept on emp.dept_id = dept.id")
    public long count();

    //分页查询
    @Select("select emp.*, dept.name deptName from emp left join dept on emp.dept_id = dept.id" +
            " order by emp.update_time desc limit #{start},#{pageSize}")
    public List<Emp> list(Integer start, Integer pageSize);
}
