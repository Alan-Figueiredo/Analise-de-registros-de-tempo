
import config.DataSource;
import config.JsonAdapter;
import utils.DataProcessing;
import utils.GenerateJson;
import utils.RecordsAnalyzer;


public class Main {

    public static void main(String[] args) {

        DataSource adapter = new JsonAdapter("data.json","result.json");
        DataProcessing dataProcessing = new DataProcessing(adapter);
        RecordsAnalyzer analyzer = new RecordsAnalyzer(dataProcessing);
        GenerateJson generateJson = new GenerateJson(analyzer,adapter);
        generateJson.exportJson();


    }
}
