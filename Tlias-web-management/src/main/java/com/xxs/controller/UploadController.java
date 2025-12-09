package com.xxs.controller;

import com.xxs.pojo.Result;
import com.xxs.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    /*本地磁盘存储方案*/
    /*@PostMapping("/upload")
    public Result Upload(String name, Integer age, MultipartFile file) throws IOException {
        log.info("文件上传接收参数：{},{},{}",name,age,file);
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        //为防止文件名重复，拼接出新的文件名
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));   //从后向前索引最后一个'.'，来获取原文件扩展名
        String newFileName = UUID.randomUUID().toString() + extension;  //使用唯一不重复的UUID进行新文件名的拼接，解决文件名重复的问题
        //保存文件
        file.transferTo(new File("D:/test/" + newFileName));
        return Result.success();
    }*/

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception{
        log.info("文件上传：{}",file.getOriginalFilename());
        //将文件交给OSS，云端存储管理
        String url = aliyunOSSOperator.upload(file.getBytes(), Objects.requireNonNull(file.getOriginalFilename()));
        log.info("文件上传OSS，url：{}",url);
        return Result.success(url);

    }
}
