package org.example.pipeline;

import org.example.files.DataRecord;
import org.example.files.preprocessorclasses.Preprocessor;
import org.example.files.readerclasses.Reader;

import java.io.IOException;
import java.util.*;

import java.util.List;

/**
 * PipelineRunner orchestrates a sequence of preprocessing steps on data records read from a source.
 * It reads records using a Reader, applies a series of Preprocessors, and writes the final.
 */

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

    /**
     *
     * @param source provided resource for the reader as a String (e.g., file path).
     * @throws IOException thrown if there is an error reading the source or processing records.
     * Runs the pipeline on the specified source.
     * By running the run() method, the PipelineRunner will:
     * 1. Open the reader with the given source.
     * 2. Read records one by one.
     * 3. Apply each preprocessor step to the record.
     * 4. Pass the processed record to the sink.
     * 5. Close the reader and finalize the sink.
     */
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
