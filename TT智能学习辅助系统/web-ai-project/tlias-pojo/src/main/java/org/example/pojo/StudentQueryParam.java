package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentQueryParam {
    private String name;// 姓名
    private Integer degree;// 学历
    private Integer clazzId;// 班级ID
    private Integer page  = 1;//当前页码
    private Integer pageSize = 10;//每页记录数
}
