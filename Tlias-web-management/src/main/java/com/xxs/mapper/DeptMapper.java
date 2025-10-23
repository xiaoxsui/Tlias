package com.xxs.mapper;

import com.xxs.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

//为dept表创建的持久层mapper接口
@Mapper
public interface DeptMapper {

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    //实体类属性名 和 数据库表结构查询返回的字段名一致，会导致无法识别create_time与update_time
    //方式一：手动结果映射
    @Results({
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    //方式二：起别名
//    @Select("select id, name, create_time creatTime, update_time updateTime from dept order by update_time desc")
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findall();

    //新增部门
    @Insert("insert into dept(name, create_time, update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    //根据ID查询部门数据
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getById(Integer id);

    //根据ID修改部门名称
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}

