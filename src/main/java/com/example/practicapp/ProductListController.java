package com.example.practicapp;

import com.example.practicapp.MainMenuController;
import com.example.practicapp.objects.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ProductListController implements Initializable {
    private static MainMenuController mainMenuController;
    @FXML
    private GridPane gridPane;
    public static void setData(MainMenuController parentcontroller){
        mainMenuController = parentcontroller;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateGrid("");
    }
    public void updateGrid(String filter){

        gridPane.getChildren().clear();
        List<Product> products = DatabaseController.getProducts();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getName().contains(filter)) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("ProductLabel.fxml"));
                    Pane anchorPane = fxmlLoader.load();

                    ProductLabelController controller = fxmlLoader.getController();
                    //controller.setData(PhotographList.get(i).getPhotograph(), PhotographList.get(i).getDescription(), menuController);
                    if (column == 2) {
                        column = 0;
                        row++;
                    }
                    //
                    gridPane.add(anchorPane, column++, row);
                    GridPane.setMargin(anchorPane, new Insets(5));
                }
                else if (filter == ""){
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("ProductLabel.fxml"));
                    Pane anchorPane = fxmlLoader.load();

                    ProductLabelController controller = fxmlLoader.getController();
                   // controller.setData(PhotographList.get(i).getPhotograph(), PhotographList.get(i).getDescription(), menuController);
                    if (column == 2) {
                        column = 0;
                        row++;
                    }
                    //
                    gridPane.add(anchorPane, column++, row);
                    GridPane.setMargin(anchorPane, new Insets(5));
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
