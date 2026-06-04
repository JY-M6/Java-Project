package org.example.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ClazzQueryParam {
    private Integer page  = 1;//当前页码
    private Integer pageSize = 10;//每页记录数
    private String name ;//姓名
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;//开课日期--开始
    @DateTimeFormat (pattern = "yyyy-MM-dd")
    private LocalDate end;//结课日期--结束
}
