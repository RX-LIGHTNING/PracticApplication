package com.example.practicapp;

import com.example.practicapp.objects.Ingredient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Objects;

public class AdminPaneIngredients {
    @FXML private VBox IngredVbox;
    @FXML private VBox Providers;
    @FXML private TextField ingredTextField;
    List<Ingredient> ingredsList = DatabaseController.getIngredients();
    Button[] ingredsButton = new Button[ingredsList.size()];
    int selecteditem=0;
    public void setData(AdminPaneController adminPaneController){
        updateIngreds();
    }
    public void updateIngreds(){
        for (int i = 0; i < ingredsList.size(); i++) {
            ingredsButton[i]  =new Button();
            ingredsButton[i].setText("ID:"+ingredsList.get(i).getIng_id()+" "+ingredsList.get(i).getName());
            ingredsButton[i].setPrefWidth(150);
            int id = ingredsList.get(i).getIng_id();
            ingredsButton[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    onSelect(id);
                }
            });
            IngredVbox.getChildren().add(ingredsButton[i]);
        }

    }
    public void onSelect(int id){
        selecteditem = id;
        for (int i = 0; i < ingredsList.size(); i++) {
            if(ingredsList.get(i).getIng_id()==id) {
                ingredTextField.setText(ingredsList.get(i).getName());
            }
        }
    }
    public void ingredInsert(){
        if(!Objects.equals(ingredTextField.getText(), "")){
            DatabaseController.insertIngredient(ingredTextField.getText());
        }
    }
    public void ingredUpdate(){
        if(selecteditem!=0 && Objects.equals(ingredTextField.getText(), "")){
            DatabaseController.updateIngredient(selecteditem,ingredTextField.getText());
        }
    }
    public void ingredDelete(){
        if(selecteditem!=0){
            DatabaseController.deleteIngredient(selecteditem);
        }
    }
}
