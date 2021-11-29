package com.example.practicapp;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class OrderController {
    @FXML
    private Text ItemName;
    MainMenuController mainMenuController;
    private int id;
    public void setData(MainMenuController mainMenuController, Product product) {
        this.mainMenuController = mainMenuController;
        ItemName.setText(product.getName());
        this.id = product.getId();
    }
    public void toProductListPane() throws IOException {
        mainMenuController.setProductListPane();
    }
}
