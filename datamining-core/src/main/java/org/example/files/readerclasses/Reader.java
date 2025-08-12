package org.example.files.readerclasses;


import java.io.IOException;

/**
 * Minimal streaming reader contract: open --> hasNext/next --> close.
 * - open(source): prepares resources; does not consume data rows.
 * - hasNext(): non-consuming probe (may fill an internal peek).
 * - next(): consumes and returns exactly one record.
 * - close(): releases resources; idempotent (safe to call multiple times).
 */
public interface Reader<T> extends AutoCloseable {


    void open(String source) throws IOException;
    boolean hasNext() throws IOException;
    T next() throws java.io.IOException, java.util.NoSuchElementException;

    @Override
    void close() throws java.io.IOException;

}
