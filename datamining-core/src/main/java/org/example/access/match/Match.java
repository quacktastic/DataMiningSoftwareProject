package org.example.access.match;


public interface Match {

    boolean idMatch(String id);
    boolean passwordMatch(String password);
    boolean isValidMatch(String id, String password);


}
