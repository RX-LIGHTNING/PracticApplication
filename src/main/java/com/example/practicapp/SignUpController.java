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
        if(temp!=0&&passwordField.getText().length()>0 && passwordField.getText().length()<25
                && mailField.getText().length()>0 && mailField.getText().length()<25
                && orgNameField.getText().length() > 0 && orgNameField.getText().length()<40
                &&loginField.getText().length() > 0 && loginField.getText().length()<25
        ) {
            if (DatabaseController.signUp(loginField.getText(),
                    passwordField.getText(),
                    mailField.getText(),
                    orgNameField.getText(),
                    temp)) {
                showSignIn();
            }

        }
        else {
            mailField.setStyle("-fx-border-color: red");
            orgNameField.setStyle("-fx-border-color: red");
            loginField.setStyle("-fx-border-color: red");
            passwordField.setStyle("-fx-border-color: red");
        }
    }
}
