package org.example;

import org.example.files.readerclasses.CSVReader;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;



public class CSVReaderSmokeTest {


    @Test
    void simpleCsv_readsTwoRows() throws Exception {

       var url = getClass().getResource("/datasets/csv/simple.csv");
       assertNotNull(url, "resource not found");
       var path = Paths.get(url.toURI()).toString();

       try (var r = new CSVReader(',', true)) {
           r.open(path);
           int count = 0;
           while (r.hasNext()) {
               r.next();
               count++;
           }
           assertEquals(2, count);
          }

       }

       @Test
       void mixedTypes_Typing() throws Exception {

        var url = getClass().getResource("/datasets/csv/mixed_types.csv");
        assertNotNull(url, "Resource not found");
        var path = Paths.get(url.toURI()).toString();

        try (var r = new CSVReader(',', true)){
            r.open(path);
            var rec = r.next();
            assertTrue(rec.asMap().get("id") instanceof String);
            assertTrue(rec.asMap().get("value") == null || rec.asMap().get("value") instanceof Double);
            assertTrue(rec.asMap().get("flag") instanceof Boolean);
        }

       }


       @Test
       void blankLines_Reading() throws Exception {

           var url = getClass().getResource("/datasets/csv/blank_lines.csv");
           assertNotNull(url, "Resource not found");
           var path = Paths.get(url.toURI()).toString();

           try (var r = new CSVReader(',', true)){
               r.open(path);

               // Testing first record as x = 10 L, y = null:
               assertTrue(r.hasNext());
               var rec1 = r.next();
               assertEquals(10L, rec1.asMap().get("x"));
               assertNull(rec1.asMap().get("y"));

               // Testing second record as x = 20 L, y = 30 L
               assertTrue(r.hasNext());
               var rec2 = r.next();
               assertEquals(20L, rec2.asMap().get("x"));
               assertEquals(30L, rec2.asMap().get("y"));


               assertFalse(r.hasNext());
           }

       }
    }


