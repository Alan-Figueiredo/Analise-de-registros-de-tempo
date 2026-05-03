package utils;



import config.PathJson;
import model.Record;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.*;


public class DataProcessing implements PathJson {

    private static final ObjectMapper mapper = new ObjectMapper();
    private List<Record> records;
    private List<Record> unmodifiableRecords;

    public DataProcessing() {
        inputData();
    }

    public void inputData(){
        try {

            if (!getJson().exists()) {
                throw new RuntimeException("Arquivo data.json não encontrado");
            }
            records = mapper.readValue(getJson(),new TypeReference<List<Record>>(){});
            unmodifiableRecords = Collections.unmodifiableList(records);

        }
        catch (Exception e) {
            System.err.println("Erro ao ler o arquivo: "+ e.getMessage());
        }
    }

    public List<Record> getRecords() {
        return unmodifiableRecords;
    }


    @Override
    public File getJson() {
        return new File("data.json");
    }

    @Override
    public File writeJson() {
        return null;
    }
}


