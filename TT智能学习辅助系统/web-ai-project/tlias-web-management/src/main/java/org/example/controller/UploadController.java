package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Result;
import org.example.utils.AliyunOSSOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    //本地磁盘存储方案
    /*@PostMapping("/upload")
    public Result handleFileUpload(String name,Integer age,MultipartFile file) throws IOException {
        log.info("文件上传:{},{},{}",name,age,file);
        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        //新的文件名
        String extention = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + extention;
        //保存文件
        file.transferTo(new File("D:/JavaWeb/images/" + newFileName));
        return Result.success();
    }*/

    //阿里云OSS存储方案
    @Autowired
    private AliyunOSSOperator AliyunOSSOperator;
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传:{}",file.getOriginalFilename());
        //阿里云OSS存储
        String url = AliyunOSSOperator.upload(file.getBytes(),file.getOriginalFilename());
        log.info("文件上传成功:{}",url);
        return Result.success(url);
    }
}
