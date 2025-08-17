package org.example.person;

public interface Person {

     String getId();
     String getPassword();

     void setId(String id);
     void setPassword(String password);

    boolean isValid();
}
