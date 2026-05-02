package Utils;

import Dto.EmployeeMostDistinctTask;
import Dto.EmployessTopThreeDto;
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

        Map<Integer,List<Records>> groupByTaskId = CLEANlIST.stream()
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
        return LISTGROUPING.stream()
                .max(Comparator.comparingInt(r -> r.totalMinutes()))
                .stream().toList().getFirst();
    }

    public List<TaskTopThreeDto> topThreeRecords(){
        return LISTGROUPING.stream()
                .sorted(Comparator.comparingInt((TaskDto r)-> r.totalMinutes()).reversed())
                .limit(3)
                .map(t -> new TaskTopThreeDto(t.taskId(),t.taskName(),t.percentage()))
                .toList();

    }

    public List<EmployessTopThreeDto> topThreeEmployess(){

        Map<Integer,List<Records>> groupByTaskId = CLEANlIST.stream()
                .collect(Collectors.groupingBy(r -> r.getUserId()));
        List<EmployessTopThreeDto> result = groupByTaskId.entrySet()
                .stream().map(e-> {

                    Integer userId = Integer.valueOf(e.getKey());
                    List<Records> valueList = e.getValue();

                    int totalMinutes = valueList.stream()
                            .mapToInt(r-> r.getMinutes()).sum();

                    String UserName = valueList.get(0).getUserName();

                    return new EmployessTopThreeDto(
                            userId,
                            UserName,
                            totalMinutes
                    );
                })
                .sorted(Comparator.comparingInt((EmployessTopThreeDto r)->r.totalMinutes())
                        .reversed()).limit(3).toList();
        return  result;

    }

    public List<EmployeeMostDistinctTask> distinctTasks (){
        List<Records> teste = CLEANlIST;
        System.out.println(teste);

        return List.of();
    }

}
