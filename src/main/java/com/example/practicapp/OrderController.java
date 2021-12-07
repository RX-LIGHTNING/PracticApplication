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

public class OrderController {
    @FXML
    private Text ItemName;
    @FXML
    private TextField contactField;

    @FXML
    private DatePicker productDate;
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
    }
    public void toProductListPane() throws IOException {
        mainMenuController.setProductListPane();
    }
    public void orderConfirm() throws IOException {
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(productDate.getValue());
        java.sql.Date CurrentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        if(gettedDatePickerDate.after(CurrentDate) &&
               productQuantity.getValue()!=0 &&
                !Objects.equals(contactField.getText(),"")
        ){
            DatabaseController.OrderInsert((int) productQuantity.getValue(),this.name, gettedDatePickerDate,contactField.getText());
            mainMenuController.setProductListPane();
        }
    }
    public void quantityChange(){
        quantityText.setText(String.valueOf((int)(productQuantity.getValue())));
    }
}
