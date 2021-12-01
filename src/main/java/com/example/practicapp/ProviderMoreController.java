package com.example.practicapp;

import com.example.practicapp.objects.Ingredient;
import com.example.practicapp.objects.Provider;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;

public class ProviderMoreController {
    @FXML
    private Text orgname;
    @FXML
    private Pane mainPane;
    MainMenuController mainMenuController;
    public void setData(MainMenuController mainMenuController, int provider){
        this.mainMenuController = mainMenuController;
        List<Ingredient> temp =  DatabaseController.getIngredients();
        List<Provider> providers= DatabaseController.getProviders();
        Text[] tempText= new Text[temp.size()];
        for (int i = 0; i < temp.size(); i++) {
            tempText[i] = new Text();
            tempText[i].setText("Цена на "+temp.get(i).getName()+" " + providers.get(i).getPrice() );
            tempText[i].setLayoutX(100);
            tempText[i].setLayoutY((i+1)*30);

        }
        mainPane.getChildren().addAll(tempText);

    }
    public void returnToProviders() throws IOException {
        mainMenuController.setProvidersPane();
    }
}
