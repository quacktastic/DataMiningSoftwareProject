package org.example.access.login;

public interface Login {
    boolean idMatch(int id);
    boolean passwordMatch(String password);
    boolean isValidLogin(int id, String password);
}
