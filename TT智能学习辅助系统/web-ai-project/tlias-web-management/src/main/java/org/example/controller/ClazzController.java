package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.anno.Log;
import org.example.pojo.*;
import org.example.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    //查询所有班级数据(分页查询)
    @GetMapping
    public Result list(ClazzQueryParam clazzQueryParam){
        log.info("分页查询,参数:{}",clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    //添加班级
    @Log
    @PostMapping
    public Result add(@RequestBody Clazz clazz) {
        log.info("添加班级数据：{}", clazz);
        clazzService.addClazz(clazz);
        return Result.success();
    }

    //删除班级
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除班级数据：{}", id);
        clazzService.deleteClazz(id);
        return Result.success();
    }

    //查询班级数据
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id) {
        log.info("查询班级数据：{}", id);
        Clazz clazz = clazzService.getClazz(id);
        return Result.success(clazz);
    }

    //修改班级数据
    @Log
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级数据：{}", clazz);
        clazzService.updateClazz(clazz);
        return Result.success();
    }

    //查询所有班级
    @GetMapping("/list")
    public Result listClazz() {
        log.info("查询所有班级：");
        List<Clazz> list = clazzService.listClazz();
        return Result.success(list);
    }
}
