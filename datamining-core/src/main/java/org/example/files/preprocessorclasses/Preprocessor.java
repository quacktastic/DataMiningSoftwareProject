package org.example.files.preprocessorclasses;

import org.example.files.DataRecord;

public interface Preprocessor {
    DataRecord apply(DataRecord in);
}
