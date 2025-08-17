package org.example.access.login.admin;
import org.example.access.login.Login;

public class AdminLogin implements Login {
    private int adminId;
    private String adminPassword;

    public AdminLogin(int adminId, String adminPassword) {
        this.adminId = adminId;
        this.adminPassword = adminPassword;
    }

    @Override
    public boolean idMatch(int id) {
        return this.adminId == id;
    }

    @Override
    public boolean passwordMatch(String password) {
        return this.adminPassword.equals(password);
    }

    @Override
    public boolean isValidLogin(int id, String password) {
        return idMatch(id) && passwordMatch(password);
    }
}
