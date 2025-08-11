package org.example.files.readerclasses;


/**
 * Reader Interface provides flexibility and allows to make multiple reader classes that have
 * same purpose with different file types. Users can add different types of files to make
 * the software train itself.
 */
public interface Reader {

    void open();
    boolean hasNext();
    Record next();
    void close();

}
