package org.example.files.readerclasses;

import org.example.files.DataRecord;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.security.Key;
import java.util.*;

/**
 * CSVReader implements a Reader for CSV files. Designed to handle
 * CSV files with a specified delimiter and optional header.
 */
public class CSVReader implements Reader<DataRecord> {

    /**
     * Default delimiter for CSV files. Delimiter is used to separate fields in a CSV line.
     * Common delimiters include comma (','), semicolon (';'), and tab ('\t').
     * This class uses a single character delimiter.
     *
     * hasHeader indicates whether the first line of the CSV file contains headers.
     * If true, the first line is treated as headers and later lines are treated as data.
     * If false, all lines are treated as data records.
     */
    private final char delimiter;
    private final boolean hasHeader;

    private List<String> headers;
    private BufferedReader bufferedReader;
    private String peekLine;

    public CSVReader(char delimiter, boolean hasHeader) {
        this.delimiter = delimiter;
        this.hasHeader = hasHeader;
    }


    @Override
    public void open(String source) throws IOException {
        bufferedReader = Files.newBufferedReader(Path.of(source), StandardCharsets.UTF_8);
        if (hasHeader) {
            String h = bufferedReader.readLine();
            if (h == null) {throw new EOFException("Empty CSV");}

            if (!h.isEmpty() && h.charAt(0) == '\uFEFF') {h = h.substring(1);}
            headers = normalize(splitCsv(h));
        }
    }

    @Override
    public boolean hasNext() throws IOException {
        if (bufferedReader == null) {
            throw new IllegalStateException("Call open() first.");
        }
            if (peekLine != null) {
                return true;
            }

        String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.isBlank()) continue;
                peekLine = line;
                return true;
            }
        return false;
    }

    @Override
    public DataRecord next() throws IOException {
        if (!hasNext()) throw new NoSuchElementException("No more records");

        String line = peekLine;
        peekLine = null;

        List<String> cells = splitCsv(line);
        if (headers == null) {
            headers = autoHeaders(cells.size());
        }

            Map<String,Object> m = new LinkedHashMap<>();
            for (int i = 0; i < headers.size(); i++) {
                String col = headers.get(i);


                String raw;
                if (i >= cells.size()) {
                    raw = null;
                } else {
                    String v = cells.get(i);
                    if (v != null) {
                        v = v.trim();
                        if (v.isEmpty()) {
                            v = null;
                        }
                    }
                    raw = v;
                }
                m.put(col, castTyped(col, raw));
            }
            return new DataRecord(m);
    }

    @Override
    public void close() throws IOException {

        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            }
            finally {
                bufferedReader = null;
                headers = null;
                peekLine = null;
            }
        }
    }

    private List<String> splitCsv(String line) {
        List<String> out = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQ = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') {inQ = !inQ;}
            else if (c==delimiter && !inQ) {out.add(sb.toString()); sb.setLength(0);}
            else sb.append(c);
        }
        out.add(sb.toString());
        return out;
    }


    private List<String> normalize(List<String> hs) {
        List<String> o = new ArrayList<>(hs.size());
        for (String h: hs) {
            o.add(h.trim().toLowerCase().replace(' ', '_'));
        }
        return o;
    }


    private List<String> autoHeaders(int n) {
        List<String> hs = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            hs.add("col" + i);
        }
            return hs;
    }


    private Object castTyped(String col, String raw) {
        if (raw == null || raw.isEmpty() || raw.equalsIgnoreCase("na") || raw.equalsIgnoreCase("n/a")) {
            return null;
        }

        String lc = col.toLowerCase();
        if (lc.contains("id") || lc.contains("code") || lc.contains("zip")) {
            return raw;
        }

        if (raw.equalsIgnoreCase("true") || raw.equalsIgnoreCase("false")) {
            return Boolean.parseBoolean(raw);
        }

        try {
            if (!raw.contains(".") && !raw.contains("e") && !raw.contains("E"))
                return Long.parseLong(raw); }
        catch (NumberFormatException ignore){}

        try {
            return Double.parseDouble(raw);
        } catch (NumberFormatException ignore) {}

        return raw;
    }


}
