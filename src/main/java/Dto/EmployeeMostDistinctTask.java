package Dto;

import java.util.List;

public record EmployeeMostDistinctTask(
        Integer userId,
        String userName,
        Integer distinctTasks,
        List<Integer> taskIds
) {
}
