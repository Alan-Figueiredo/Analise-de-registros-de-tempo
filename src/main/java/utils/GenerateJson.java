package utils;

import config.DataSource;
import dto.ResponseDto;


public class GenerateJson {


    private final RecordsAnalyzer analyzer;
    private final DataSource adapter;

    public GenerateJson(RecordsAnalyzer analyzer, DataSource adapter) {
        this.analyzer = analyzer;
        this.adapter = adapter;
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
        adapter.save(responseDto);
    }
}
