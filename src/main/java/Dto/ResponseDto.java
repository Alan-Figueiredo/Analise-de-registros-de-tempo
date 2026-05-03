package Dto;

import java.util.List;

public record ResponseDto(
        Integer totalMinutes,
        List<TaskDto> tasks,
        TaskDto mostWorkedTask,
        List<TaskTopThreeDto> top3TasksPercentage,
        List<EmployeesTopThreeDto> top3Employees,
        EmployeeMostDistinctTask mostDistinctUserOnTasks,
        long ignoredRecords
) {}
