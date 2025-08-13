package org.example.files.preprocessorclasses;

import org.example.files.DataRecord;

import java.util.Locale;

public class Rename implements Preprocessor {

    private final String from;
    private final String to;
    private final boolean overwriteIfExists;

    public Rename(String from, String to, boolean overwriteIfExists) {
        if(from == null || from.isBlank()) {throw new IllegalArgumentException("from");}
        if(to == null || from.isBlank()) {throw new IllegalArgumentException("to");}
        this.from = from.trim().toLowerCase().replace(' ','_');
        this.to= to.trim().toLowerCase().replace(' ','_');
        this.overwriteIfExists = overwriteIfExists;
    }

    @Override
    public DataRecord apply(DataRecord rec) {

        var m = rec.asMap();
        if (from.equals(to)) {return rec;}
        if (!m.containsKey(from)) {return rec;}
        Object v = m.remove(from);
        m.put(to, v);
        return rec;
    }
}
