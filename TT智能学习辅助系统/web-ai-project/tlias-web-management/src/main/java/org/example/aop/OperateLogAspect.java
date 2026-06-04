package org.example.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.mapper.OperateLogMapper;
import org.example.pojo.OperateLog;
import org.example.utils.CurrentHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class OperateLogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(org.example.anno.Log)")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        //获取方法的返回值
        Object result = joinPoint.proceed();

        //获取方法执行耗时
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;

        //将日志数据插入数据库
        OperateLog olog = new OperateLog();
        olog.setOperateEmpId(CurrentHolder.getCurrent().getId());
        olog.setOperateTime(LocalDateTime.now());
        olog.setClassName(joinPoint.getTarget().getClass().getName());
        olog.setMethodName(joinPoint.getSignature().getName());
        olog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        olog.setReturnValue(result != null ? result.toString() : "void");
        olog.setCostTime(costTime);

        //插入数据库
        log.info("插入日志数据：{}", olog);
        operateLogMapper.insert(olog);

        return result;
    }
}
