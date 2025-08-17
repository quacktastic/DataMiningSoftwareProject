package org.example.access.match;


public interface Match {

    boolean idMatch(int id);
    boolean passwordMatch(String password);
    boolean isValidMatch(int id, String password);


}
