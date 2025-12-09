package com.xxs.mapper;

import com.xxs.pojo.Emp;
import com.xxs.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

//操作员工基本信息
@Mapper
public interface EmpMapper {

    /*    ————————————————原始分页查询实现方式——————————————————*/
/*    //查询总记录数
    @Select("select count(*) from emp left join dept on emp.dept_id = dept.id")
    public long count();

    //分页查询
    @Select("select emp.*, dept.name deptName from emp left join dept on emp.dept_id = dept.id" +
            " order by emp.update_time desc limit #{start},#{pageSize}")
    public List<Emp> list(Integer start, Integer pageSize);*/

//    @Select("select emp.*, dept.name deptName from emp left join dept on emp.dept_id = dept.id order by emp.update_time")
//条件分页查询
//    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    //条件分页查询-代码优化
    public List<Emp> list(EmpQueryParam empQueryParam);

    //新增员工基本信息
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);
}
