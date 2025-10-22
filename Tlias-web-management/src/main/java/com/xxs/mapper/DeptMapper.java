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
//    @Select("select id, name, creat_time creatTime, update_time updateTime from dept order by update_time desc")
    @Select("select id, name, creat_time, update_time from dept order by update_time desc")
    List<Dept> findall();


}

