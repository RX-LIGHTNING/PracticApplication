package com.example.practicapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class SignInController {
    public void exitApplication(){
        System.exit(1);
    }
    public void ShowSignUp() throws IOException {
        ApplicationCoreController.ShowSingUpMenu();
    }
    public void ShowMainMenu() throws IOException {
        ApplicationCoreController.ShowMainMenu();
    }
}