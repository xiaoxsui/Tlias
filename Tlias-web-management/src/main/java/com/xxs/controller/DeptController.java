package com.xxs.controller;

import com.xxs.pojo.Dept;
import com.xxs.pojo.Result;
import com.xxs.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//控制层的请求处理类
@RestController
/*将类标记为一个 RESTful 控制器
* 包含@Controller与@ResponseBody注解*/
public class DeptController {

    @Autowired
    private DeptService deptService;

    //controller方法的返回值，定义的是最终需要给前端响应的数据——使用统一数据格式result
/*    @RequestMapping(value = "/depts",method = RequestMethod.GET)
    //设置映射请求请求路径的具体信息，同时限定该方法的请求方式为GET*/
    @GetMapping("/depts")   //@RequestMapping的衍生注解，直接限定了请求方式为GET
    public Result list(){
        System.out.println("查询全部的部门数据");
        List<Dept> deptList = deptService.findall();
        return Result.success(deptList);
    }
}
