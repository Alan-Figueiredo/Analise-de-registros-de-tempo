package Utils;

import Dto.ResponseDto;
import tools.jackson.core.util.DefaultIndenter;
import tools.jackson.core.util.DefaultPrettyPrinter;
import tools.jackson.databind.ObjectMapper;


import java.io.File;

public class GenerateJson {

    private final ObjectMapper MAPPER = new ObjectMapper();
    private  final RecordsAnalyzer RA = new RecordsAnalyzer();

    public ResponseDto buildJson(){
        return new ResponseDto(
                RA.countTotalMinutes(),
                RA.groupingByTaskId(),
                RA.mostWorkedTask(),
                RA.topThreeRecords(),
                RA.topThreeEmployess(),
                RA.mostDistinctTasks(),
                RA.countIgnoredRecords()
        );
    }

    public void generateJsonResult() {

        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        printer.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);

        MAPPER.writerWithDefaultPrettyPrinter()
                .with(printer)
                .writeValue(new File("result.json"), this.buildJson());
    }
}
