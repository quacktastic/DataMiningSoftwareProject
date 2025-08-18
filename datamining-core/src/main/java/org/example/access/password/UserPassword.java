package org.example.access.password;

import org.example.access.list.UserDataStorage;
import org.example.access.validate.PasswordValidator;

public class UserPassword implements Password {

    PasswordValidator passwordValidator = new PasswordValidator();
    UserDataStorage dataStorage = new UserDataStorage();

    public boolean passwordAccepted(String password) {
        return passwordValidator.isValid(password);
    }

}
