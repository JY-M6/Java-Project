package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    //根据查询参数查询学生列表
    List<Student> list(StudentQueryParam studentQueryParam);

    //添加学生数据
    @Insert("insert into student(name,no,gender,phone,id_card,is_college,address,degree,graduation_date,clazz_id,create_time,update_time) " +
            "values(#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{createTime},#{updateTime})")
    void insert(Student student);

    //根据ID查询学生信息
    @Select("select * from student where id = #{id}")
    Student selectById(Integer id);

    //修改学生数据
    void update(Student student);

    //根据ID删除学生数据
    @Select("delete from student where id = #{id}")
    void delete(Integer id);

    //修改学生违规信息
    @Update("update student set update_time = #{updateTime}, " +
            "violation_count = #{violationCount}, violation_score = #{violationScore} where id = #{id}")
    void updateVoiolation(Student student);

    //统计学生学历数据
    List<Map<String, Object>> countStudentDegreeData();

    //统计班级人数数据
    List<Map<String, Object>> countStudentClazzData();
}
