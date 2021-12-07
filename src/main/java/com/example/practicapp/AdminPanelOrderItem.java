package com.example.practicapp;

import com.example.practicapp.objects.Order;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class AdminPanelOrderItem {

    AdminPaneController mainMenuController;
    Order order;
    @FXML
    private Text ProductName;
    @FXML
    private Text StatusBar;
    @FXML
    private Text quantityBar;
    @FXML
    private Text OrgText;
    @FXML
    private Text ContactText;
    public void setData(Order order, AdminPaneController mainMenuController) {
        this.mainMenuController = mainMenuController;
        this.order = order;
        ProductName.setText(order.getProduct());
        StatusBar.setText("Статус: "+order.getStatus());
        quantityBar.setText("Кол-во: "+order.getQuantity()+"руб/шт");
        ContactText.setText("Контакты"+order.getContact());
        OrgText.setText("Организация"+order.getOrganzition());
    }
    public void CancelOrder() throws IOException {
        DatabaseController.OrderCancel(order.getId(),-1);
        mainMenuController.showOrdersPane();
    }
    public void VerifyOrder() throws IOException {
        DatabaseController.OrderCancel(order.getId(),1);
        mainMenuController.showOrdersPane();
    }
    public void FinishOrder() throws IOException {
        DatabaseController.OrderCancel(order.getId(),2);
        mainMenuController.showOrdersPane();
    }
    public void PrintAgreement() {
        if(Objects.equals(order.getStatus(), "Ожидание выполнения") &&
                Objects.equals(order.getStatus(), "Выполнен"))
        {

        }
    }
}
