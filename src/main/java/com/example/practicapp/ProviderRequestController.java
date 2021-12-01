package com.example.practicapp;

import com.example.practicapp.objects.Ingredient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.example.practicapp.DatabaseController.getIngredients;

public class ProviderRequestController {
        @FXML
        private TextField IngredientPrice;

        @FXML
        private Text Ingridient;

        @FXML
        private Text checktext;

        @FXML
        private Pane mainPane;
        List<Ingredient> temp =  DatabaseController.getIngredients();
        TextField[] tempFields;
        MainMenuController mainMenuController;

    public void setData(MainMenuController mainMenuController){
            this.mainMenuController = mainMenuController;

            Text[] tempText= new Text[temp.size()];
            tempFields = new TextField[temp.size()];
                for (int i = 0; i < temp.size(); i++) {
                    tempFields[i] = new TextField();
                    tempText[i] = new Text();
                    tempText[i].setText("Цена на "+temp.get(i).getName());

                    tempFields[i].setLayoutX(IngredientPrice.getLayoutX()); //IngredientPrice.getLayoutX()+10
                    tempFields[i].setLayoutY(IngredientPrice.getLayoutY()+30*(i+1)); //IngredientPrice.getLayoutY()+10
                    tempText[i].setLayoutX(tempFields[i].getLayoutX()-170);
                    tempText[i].setLayoutY(tempFields[i].getLayoutY()+10);

                }
        mainPane.getChildren().addAll(tempFields);
        mainPane.getChildren().addAll(tempText);
        mainPane.getChildren().remove(IngredientPrice);
        mainPane.getChildren().remove(Ingridient);
        }
        public void submitRequest() throws IOException {
            for (int i = 0; i < temp.size(); i++) {
                DatabaseController.InsertIngredientPrice(temp.get(i).getIng_id(),Integer.parseInt(tempFields[i].getText()));
            }
        }
}
