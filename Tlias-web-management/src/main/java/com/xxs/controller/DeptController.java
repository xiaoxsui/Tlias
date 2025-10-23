package com.xxs.controller;

import com.xxs.pojo.Dept;
import com.xxs.pojo.Result;
import com.xxs.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//使用logback格式记录日志
@Slf4j
//抽取接口的公共路径
@RequestMapping("/depts")
//控制层的请求处理类
@RestController
/*将类标记为一个 RESTful 控制器
* 包含@Controller与@ResponseBody注解*/
public class DeptController {

    @Autowired
    private DeptService deptService;

    //查询部门列表
    //controller方法的返回值，定义的是最终需要给前端响应的数据——使用统一数据格式result
/*    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    //设置映射请求请求路径的具体信息，同时限定该方法的请求方式为GET*/
    @GetMapping  //@RequestMapping的衍生注解，直接限定了请求方式为GET
    public Result list(){
        //System.out.println("查询全部的部门数据");
        log.info("查询全部的部门数据");
        List<Dept> deptList = deptService.findall();
        return Result.success(deptList);
    }

    //删除部门
    @DeleteMapping
    public Result delete(int id){       //请求参数名与形参变量名相同时，可省略@RequestParam注解
        //System.out.println("根据ID删除部门：" + id);
        log.info("根据ID删除部门：{}", id);    //通过占位符{}的写法避免字符串拼接
        deptService.deleteById(id);
        return Result.success();
    }

    //新增部门
    @PostMapping
    public Result add(@RequestBody Dept dept){
        //System.out.println("添加部门：" + dept.getName());
        log.info("添加部门：{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    //根据ID查询部门
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        //System.out.println("根据ID查询部门：" + id);
        log.info("根据ID查询部门：{}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    //根据ID修改部门数据
    @PutMapping
    public Result update(@RequestBody Dept dept){
        //System.out.println("修改部门：" + dept.getId());
        log.info("修改部门：{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
