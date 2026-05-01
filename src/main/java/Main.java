
import Utils.DataProcessing;

public class Main {

    public static void main(String[] args) {

        DataProcessing data = new DataProcessing();
        data.inputData();
        System.out.println(data.clearList());
        System.out.println(data.countIgnoredRecords());
    }
}
