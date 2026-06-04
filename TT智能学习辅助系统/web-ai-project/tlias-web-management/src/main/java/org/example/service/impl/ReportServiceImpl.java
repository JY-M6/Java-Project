package org.example.service.impl;

import org.example.mapper.EmpMapper;
import org.example.mapper.StudentMapper;
import org.example.pojo.ClazzOption;
import org.example.pojo.JobOption;
import org.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;

    //获取员工职位数据
    @Override
    public JobOption getEmpJobData() {
        //1.调用mapper接口获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        //2.将统计数据封装到JobOption对象中
        List<Object> jobList = list.stream().map(dataMap->dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap->dataMap.get("num")).toList();
        return new JobOption(jobList,dataList);
    }

    //获取员工性别数据
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    //获取学生学历数据
    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentMapper.countStudentDegreeData();
    }

    //获取班级人数数据
    @Override
    public ClazzOption getStudentCountData() {
        //1.调用mapper接口获取统计数据
        List<Map<String, Object>> list = studentMapper.countStudentClazzData();
        //2.将统计数据封装到ClazzOption对象中
        List<Object> jobList = list.stream().map(dataMap->dataMap.get("clazz")).toList();
        List<Object> dataList = list.stream().map(dataMap->dataMap.get("num")).toList();
        return new ClazzOption(jobList,dataList);
    }
}
