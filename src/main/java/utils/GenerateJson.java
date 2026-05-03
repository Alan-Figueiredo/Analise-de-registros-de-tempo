package utils;

import dto.ResponseDto;
import tools.jackson.core.util.DefaultIndenter;
import tools.jackson.core.util.DefaultPrettyPrinter;
import tools.jackson.databind.ObjectMapper;


import java.io.File;

public class GenerateJson {

    private final ObjectMapper mapper = new ObjectMapper();
    private  final RecordsAnalyzer analyzer = new RecordsAnalyzer();

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

    public void generateJsonResult() {

        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        printer.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);

        mapper.writerWithDefaultPrettyPrinter()
                .with(printer)
                .writeValue(new File("result.json"), this.buildJson());
    }
}
