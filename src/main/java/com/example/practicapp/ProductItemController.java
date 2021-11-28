package com.example.practicapp;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ProductItemController {
    @FXML Text ProductName;
    @FXML  Text PriceField;
    String name;
    int price;
    MainMenuController mainMenuController;
    public void setData(String name, int price, MainMenuController mainMenuController) {
        this.name = name;
        this.price = price;
        this.mainMenuController = mainMenuController;
        ProductName.setText(name);
        PriceField.setText(price+" руб/шт");
    }
}
