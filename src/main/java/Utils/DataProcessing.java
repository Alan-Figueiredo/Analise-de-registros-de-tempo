package Utils;

// 2.1
// Processar os dados
// Ignorar registro com minutes <= 0
// Informar quantidade de registros ignorados

import Config.PathJson;
import Model.Task;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;


public class DataProcessing {


    private static final ObjectMapper MAPPER = new ObjectMapper();
    private List<Task> listTask = new ArrayList<>();

    public List<Task> inputData(){
        try {
          listTask = MAPPER.readValue(PathJson.PATH,new TypeReference<List<Task>>(){});
          return listTask;
        }
        catch (Exception e) {
            System.err.println("Erro ao ler o arquivo: "+ e.getMessage());
            return List.of();
        }

    }

    public List<Task> clearList() {
        return listTask.stream()
                .filter(task -> task.getMinutes() > 0)
                .toList();
    }

    public long countIgnoredRecords() {
        return listTask.stream()
                .filter(t -> t.getMinutes() <= 0)
                .count();
    }
}
