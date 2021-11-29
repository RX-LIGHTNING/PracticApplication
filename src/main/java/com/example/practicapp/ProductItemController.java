package com.example.practicapp;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

public class ProductItemController {
    @FXML Text ProductName;
    @FXML  Text PriceField;
    @FXML ImageView ProductImage;
    private String name;
    private Product product;
    private int price;
    private MainMenuController mainMenuController;

    public void setData(Product product, MainMenuController mainMenuController) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.mainMenuController = mainMenuController;
        this.product = product;
        //ProductImage.setImage(image);
        ProductName.setText(name);
        PriceField.setText(price+" руб/шт");
    }
    public void orderClick() throws IOException {
        mainMenuController.setOrderPane(product);
    }
}
