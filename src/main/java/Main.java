import Config.PathJson;
import Utils.DataProcessing;

public class Main {

    public static void main(String[] args) {

        DataProcessing data = new DataProcessing();
        data.inputData(PathJson.PATH);
    }
}
