package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.exception.BusinessException;
import org.example.mapper.ClazzMapper;
import org.example.pojo.*;
import org.example.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    //查询所有班级数据(分页查询)
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        //2.执行查询
        List<Clazz> list = clazzMapper.list(clazzQueryParam);
        //3.返回结果
        Page<Clazz> p = (Page<Clazz>) list;
        //循环设置状态
        for (int i = 0; i < p.getResult().size(); i++) {
            if (p.getResult().get(i).getEndDate().isBefore(ChronoLocalDate.from(LocalDateTime.now()))) {
                p.getResult().get(i).setStatus("已结课");
            } else if (p.getResult().get(i).getEndDate().isAfter(ChronoLocalDate.from(LocalDateTime.now())) && p.getResult().get(i).getBeginDate().isBefore(ChronoLocalDate.from(LocalDateTime.now()))) {
                p.getResult().get(i).setStatus("在读");
            } else {
                p.getResult().get(i).setStatus("未开班");
            }
        }
        return new PageResult<Clazz>(p.getTotal(),p.getResult());
    }

    //添加班级
    @Override
    public void addClazz(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    //删除班级
    @Override
    public void deleteClazz(Integer id) {
        //删除班级前，先检查班级下是否有学生
        int studentCount = clazzMapper.countStudentByClazzId(id);
        if (studentCount > 0) {
            throw new BusinessException("对不起，当前班级下有学生，不能直接删除！");
        } else if (getClazz(id).getBeginDate().isBefore(ChronoLocalDate.from(LocalDateTime.now()))
                &&getClazz(id).getEndDate().isAfter(ChronoLocalDate.from(LocalDateTime.now()))) {
            throw new BusinessException("对不起，当前班级正在进行中，不能直接删除！");
        }
        //如果没有学生，则执行删除
        clazzMapper.delete(id);
    }

    //获取班级信息
    @Override
    public Clazz getClazz(Integer id) {
        return clazzMapper.selectById(id);
    }

    //修改班级信息
    @Override
    public void updateClazz(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    //查询班级信息
    @Override
    public List<Clazz> listClazz() {
        List<Clazz> list = clazzMapper.listAll(new Clazz());
        return list;
    }

}
