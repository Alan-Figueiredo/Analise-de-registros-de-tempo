package dto;

public record TaskDto(
       Integer taskId,
       String taskName,
       Integer totalMinutes,
       String percentage

) {
}
