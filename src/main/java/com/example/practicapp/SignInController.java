package com.example.practicapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class SignInController {
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;
    public void exitApplication(){
        System.exit(1);
    }
    public void ShowSignUp() throws IOException {
        ApplicationCoreController.ShowSingUpMenu();
    }
    public void applySignIn() throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        if(DatabaseController.isLoggedIn("123", "123")) {

            ApplicationCoreController.ShowMainMenu();
        }
    }
}