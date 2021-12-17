package com.example.practicapp;

import com.example.practicapp.objects.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AdminPaneProductController {
    @FXML
    private GridPane gridPane;
    @FXML
    private TextField searchField;

    private AdminPaneController mainMenuController;

    public void setData(AdminPaneController parentcontroller){
        this.mainMenuController = parentcontroller;
        updateGrid("");
    }

    public void updateGrid(String filter){

        gridPane.getChildren().clear();
        List<Product> products = DatabaseController.getProducts();
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getName().toLowerCase().contains(filter.toLowerCase())) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("AdminPanelProductsItem.fxml"));
                    Pane anchorPane = fxmlLoader.load();
                    AdminProductsItemController controller = fxmlLoader.getController();
                    controller.setData(products.get(i), this.mainMenuController);

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
    public void search(){
        updateGrid(searchField.getText());
    }
    public void addNewProduct() throws IOException {
        mainMenuController.showProductAdd(null);
    }
}
