package com.example.practicapp;

import com.example.practicapp.objects.Order;
import com.example.practicapp.objects.Provider;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.List;

public class MyOrderController{
    @FXML
    private TextField searchField;
    @FXML
    private GridPane gridPane;

    MainMenuController mainMenuController;
    public void setData(MainMenuController parentController){

        mainMenuController = parentController;
        updategrid("");
    }
    public void updategrid(String filter){
        gridPane.getChildren().clear();
        List<Order> orders = DatabaseController.getOrders();
        int row = 1;
        try {
            for (int i = 0; i < orders.size(); i++) {
                if (orders.get(i).getProduct().contains(filter)) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("MyOrdersItem.fxml"));
                    Pane anchorPane = fxmlLoader.load();

//                    MyOrderItem controller = fxmlLoader.getController();
//                    controller.setData(orders.get(i), mainMenuController);
                    row++;
                    gridPane.add(anchorPane, 0, row);
                    GridPane.setMargin(anchorPane, new Insets(10,1,1,1));
                }
                gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
                gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                gridPane.setMaxWidth(Region.USE_COMPUTED_SIZE);
                //
                gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
                gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                gridPane.setMaxHeight(Region.USE_COMPUTED_SIZE);
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    public void search(){
        updategrid(searchField.getText());
    }
}
