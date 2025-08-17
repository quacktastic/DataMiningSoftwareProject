package org.example.person;

public interface Person {

     int getId();
     String getPassword();

     void setId(int id);
     void setPassword(String password);

    boolean isValid();
}
