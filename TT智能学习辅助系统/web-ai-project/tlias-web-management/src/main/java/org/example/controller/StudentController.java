package org.example.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.anno.Log;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    //查询所有学生数据(分页查询)
    @GetMapping
    public Result list(StudentQueryParam studentQueryParam) {
        log.info("分页查询,参数:{}", studentQueryParam);
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    //添加学生
    @PostMapping
    @Log
    public Result add(@RequestBody Student student) {
        log.info("添加学生,参数:{}", student);
        studentService.addStudent(student);
        return Result.success();
    }

    //根据ID查询学生信息
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询员工：{}", id);
        Student student = studentService.getStudent(id);
        return Result.success(student);
    }

    //修改学生信息
    @PutMapping
    @Log
    public Result update(@RequestBody Student student) {
        log.info("根据ID修改学生信息,参数:{}", student);
        studentService.updateStudent(student);
        return Result.success();
    }

    //根据ID删除学生信息
    @DeleteMapping("/{id}")
    @Log
    public Result delete(@PathVariable Integer id) {
        log.info("根据ID删除学生信息,参数:{}", id);
        studentService.deleteStudent(id);
        return Result.success();
    }

    //违纪处理
    @PutMapping("/violation/{id}/{score}")
    @Log
    public Result violation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("违纪处理,参数:{},{}", id, score);
        studentService.violation(id, score);
        return Result.success();
    }
}
