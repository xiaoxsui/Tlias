package com.xxs.mapper;

import com.xxs.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//为dept表创建的持久层mapper接口
@Mapper
public interface DeptMapper {

    @Select("select id, name, creat_time, update_time from dept order by update_time desc")
    List<Dept> findall();
}

