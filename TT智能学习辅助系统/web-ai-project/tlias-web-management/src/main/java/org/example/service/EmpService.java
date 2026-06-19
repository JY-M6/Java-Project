package org.example.service;


import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.LoginInfo;
import org.example.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;


public interface EmpService {

    //PageResult<Emp> page(Integer page, Integer pageSize,String name, Integer gender, LocalDate begin,LocalDate end);
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    List<Emp> listName();

    void delete(List<Integer> ids);

    Emp getById(Integer id);

    void update(Emp emp);

    public LoginInfo login(Emp emp);

    boolean updatePassword(Integer empId, String oldPassword, String newPassword);
}
