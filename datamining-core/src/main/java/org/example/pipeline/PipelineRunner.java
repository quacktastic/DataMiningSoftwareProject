package org.example.pipeline;

import org.example.files.DataRecord;
import org.example.files.preprocessorclasses.Preprocessor;
import org.example.files.readerclasses.Reader;

import java.io.IOException;
import java.util.*;

import java.util.List;

public class PipelineRunner {

    private final Reader<DataRecord> reader;
    private final List<Preprocessor> steps;
    private final Sink sink;

    public PipelineRunner(Reader<DataRecord> reader, List<Preprocessor> steps, Sink sink) {
        if(reader == null) {throw new IllegalArgumentException("Reader is null");}
        if(sink == null) {throw new IllegalArgumentException("Sink is null");}
        this.reader = reader;

        List<Preprocessor> tmp;
        if(steps == null) {
            tmp = Collections.emptyList();
        }
        else {
            tmp = new ArrayList<>(steps.size());
            for (Preprocessor p :steps) {
                if(p != null) {
                    tmp.add(p);
                }
            }
            tmp = Collections.unmodifiableList(tmp);
        }
        this.steps = tmp;
        this.sink =sink;
    }

    public void run(String source) throws IOException {
        try {
            reader.open(source);
            while (reader.hasNext()) {
                DataRecord rec = reader.next();
                for (Preprocessor p: steps) {
                    rec = p.apply(rec);
                }
                sink.accept(rec);
            }
        } finally {
            try {reader.close();} catch (IOException ignored) {}
            sink.finish();
        }
    }
}
