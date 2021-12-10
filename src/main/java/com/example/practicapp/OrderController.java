package com.example.practicapp;

import com.example.practicapp.objects.Product;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Calendar;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderController {
    @FXML
    private Text ItemName;
    @FXML
    private TextField contactField;
    @FXML
    private Slider productQuantity;
    @FXML
    private Text quantityText;

    MainMenuController mainMenuController;
    private int id;
    private String name;
    public void setData(MainMenuController mainMenuController, Product product) {
        this.mainMenuController = mainMenuController;
        ItemName.setText(product.getName());
        this.id = product.getId();
        this.name = product.getName();
        productQuantity.setMax(DatabaseController.leftProduct(product.getName()));
    }
    public void toProductListPane() throws IOException {
        mainMenuController.setProductListPane();
    }
    public void orderConfirm() throws IOException {
        if(productQuantity.getValue()!=0 && Validator.isValid(contactField.getText())){
            DatabaseController.OrderInsert((int) productQuantity.getValue(),this.name,contactField.getText());
            mainMenuController.setProductListPane();
        }
        else{
            productQuantity.setStyle("-fx-border-color: red;");
            contactField.setStyle("-fx-border-color: red;");
        }
    }
    public void quantityChange(){
        quantityText.setText(String.valueOf((int)(productQuantity.getValue())));
    }
}
