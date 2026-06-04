package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.StudentMapper;
import org.example.pojo.Clazz;
import org.example.pojo.PageResult;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    //查询所有学生数据(分页查询)
    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        //2.执行查询
        List<Student> list = studentMapper.list(studentQueryParam);
        //3.返回结果
        Page<Student> p = (Page<Student>) list;
        return new PageResult<Student>(p.getTotal(),p.getResult());
    }

    //添加学生数据
    @Override
    public void addStudent(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    //根据ID查询学生信息
    @Override
    public Student getStudent(Integer id) {
        return studentMapper.selectById(id);
    }

    //修改学生信息
    @Override
    public void updateStudent(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void deleteStudent(Integer id) {
        studentMapper.delete(id);
    }

    //学生违纪处理
    @Override
    public void violation(Integer id, Integer score) {
        //1.根据ID查询学生信息
        Student student = studentMapper.selectById(id);
        //2.更新学生信息
        student.setViolationCount((short) (student.getViolationCount() + 1));
        student.setViolationScore((short) (student.getViolationScore() + score));
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.updateVoiolation(student);
    }
}
