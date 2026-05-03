package utils;

import config.DataSource;
import dto.ResponseDto;


public class GenerateJson {


    private final RecordsAnalyzer analyzer;
    private final DataSource dataSource;

    public GenerateJson(RecordsAnalyzer analyzer, DataSource dataSource) {
        this.analyzer = analyzer;
        this.dataSource = dataSource;
    }

    private ResponseDto buildJson(){
        return new ResponseDto(
                analyzer.countTotalMinutes(),
                analyzer.groupingByTaskId(),
                analyzer.mostWorkedTask(),
                analyzer.topThreeRecords(),
                analyzer.topThreeEmployess(),
                analyzer.mostDistinctTasks(),
                analyzer.countIgnoredRecords()
        );
    }

    public void exportJson(){
        ResponseDto responseDto = buildJson();
        dataSource.save(responseDto);
    }
}
