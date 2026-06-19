package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.anno.Log;
import org.example.pojo.*;
import org.example.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    //分页查询
    /*@GetMapping
    public Result list(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Integer gender,
                       @DateTimeFormat (pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat (pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询,参数:{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        PageResult<Emp> pageResult = empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageResult);
    }*/
    //简化版分页查询
    @GetMapping
    public Result list(EmpQueryParam empQueryParam) {
        log.info("分页查询,参数:{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    //新增员工
    @PostMapping
    @Log
    public Result add(@RequestBody Emp emp) {
        log.info("新增员工,参数:{}", emp);
        empService.save(emp);
        return Result.success();
    }

    //查询员工姓名
    @GetMapping("/list")
    public Result listByName() {
        log.info("查询所有班主任姓名：");
        List<Emp> list = empService.listName();
        return Result.success(list);
    }

    //删除员工
    //public Result delete(Integer[] ids){
    @DeleteMapping
    @Log
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除员工：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    //根据ID查询员工
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询员工：{}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    //修改员工信息
    @PutMapping
    @Log
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工: {}", emp);
        empService.update(emp);
        return Result.success();
    }

    //修改密码
    @PutMapping("/password")
    @Log
    public Result updatePassword(@RequestHeader("token") String token, @RequestBody java.util.Map<String, String> params) {
        String oldPassword = params.get("oldPassword");
        String newPassword = params.get("newPassword");
        log.info("修改密码，oldPassword: {}, newPassword: {}", oldPassword, newPassword);
        
        Integer empId = (Integer) org.example.utils.JwtUtils.parseToken(token).get("id");
        boolean success = empService.updatePassword(empId, oldPassword, newPassword);
        if (success) {
            return Result.success();
        } else {
            return Result.error("原始密码错误");
        }
    }
}
