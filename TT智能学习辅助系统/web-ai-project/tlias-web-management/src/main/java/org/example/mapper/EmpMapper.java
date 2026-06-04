package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

//员工信息
@Mapper
public interface EmpMapper {
    //------------------原始分页查询实现------------------
    /*//查询总记录数
    @Select("select count(*) from emp")
    public Long count();
    //分页查询
    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id "+
            "order by e.update_time desc limit #{start},#{pageSize}")
    public List<Emp> list(Integer start, Integer pageSize);*/
    //------------------分页插件实现------------------
    /*@Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id "+
            "order by e.update_time desc")
    public List<Emp> list();*/
    //------------------条件分页插件实现------------------
    //@Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id "+
    //        "where e.name like concat('%',#{name},'%') and e.gender = #{gender} and e.entry_date between #{begin} and #{end} "+
    //        "order by e.update_time desc")

    //public List<Emp> list(String name , Integer gender, LocalDate begin, LocalDate end);
    List<Emp> list(EmpQueryParam empQueryParam);

    //添加员工
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username,password,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time) " +
            "values(#{username},#{password},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    public void insert(Emp emp);

    @Select("select * from emp")
    List<Emp> listName();

    //批量删除员工
    void deleteByIds(List<Integer> ids);

    //根据ID查询员工信息
    Emp selectById(Integer id);

    //更新员工信息
    void updateById(Emp emp);

    //统计员工职位人数
    List<Map<String, Object>> countEmpJobData();

    //统计员工性别人数
    List<Map<String, Object>> countEmpGenderData();

    //根据用户名和密码查询员工信息
    @Select("select id,username,name from emp where username = #{username} and password = #{password}")
    Emp login(Emp emp);
}
