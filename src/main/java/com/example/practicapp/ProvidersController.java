package com.example.practicapp;

import com.example.practicapp.objects.Ingredient;
import com.example.practicapp.objects.Provider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArrayBase;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.print.Collation;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ProvidersController {
    @FXML private TextField searchField;
    @FXML private GridPane gridPane;
    @FXML private ComboBox<String> ingredientBox;
    private int sort=0;
    MainMenuController mainMenuController;

    public void setData(MainMenuController parentController){
        mainMenuController = parentController;
        List<Ingredient> temp = DatabaseController.getIngredients();
        ObservableList<String> ingreds = FXCollections.observableArrayList();
        ingreds.add("");
        for (int i = 0; i < temp.size(); i++) {
            ingreds.add(temp.get(i).getName());
        }
            ingredientBox.setItems(ingreds);
        ingredientBox.setValue("");
        updategrid("", "");

    }
    public void updategrid(String filter, String ingredient){
        gridPane.getChildren().clear();
        List<Provider> providers = DatabaseController.getProviders();
        int row = 1;
        try {
            for (int i = 0; i < providers.size(); i++) {
                //list sort
                if(sort==1){Collections.sort(providers);}
                else if(sort==2){Collections.sort(providers);Collections.reverse(providers);}
                if(providers.get(i).getOrgname().toLowerCase(Locale.ROOT).contains(filter)&&
                        providers.get(i).getIngredientname().contains(ingredient)) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("ProviderItem.fxml"));
                    Pane anchorPane = fxmlLoader.load();

                    ProviderItemController controller = fxmlLoader.getController();
                    controller.setData(providers.get(i), mainMenuController);
                    row++;
                    gridPane.add(anchorPane, 0, row);
                    GridPane.setMargin(anchorPane, new Insets(10, 1, 1, 1));
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
        updategrid(searchField.getText(),ingredientBox.getValue());
    }
    public void sortUp(){
        sort = 1;
        updategrid(searchField.getText(),ingredientBox.getValue());
    }
    public void sortDown(){
        sort = 2;
        updategrid(searchField.getText(),ingredientBox.getValue());
    }
    public void searchReset(){
        sort = 0;
        updategrid("","");
    }
}
