package com.example.practicapp;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ProductItemController {
    @FXML Text ProductName;
    @FXML  Text PriceField;
    @FXML ImageView ProductImage;
    String name;
    int price;
    MainMenuController mainMenuController;
    public void setData(String name, int price, Image image, MainMenuController mainMenuController) {
        this.name = name;
        this.price = price;
        this.mainMenuController = mainMenuController;
        //ProductImage.setImage(image);
        ProductName.setText(name);
        PriceField.setText(price+" руб/шт");
    }
}
