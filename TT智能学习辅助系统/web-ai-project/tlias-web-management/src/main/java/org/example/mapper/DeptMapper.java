package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {
    //方式一：使用@Results注解
//    @Results({
//            @Result(column = "create_time",property = "createTime"),
//            @Result(column = "update_time",property = "updateTime")
//    })
    //方式二：使用别名
    //@Select("select id,name,create_time createTime,update_time updateTime from dept order by update_time desc")
    //查询所有部门数据
    //方式三：使用驼峰命名映射
    @Select("select id,name,create_time,update_time from dept order by update_time desc")
    public List<Dept> findAll();

    @Delete("delete from dept where id=#{id}")
    public void deleteById(Integer id);

    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    public void insert(Dept dept);

    //根据id查询部门
    @Select("select id,name,create_time,update_time from dept where id=#{id}")
    public Dept getById(Integer id);

    //修改部门
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id}")
    public void update(Dept dept);

    //查询指定部门下的员工数量
    @Select("select count(*) from emp where dept_id = #{deptId}")
    public int countEmpByDeptId(Integer deptId);
}
