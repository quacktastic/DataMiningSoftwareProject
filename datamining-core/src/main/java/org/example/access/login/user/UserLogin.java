package org.example.access.login.user;

import org.example.access.login.Login;

public class UserLogin implements Login {

    private int id;
    private String password;

    public UserLogin(int id, String password) {
        this.id = id;
        this.password = password;
    }

    @Override
    public boolean idMatch(int id) {
        return this.id == id;
    }

    @Override
    public boolean passwordMatch(String password) {

    }




}
