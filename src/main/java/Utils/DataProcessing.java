package Utils;

import Config.PathJson;
import Model.Records;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import java.util.*;



public class DataProcessing {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private List<Records> listRecords ;

    public DataProcessing() {
        inputData();
    }

    public void inputData(){
        try {

            if (!PathJson.PATH.exists()) {
                throw new RuntimeException("Arquivo data.json não encontrado");
            }
            listRecords = MAPPER.readValue(PathJson.PATH,new TypeReference<List<Records>>(){});
        }
        catch (Exception e) {
            System.err.println("Erro ao ler o arquivo: "+ e.getMessage());
        }
    }

    public List<Records> getListRecords() {
        return listRecords;
    }
}


