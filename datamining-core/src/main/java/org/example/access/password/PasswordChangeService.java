package org.example.access.password;


import org.example.access.list.UserDataStorage;
import org.example.access.validate.PasswordValidator;

import java.util.Objects;

/**
 * This class got the charge when the user preferred to change their password.
 */
public class PasswordChangeService {


    PasswordValidator validator = new PasswordValidator();

    public void change(String id, String currentPassword, String newPassword) {
        if (!passwordReadyToChange(id, currentPassword, newPassword)) {
            System.out.println("Invalid id, password or new password. Please re-enter your inputs and try again.");
            return;
        }
        boolean changed = UserDataStorage.updatePasswordInMap(id, newPassword);
        if (changed) {
            System.out.println("Password updated successfully.");
        } else {
            System.out.println("Failed to update the password");
        }

    }
    public boolean idFound(String id) {
        return UserDataStorage.existsById(id);
    }

    public boolean passwordMatches(String id, String password) {
        String uPassword = UserDataStorage.getPasswordById(id);
        return password.equals(uPassword);
    }

    // Ensuring validity by calling the isValid() method from PasswordValidator class.
    public boolean newPasswordIsValid(String newPassword) {
        return validator.isValid(newPassword);
    }

    public boolean passwordReadyToChange(String id, String password, String newPassword) {
        if (!idFound(id)) {
            System.out.println("ID not found!");
        }

        if (!passwordMatches(id, password)) {
            System.out.println("ID doesn't match your current password!");
        }

        if (!newPasswordIsValid(newPassword)) {
            System.out.println("New password doesn't match the validity rules!");
        }
        return idFound(id) && passwordMatches(id, password) && newPasswordIsValid(newPassword);
    }

}
