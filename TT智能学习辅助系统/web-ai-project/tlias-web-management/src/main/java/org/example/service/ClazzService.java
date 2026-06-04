package org.example.service;

import org.example.pojo.*;

import java.util.List;

public interface ClazzService {
    public void addClazz(Clazz clazz);

    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    void deleteClazz(Integer id);

    Clazz getClazz(Integer id);

    void updateClazz(Clazz clazz);

    List<Clazz> listClazz();
}
