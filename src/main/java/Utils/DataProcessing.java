package Utils;

// 2.1
// Processar os dados
// Ignorar registro com minutes <= 0
// Informar quantidade de registros ignorados

import Config.PathJson;
import Dto.GroupingRecordsTaskIdDto;
import Dto.ResponseDto;
import Model.Records;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;


import java.util.*;
import java.util.stream.Collectors;


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

    public List<Records> validRecords() {
        return listRecords.stream()
                .filter(r -> r.getMinutes() != null && r.getMinutes() > 0)
                .toList();
    }

    public long countIgnoredRecords() {
        return listRecords.stream()
                .filter(r -> r.getMinutes() == null || r.getMinutes() <= 0)
                .count();
    }


    public ResponseDto groupingByTaskId() {

        List<Records> cleanList = this.validRecords();

        int totalMinutesAll = cleanList.stream()
                .mapToInt(r -> r.getMinutes()).sum();

        Map<Integer,List<Records>> groupByTaskId = cleanList.stream()
                .collect(Collectors.groupingBy(r -> r.getTaskId()));

        List<GroupingRecordsTaskIdDto> result = groupByTaskId.entrySet()
                        .stream().map(e-> {

                            Integer taskId = e.getKey();
                            List<Records> lista = e.getValue();

                            int totalMinutes = lista.stream()
                            .mapToInt(r-> r.getMinutes()).sum();

                            String taskName = lista.get(0).getTaskName();
                            double perc = totalMinutesAll == 0 ? 0 : (totalMinutes * 100.0) / totalMinutesAll;

                            return new GroupingRecordsTaskIdDto(
                                    taskId,
                                    taskName,
                                    totalMinutes,
                                    String.format(Locale.US,"%.2f%%", perc)
                            );

                })
                .toList();
        return new ResponseDto(totalMinutesAll, result);
    }

}
