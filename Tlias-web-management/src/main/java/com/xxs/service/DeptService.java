package com.xxs.service;


import com.xxs.pojo.Dept;

import java.util.List;

public interface DeptService {
    void deleteById(Integer id);

    List<Dept> findall();
}
