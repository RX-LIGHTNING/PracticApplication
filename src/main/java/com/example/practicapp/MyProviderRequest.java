package com.example.practicapp;

import com.example.practicapp.objects.Product;
import com.example.practicapp.objects.Provider;
import com.example.practicapp.objects.User;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class MyProviderRequest {
    @FXML
    private GridPane gridPane;
    @FXML
    private TextField searchField;

    private MainMenuController mainMenuController;

    public void setData(MainMenuController parentcontroller){
        this.mainMenuController = parentcontroller;
        updateGrid("");
    }
    public void updateGrid(String filter){
        gridPane.getChildren().clear();
        List<Provider> providers = DatabaseController.getProvidersById(User.getId());
        int row = 1;
            try {
                for (int i = 0; i < providers.size(); i++) {

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("ProviderRequestItem.fxml"));
                    Pane anchorPane = fxmlLoader.load();

                    ProviderRequestItemController controller = fxmlLoader.getController();
                    controller.setData(providers.get(i), mainMenuController, MyProviderRequest.this);
                    row++;
                    gridPane.add(anchorPane, 0, row);
                    GridPane.setMargin(anchorPane, new Insets(10, 1, 1, 1));


                    gridPane.setMinWidth(Region.USE_COMPUTED_SIZE);
                    gridPane.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    gridPane.setMaxWidth(Region.USE_COMPUTED_SIZE);
                    //
                    gridPane.setMinHeight(Region.USE_COMPUTED_SIZE);
                    gridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    gridPane.setMaxHeight(Region.USE_COMPUTED_SIZE);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    public void showOnlyCanceled(){
        updateGrid("");
    }
    public void showOnlyVerifying(){
        updateGrid("");
    }
    public void clearFilter(){
        updateGrid("");
    }
}
