package org.example;

import org.example.access.password.UserPassword;

public class IDPasswordDeneme {

    public static void main(String[] args) {

        String idTest = "556332984";
        String pwd = "i6364w452r";
        String pwd2 = "oo8845?tL";
        String pwd3 = "22334563";
        String uPwd = "i55334s23o";
        UserPassword userPassword = new UserPassword();

        System.out.println(userPassword.passwordAccepted(pwd));
        System.out.println(userPassword.passwordAccepted(pwd2));
        userPassword.passwordChange(idTest, pwd, uPwd);
        System.out.println(userPassword.passwordAccepted(pwd3));




    }
}
