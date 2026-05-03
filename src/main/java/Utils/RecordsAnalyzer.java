package Utils;

import Dto.EmployeeMostDistinctTask;
import Dto.EmployeesTopThreeDto;
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
    public final List<Records> CLEANlIST = this.validRecords();
    public final List<TaskDto> LISTGROUPING = this.groupingByTaskId();


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

        int totalMinutesAll = this.countTotalMinutes();
        Map<Integer,List<Records>> groupByTaskId = CLEANlIST.stream()
                .collect(Collectors.groupingBy(r -> r.getTaskId()));
        List<TaskDto> result = groupByTaskId.entrySet()
                .stream().map(e-> {
                    Integer taskId = e.getKey();
                    List<Records> valueList = e.getValue();

                    int totalMinutes = valueList.stream()
                            .mapToInt(r-> r.getMinutes()).sum();

                    String taskName = valueList.get(0).getTaskName();
                    double perc = totalMinutesAll == 0 ? 0 : (totalMinutes * 100.0) / totalMinutesAll;

                    return new TaskDto(
                            taskId,
                            taskName,
                            totalMinutes,
                            String.format(Locale.US,"%.2f%%", perc)
                    );

                })
                .sorted(Comparator
                        .comparing((TaskDto t) -> t.totalMinutes()).reversed()
                        .thenComparing((TaskDto t) -> t.taskId())).toList();
        return  result;
    }

    public TaskDto mostWorkedTask(){
        return LISTGROUPING.getFirst();
    }

    public List<TaskTopThreeDto> topThreeRecords(){
        return LISTGROUPING.stream()
                .limit(3)
                .map(t -> new TaskTopThreeDto(t.taskId(),t.taskName(),t.percentage()))
                .toList();

    }

    public List<EmployeesTopThreeDto> topThreeEmployess(){

        Map<Integer,List<Records>> groupByTaskId = CLEANlIST.stream()
                .collect(Collectors.groupingBy(r -> r.getUserId()));
        List<EmployeesTopThreeDto> result = groupByTaskId.entrySet()
                .stream().map(e-> {

                    Integer userId = Integer.valueOf(e.getKey());
                    List<Records> valueList = e.getValue();

                    int totalMinutes = valueList.stream()
                            .mapToInt(r-> r.getMinutes()).sum();

                    String UserName = valueList.get(0).getUserName();

                    return new EmployeesTopThreeDto(
                            userId,
                            UserName,
                            totalMinutes
                    );
                })
                .sorted(Comparator.comparing((EmployeesTopThreeDto r)->r.totalMinutes())
                        .reversed().thenComparing((EmployeesTopThreeDto r)->r.userId()))
                .limit(3).toList();
        return  result;

    }

    public EmployeeMostDistinctTask mostDistinctTasks (){

        Map<Integer,List<Records>> groups = CLEANlIST.stream()
                        .collect(Collectors.groupingBy(r->r.getUserId()));

        EmployeeMostDistinctTask ListDistinct = groups.entrySet().stream()
                .map(entry -> {

                    Integer userId = entry.getKey();
                    List<Records> records = entry.getValue();
                    String userName = records.get(0).getUserName();

                    List<Integer> taskIds = records.stream()
                            .map(r -> r.getTaskId()).distinct()
                            .sorted()
                            .toList();

                    Integer total = taskIds.size();

                    return new EmployeeMostDistinctTask(userId,userName,total,taskIds);
                }).sorted(
                        Comparator
                                .comparing((EmployeeMostDistinctTask t) -> t.distinctTasks()).reversed()
                                .thenComparing((EmployeeMostDistinctTask t) -> t.userId())
                )
                .toList().getFirst();
        return ListDistinct;
    }

}
