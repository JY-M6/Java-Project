package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.LogMapper;
import org.example.pojo.*;
import org.example.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper LogMapper;
    @Override
    public PageResult<OperateLog> page(OperateLog operateLog) {
        //1.设置分页参数
        PageHelper.startPage(operateLog.getPage(), operateLog.getPageSize());
        //2.执行查询
        List<OperateLog> list = LogMapper.page(operateLog);
        //3.返回结果
        Page<OperateLog> p = (Page<OperateLog>) list;
        return new PageResult<OperateLog>(p.getTotal(), p.getResult());
    }

    @Override
    public void insertLoginLog(EmpLoginLog empLoginLog) {
        empLoginLog.setLoginTime(LocalDateTime.now());

        LogMapper.insert(empLoginLog);
    }

}
