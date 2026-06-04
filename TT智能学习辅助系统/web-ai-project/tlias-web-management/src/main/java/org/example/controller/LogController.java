package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.*;
import org.example.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/log")
@RestController
public class LogController {
    @Autowired
    private LogService LogService;

    @GetMapping("/page")
    public Result list(OperateLog operateLog) {
        log.info("分页查询,参数:{}",operateLog);
        PageResult<OperateLog> pageResult = LogService.page(operateLog);
        return Result.success(pageResult);
    }

}
