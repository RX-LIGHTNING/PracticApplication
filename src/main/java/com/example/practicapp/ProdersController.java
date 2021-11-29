package com.example.practicapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProdersController implements Initializable {
    @FXML
    private TextField searchField;
    @FXML
    private GridPane gridPane;

    MainMenuController mainMenuController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void SetData(MainMenuController parentController){
        mainMenuController = parentController;
    }
    public void updategrid(String filter){
        gridPane.getChildren().clear();
        List<Product> products = DatabaseController.getProducts();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getName().contains(filter)) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("ProductItem.fxml"));
                    Pane anchorPane = fxmlLoader.load();

                    ProductItemController controller = fxmlLoader.getController();
                 //   controller.setData(products.get(i).getName(), products.get(i).getPrice(),products.get(i).getImage(), mainMenuController);
                    if (column == 3) {
                        column = 0;
                        row++;
                    }
                    //
                    gridPane.add(anchorPane, column++, row);
                    GridPane.setMargin(anchorPane, new Insets(20));
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
}
