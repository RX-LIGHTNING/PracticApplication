package com.example.practicapp;

import com.example.practicapp.objects.Product;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;

public class AdminProductsItemController {
    @FXML
    Text ProductName;
    @FXML  Text PriceField;
    @FXML
    ImageView ProductImage;
    private String name;
    private Product product;
    private int price;
    private AdminPaneController mainMenuController;

    public void setData(Product product, AdminPaneController mainMenuController) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.mainMenuController = mainMenuController;
        this.product = product;
        ProductName.setText(name);
        PriceField.setText(price+" руб/шт");
        ProductImage.setImage(product.getImage());
    }
    public void moreClick() throws IOException {
       // mainMenuController.setOrderPane(product);
    }

}
