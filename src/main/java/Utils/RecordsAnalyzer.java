package Utils;

import Dto.TaskDto;
import Dto.TaskTopThreeDto;
import Model.Records;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class RecordsAnalyzer {

    public static final DataProcessing DP = new DataProcessing();


    public Integer countTotalMinutes() {
        return this.validRecords().stream()
                .mapToInt(r -> r.getMinutes()).sum();
    }

    public List<Records> validRecords() {
        return DP.getListRecords().stream()
                .filter(r -> r.getMinutes() != null && r.getMinutes() > 0)
                .toList();
    }

    public long countIgnoredRecords() {
        return DP.getListRecords().stream()
                .filter(r -> r.getMinutes() == null || r.getMinutes() <= 0)
                .count();
    }

    public List<TaskDto> groupingByTaskId() {

        List<Records> cleanList = this.validRecords();

        Map<Integer,List<Records>> groupByTaskId = cleanList.stream()
                .collect(Collectors.groupingBy(r -> r.getTaskId()));

        List<TaskDto> result = groupByTaskId.entrySet()
                .stream().map(e-> {
                    Integer taskId = e.getKey();
                    List<Records> valueList = e.getValue();

                    int totalMinutes = valueList.stream()
                            .mapToInt(r-> r.getMinutes()).sum();

                    String taskName = valueList.get(0).getTaskName();
                    double perc = this.countTotalMinutes() == 0 ? 0 : (totalMinutes * 100.0) / this.countTotalMinutes();

                    return new TaskDto(
                            taskId,
                            taskName,
                            totalMinutes,
                            String.format(Locale.US,"%.2f%%", perc)
                    );

                })
                .toList();
        return  result;
    }

    public TaskDto mostWorkedTask(){

        List<TaskDto> listGrouping = groupingByTaskId();

        return listGrouping.stream()
                .max(Comparator.comparingInt(r -> r.totalMinutes()))
                .stream().toList().getFirst();
    }

    public List<TaskTopThreeDto> topThreeRecords(){

        List<TaskDto> cleanList = this.groupingByTaskId();

        return cleanList.stream()
                .sorted(Comparator.comparingInt((TaskDto r)-> r.totalMinutes()).reversed())
                .limit(3)
                .map(t -> new TaskTopThreeDto(t.taskId(),t.taskName(),t.percentage()))
                .toList();

    }
}
