package org.example.service;

import org.example.pojo.EmpLoginLog;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.OperateLog;
import org.example.pojo.PageResult;

public interface LogService {
    PageResult<OperateLog> page(OperateLog operateLog);

    public void insertLoginLog(EmpLoginLog empLoginLog);
}
