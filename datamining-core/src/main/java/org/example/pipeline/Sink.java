package org.example.pipeline;

import org.example.files.DataRecord;

public interface Sink {
    void accept(DataRecord record);
    default void finish() { }
}
