package com.dkd.manage.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class TaskDto {
    private Long createType;//创建类型
    private String innerCode;//售货机编号
    private Long userId;//执行人id
    private Long assignorId;//指派人id
    private Long productTypeId;//工单类型
    private String desc;//任务描述
    private List<TaskDetailsDto> details;//工单详情（只有补货工才涉及）
}
