package org.example.service.impl;

import org.example.exception.BusinessException;
import org.example.mapper.DeptMapper;
import org.example.pojo.Dept;
import org.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> findAll(){
        return deptMapper.findAll();
    }

    //删除部门
    @Override
    public void deleteById(Integer id) {
        //先检查该部门下是否有员工
        int empCount = deptMapper.countEmpByDeptId(id);
        if (empCount > 0) {
            throw new BusinessException("对不起，当前部门下有员工，不能直接删除！");
        }
        //如果没有员工，则执行删除
        deptMapper.deleteById(id);
    }

    @Override
    public void addDept(Dept dept) {
        //补全基础属性
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //调用mapper方法
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        //补全基础属性
        dept.setUpdateTime(LocalDateTime.now());
        //调用mapper方法
        deptMapper.update(dept);
    }
}
