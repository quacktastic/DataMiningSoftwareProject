package org.example.files;
import java.util.Map;

public final class DataRecord {
    private final Map<String, Object> values;
    public DataRecord(Map<String, Object> values)
    {this.values = values;}
    public Object get(String col){return values.get(col);}
    public Map<String, Object> asMap() {return values;}

}
