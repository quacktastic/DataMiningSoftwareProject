package org.example.files.readerclasses;

import java.io.BufferedReader;

public class CSVReader implements Reader {

    private String file;
    private BufferedReader bufferedReader;
    private String line;

    public CSVReader(String file, BufferedReader bufferedReader, String line) {

        this.file = file;
        this.bufferedReader = bufferedReader;
        this.line = line;

    }

    @Override
    public void open() {

    }

    @Override
    public boolean hasNext() {

        return false;
    }

    @Override
    public Record next() {


    }

    @Override
    public void close() {

    }



}
