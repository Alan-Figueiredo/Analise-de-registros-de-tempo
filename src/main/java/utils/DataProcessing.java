package utils;



import config.DataSource;
import model.Taskrecord;

import java.util.*;


public class DataProcessing {

    private final List<Taskrecord> records;


    public DataProcessing(DataSource dataSource) {
        this.records = Collections.unmodifiableList(dataSource.load());
    }

    public List<Taskrecord> getRecords() {
        return records;
    }


}


