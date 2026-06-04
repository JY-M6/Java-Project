package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.example.pojo.EmpLoginLog;
import org.example.pojo.OperateLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogMapper {
    //分页查询
    List<OperateLog> page(OperateLog operateLog);

    //插入日志数据
    @Insert("insert into emp_login_log (id, username, password, login_time, is_success, jwt, cost_time) " +
            "values (#{id}, #{username}, #{password}, #{loginTime}, #{isSuccess}, #{jwt}, #{costTime})")
    void insert(EmpLoginLog empLoginLog);

}
