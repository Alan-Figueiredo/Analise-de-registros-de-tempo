package dto;

public record EmployeesTopThreeDto(
        Integer userId,
        String userName,
        Integer totalMinutes
) {
}