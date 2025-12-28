package com.xxs.mapper;

import com.xxs.pojo.Emp;
import com.xxs.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    //根据id查询员工信息以及工作经历信息
    Emp getById(Integer id);

    //根据id更新员工的基本信息
    void updateById(Emp emp);

    //统计员工人数
    @MapKey("pos")  //不需要声明，只解决mybatisX插件的误报错误
    List<Map<String, Object>> countEmpJobData();

    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    //根据用户名和密码查询员工信息
    @Select("select id, username, name from emp where username=#{username} and password=#{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
