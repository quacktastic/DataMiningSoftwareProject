package org.example.access.validate;

import org.example.access.list.UserDataStorage;

import java.util.Map;

public class PasswordValidator implements Validator<String> {

    @Override
    public boolean isValid(String password) {

        if (password == null || password.isEmpty()) {
            System.out.println("Password cannot be empty.");
            return false;
        }
        if (!passwordLengthValid(password)) {
            System.out.println("Password length should be between 8 and 14 letters.");
            return false;
        }
        if (!notOnlyDigits(password)) {
            System.out.println("Password cannot consist of only digits.");
            return false;
        }
        return true;
    }

    // Password should include different characters
    private boolean notOnlyDigits(String password) {
        return password.length() != countInt(password);
    }

    // Get the amount of integer index usage
    private int countInt(String password) {
        int count = 0;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isDigit(c)) {
                count++;
            }
        }
        return count;
    }

    // Password should have a length between 8 and 14
    private boolean passwordLengthValid(String password) {
        return  password.length() <= 14 && password.length() >= 8;
    }
}
