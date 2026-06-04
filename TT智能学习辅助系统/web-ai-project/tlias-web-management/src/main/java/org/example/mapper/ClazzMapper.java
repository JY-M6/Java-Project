package org.example.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.Emp;

import java.util.List;

@Mapper
public interface ClazzMapper {

    //查询所有班级数据(分页查询)
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    //添加班级数据
    //@Insert("insert into clazz(name,room,begin_date,end_date,master_id,subject,create_time,update_time) values(#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    public void insert(Clazz clazz);

    //删除班级数据
    @Delete("delete from clazz where id = #{id}")
    void delete(Integer id);

    //根据ID查询班级数据
    @Select("select * from clazz where id = #{id}")
    Clazz selectById(Integer id);

    //更新班级数据
    void update(Clazz clazz);

    //查询所有班级数据
    @Select("select * from clazz")
    List<Clazz> listAll(Clazz clazz);

    //查询指定班级下的学生数量
    @Select("select count(*) from student where clazz_id = #{clazzId} ")
    public int countStudentByClazzId(Integer clazzId);
}
