package Utils;

import tools.jackson.databind.ObjectMapper;

import java.io.File;

public class GenerateJson {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final DataProcessing DATA = new DataProcessing();

    public static void generateJson(){
        //DATA.inputData();
        MAPPER.writerWithDefaultPrettyPrinter()
                .writeValue(new File("result.json"), DATA.groupingByTaskId());
    }
}
