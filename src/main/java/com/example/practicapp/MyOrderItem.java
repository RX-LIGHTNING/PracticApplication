package com.example.practicapp;

import com.example.practicapp.objects.Order;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;

public class MyOrderItem {
    MainMenuController mainMenuController;
    Order order;
    @FXML
    private Text ProductName;
    @FXML
    private Text StatusBar;
    @FXML
    private Text quantityBar;

    public void setData(Order order, MainMenuController mainMenuController) {
        this.mainMenuController = mainMenuController;
        this.order = order;
        ProductName.setText(order.getProduct());
        StatusBar.setText("Статус: "+order.getStatus());
        quantityBar.setText("Кол-во: "+order.getQuantity()+"руб/шт");
    }
    public void cancelOrder() throws IOException {
        DatabaseController.OrderCancel(order.getId(),-1);
        mainMenuController.setMyOrderPane();
    }
    public void printAgreement(){

    }
}
