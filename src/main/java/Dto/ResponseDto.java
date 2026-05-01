package Dto;

import java.util.List;

public record ResponseDto(
        int totalMinutes,
        List<GroupingRecordsTaskIdDto> tasks
) {}
