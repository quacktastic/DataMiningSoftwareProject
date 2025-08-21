package org.example.access.password;

import org.example.access.list.UserDataStorage;
import org.example.access.validate.PasswordValidator;

public class UserPassword implements Password {

    PasswordValidator passwordValidator = new PasswordValidator();
    PasswordChangeService passwordChangeService = new PasswordChangeService();

    public boolean passwordAccepted(String password) {
        return passwordValidator.isValid(password);
    }

    public void passwordChange(String id, String password, String newPassword) {
        passwordChangeService.change(id, password, newPassword);
    }

}
