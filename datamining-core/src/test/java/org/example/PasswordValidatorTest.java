package org.example;

import org.example.access.password.PasswordChangeService;
import org.example.access.password.UserPassword;
import org.example.access.validate.PasswordValidator;
import org.example.person.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordValidatorTest {

    private PasswordValidator v;
    private PasswordChangeService d;

    @BeforeEach
    void setUpValidator() {v = new PasswordValidator();}
    void setUpChanger() {d = new PasswordChangeService();}


    @Test
    public void passwordValidify() {

        assertTrue(v.isValid("112e33043423"));
        assertFalse(v.isValid("122345"));
        assertFalse(v.isValid("442323458ertersadsf"));
        assertFalse(v.isValid(""));
        assertFalse(v.isValid(null));
    }




}
