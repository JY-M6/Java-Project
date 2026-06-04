package org.example.service;

import org.example.pojo.Dept;

import java.util.List;

public interface DeptService {
    //查询所有部门
    public List<Dept> findAll();

    public void deleteById(Integer id);

    public void addDept(Dept dept);

    public Dept getById(Integer id);

    public void update(Dept dept);
}
