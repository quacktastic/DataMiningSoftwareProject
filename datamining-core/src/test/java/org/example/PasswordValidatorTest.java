package org.example;

import org.example.access.password.UserPassword;
import org.example.access.validate.PasswordValidator;
import org.example.person.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordValidatorTest {

    private PasswordValidator v;

    @BeforeEach
    void setUp() {v = new PasswordValidator();}


    @Test
    public void passwordValidify() {

        try {
            assertTrue(v.isValid("112e33043423"));

        } catch (Exception e) {
            System.out.println("Password invalid");
        }

        try {
            assertFalse(v.isValid("122345"));
        } catch (Exception e) {
            System.out.println("Password somehow passed the test despite it's completely digit");
        }

        try {
            assertFalse(v.isValid("442323458ertersadsf"));
        } catch (Exception e) {
            System.out.println("Password somehow passed despite its amount of characters.");
        }

        try {
            assertFalse(v.isValid(""));
        } catch (Exception e) {
            System.out.println("Password somehow passed despite it's empty");
        }

        try {
            assertFalse(v.isValid(null));
        } catch (Exception e) {
            System.out.println("Password somehow passed despite it's null");
        }
    }

    @Test
    public void passwordReAssign() {

    }




}
