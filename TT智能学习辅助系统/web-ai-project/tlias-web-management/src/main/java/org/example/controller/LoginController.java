package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.Emp;
import org.example.pojo.EmpLoginLog;
import org.example.pojo.LoginInfo;
import org.example.pojo.Result;
import org.example.service.EmpService;
import org.example.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;
    @Autowired
    private LogService logService;


    /*@RequestMapping("/login")
    public Result login(@RequestBody Emp emp) {
        long start = System.currentTimeMillis();
        log.info("登录: {}", emp);
        LoginInfo info = empService.login(emp);
        long end = System.currentTimeMillis();
        long costTime = end - start;
        log.info("登录耗时: {}ms", costTime);
        EmpLoginLog empLoginLog = new EmpLoginLog();
        empLoginLog.setUsername(emp.getUsername());
        empLoginLog.setPassword(emp.getPassword());
        short isSuccess;
        if (info.getToken() != null)
            isSuccess = 1;
        else    isSuccess = 0;
        empLoginLog.setIsSuccess(isSuccess);
        empLoginLog.setLoginTime(LocalDateTime.now());
        empLoginLog.setJwt(info.getToken());
        empLoginLog.setCostTime(costTime);
        logService.insertLoginLog(empLoginLog);
        if (info == null) {
            return Result.error("用户名或密码错误");
        }
        return Result.success(info);
    }*/

    @RequestMapping("/login")
    public Result login(@RequestBody Emp emp) {
        long start = System.currentTimeMillis();
        log.info("登录：{}", emp);
        LoginInfo info = empService.login(emp);
        long end = System.currentTimeMillis();
        long costTime = end - start;
        log.info("登录耗时:{}ms", costTime);

        if (info == null) {
            // 登录失败，记录日志
            EmpLoginLog empLoginLog = new EmpLoginLog();
            empLoginLog.setUsername(emp.getUsername());
            empLoginLog.setPassword(emp.getPassword());
            empLoginLog.setIsSuccess((short) 0);
            empLoginLog.setLoginTime(LocalDateTime.now());
            empLoginLog.setCostTime(costTime);
            logService.insertLoginLog(empLoginLog);
            return Result.error("用户名或密码不正确");
        }

        // 登录成功，记录日志
        EmpLoginLog empLoginLog = new EmpLoginLog();
        empLoginLog.setUsername(emp.getUsername());
        empLoginLog.setPassword(emp.getPassword());
        empLoginLog.setIsSuccess((short) 1);
        empLoginLog.setLoginTime(LocalDateTime.now());
        empLoginLog.setJwt(info.getToken());
        empLoginLog.setCostTime(costTime);
        logService.insertLoginLog(empLoginLog);
        return Result.success(info);
    }
}
