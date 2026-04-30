package Utils;

// 2.1
// Processar os dados
// Ignorar registro com minutes <= 0
// Informar quantidade de registros ignorados



import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.nio.file.Path;


public class DataProcessing {

    public JsonNode inputData(String path){

        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode json = mapper.readTree(Path.of(path));
            System.out.println(json);
        }
        catch (Exception e) {
            System.err.println("Erro ao ler o arquivo: "+ e.getMessage());
        }

        return null;
    }
}
