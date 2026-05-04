package config;

import model.Taskrecord;
import java.util.List;

public interface DataSource {

     List<Taskrecord> load();
     void save(Object data);
}
