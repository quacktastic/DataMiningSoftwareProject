package org.example.person.user;

import org.example.person.Person;

public class User implements Person {

    private int id;
    private String password;
    private boolean valid;

    public User(int id, String password) {
        this.id = id;
        this.password = password;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isValid() {
        return false;
    }



}
