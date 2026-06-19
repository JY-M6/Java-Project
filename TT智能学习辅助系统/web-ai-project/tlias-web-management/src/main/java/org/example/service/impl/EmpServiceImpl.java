package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.EmpExprMapper;
import org.example.mapper.EmpMapper;
import org.example.pojo.*;
import org.example.service.EmpLogService;
import org.example.service.EmpService;
import org.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    //------------------原始分页查询实现------------------
    /*@Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        return new PageResult<Emp>(empMapper.count(),empMapper.list((page-1)*pageSize,pageSize));
    }*/
    //------------------分页插件实现------------------
    @Override
    //public PageResult<Emp> page(Integer page, Integer pageSize,String name, Integer gender, LocalDate begin, LocalDate end) {
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //2.执行查询
        //List<Emp> list = empMapper.list(empQueryParam);
        List<Emp> list = empMapper.list(empQueryParam);
        //3.返回结果
        Page<Emp> p = (Page<Emp>) list;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    //保存员工
    @Transactional(rollbackFor = Exception.class)//添加事务,遇到异常回滚
    @Override
    public void save(Emp emp) {
        try {
            //1.保存员工信息
            emp.setUpdateTime(LocalDateTime.now());
            emp.setCreateTime(LocalDateTime.now());
            empMapper.insert(emp);
            //2.保存员工工作经历
            List<EmpExpr> empExprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(empExprList)) {
                //设置员工ID
                //empExprList.forEach(empExpr -> {empExpr.setEmpId(emp.getId());});
                empExprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
                empExprMapper.insertBatch(empExprList);
            }
        } finally {
            //3.保存操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "保存员工" + emp);
            empLogService.insertLog(empLog);
        }

    }

    @Override
    public List<Emp> listName() {
        return empMapper.listName();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Integer> ids) {
        //1.批量删除员工
        empMapper.deleteByIds(ids);
        //2.批量删除员工工作经历
        empExprMapper.deleteByEmpIds(ids);
    }

    //根据ID查询员工以及工作经历
    @Override
    public Emp getById(Integer id) {
        Emp emp = empMapper.selectById(id);
        return emp;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        //1.更新员工信息
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.updateById(emp);
        //2.更新员工工作经历
        //2.1 删除员工工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //2.2 添加员工工作经历
        List<EmpExpr> empExprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(empExprList)) {
            //设置员工ID
            empExprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(empExprList);
        }
    }

    @Override
    public LoginInfo login(Emp emp) {
        //1.根据用户名查询员工数据
        Emp loginEmp = empMapper.login(emp);
        //2.判断员工是否存在,如果存在，组装登录信息返回
        if (loginEmp != null) {
            log.info("员工登录: {}", loginEmp);
            //生成JWT令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginEmp.getId());
            claims.put("username", loginEmp.getUsername());
            String token = JwtUtils.generateToken(claims);
            LoginInfo loginInfo = new LoginInfo(loginEmp.getId(), loginEmp.getUsername(), loginEmp.getName(), token);
            return loginInfo;
        }
        //3.如果员工不存在，返回null
        return null;
    }

    @Override
    public boolean updatePassword(Integer empId, String oldPassword, String newPassword) {
        Emp emp = empMapper.selectById(empId);
        if (emp != null && emp.getPassword().equals(oldPassword)) {
            Emp updateEmp = new Emp();
            updateEmp.setId(empId);
            updateEmp.setPassword(newPassword);
            updateEmp.setUpdateTime(LocalDateTime.now());
            empMapper.updateById(updateEmp);
            return true;
        }
        return false;
    }
}