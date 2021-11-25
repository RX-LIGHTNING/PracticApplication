package com.example.practicapp;

import java.io.IOException;

public class SignUpController {
    public void exitApplication(){
        System.exit(1);
    }
    public void showSignIn() throws IOException {
        ApplicationCoreController.ShowSingInMenu();
    }
}
