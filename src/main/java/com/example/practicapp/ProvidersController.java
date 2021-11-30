package com.example.practicapp;

import com.example.practicapp.objects.Provider;
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

public class ProvidersController {
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
        List<Provider> providers = DatabaseController.getProvider();
        int row = 1;
        try {
            for (int i = 0; i < providers.size(); i++) {
                if (providers.get(i).getOrgname().contains(filter)) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("ProviderItem.fxml"));
                    Pane anchorPane = fxmlLoader.load();

                    ProviderItemController controller = fxmlLoader.getController();
                    controller.setData(providers.get(i), mainMenuController);
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
