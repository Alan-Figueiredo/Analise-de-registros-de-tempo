package Dto;

import java.util.List;

public record ResponseDto(
        Integer totalMinutes,
        List<TaskDto> tasks,
        TaskDto mostWorkedTask,
        List<TaskTopThreeDto> top3TasksPercentage,
        List<EmployessTopThreeDto>top3Employees,

        long ignoredRecords
) {}

//- mostDistinctUserOnTasks
