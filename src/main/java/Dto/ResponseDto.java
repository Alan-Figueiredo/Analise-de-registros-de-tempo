package Dto;

import Model.Records;

import java.util.List;

public record ResponseDto(
        Integer totalMinutes,
        List<GroupRerdsTaskIdDto> tasks,
        GroupRerdsTaskIdDto mostWorkedTask,
        long ignoredRecords
) {}

//- top3TasksPercentage
//- top3Employees
//- mostDistinctUserOnTasks
