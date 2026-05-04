package config;

import model.Taskrecord;
import tools.jackson.core.type.TypeReference;
import tools.jackson.core.util.DefaultIndenter;
import tools.jackson.core.util.DefaultPrettyPrinter;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class JsonAdapter implements DataSource{

    private static final ObjectMapper mapper = new ObjectMapper();
    private final File inputPath;
    private final File outputPath;


    public JsonAdapter(String inputPath,String outputPath) {
        this.inputPath = new File(inputPath);
        this.outputPath = new File(outputPath);

    }

    @Override
    public List<Taskrecord> load() {
        try {
            if (!inputPath.exists()) {
                throw new RuntimeException("Arquivo não encontrado: " + inputPath.getName());
            }

            return mapper.readValue(inputPath, new TypeReference<List<Taskrecord>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Erro ao ler JSON", e);
        }
    }

    @Override
    public void save(Object data) {
        try {
            DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
            printer.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);

            mapper.writer().with(printer).writeValue(outputPath, data);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao escrever JSON", e);
        }
    }
}
