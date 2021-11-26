package com.example.practicapp;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class SignUpController {
    @FXML
    TextField mailField;
    @FXML
    TextField loginField;
    @FXML
    TextField orgNameField;
    @FXML
    PasswordField passwordField;
    @FXML
    CheckBox customerBox;
    @FXML
    CheckBox providerBox;
    public void exitApplication(){
        System.exit(1);
    }
    public void showSignIn() throws IOException {
        ApplicationCoreController.ShowSingInMenu();
    }
    public void signUpApply() throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        int temp =(customerBox.isSelected()&&!providerBox.isSelected())?1:(providerBox.isSelected()&&!customerBox.isSelected())?2:(customerBox.isSelected()==providerBox.isSelected())?3:0;
       if(DatabaseController.signUp(loginField.getText(),
                passwordField.getText(),
                mailField.getText(),
                orgNameField.getText(),
                temp)) {
           showSignIn();
       }
    }
}
