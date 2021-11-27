package com.example.practicapp;

import com.example.practicapp.MainMenuController;
import com.example.practicapp.objects.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ProductListController implements Initializable {
    private static MainMenuController mainMenuController;
    @FXML
    private GridPane gridPane;
    public static void setData(MainMenuController parentcontroller){
        mainMenuController = parentcontroller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Product> products = DatabaseController.getProducts();
        for (int i = 0; i < products.size(); i++) {
            
        }
    }
}
