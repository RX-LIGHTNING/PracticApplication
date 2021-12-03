package com.example.practicapp;

import com.example.practicapp.objects.Ingredient;
import com.example.practicapp.objects.Provider;
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
    List<Provider> providersList = DatabaseController.getProviders();
    Button[] ingredsButton = new Button[ingredsList.size()];
    Button[] ProvidersButton = new Button[ingredsList.size()];
    int selecteditem=0;
    public void setData(AdminPaneController adminPaneController){
        updateIngreds();
    }
    public void updateIngreds(){
        ingredsList = DatabaseController.getIngredients();
        ingredsButton = new Button[ingredsList.size()];
        IngredVbox.getChildren().clear();
        for (int i = 0; i < ingredsList.size(); i++) {
            ingredsButton[i]=new Button();
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
    public void updateProviders(int ing_id){
        providersList = DatabaseController.getProviders();
        ProvidersButton = new Button[providersList.size()];
        Providers.getChildren().clear();
        for (int i = 0; i < providersList.size(); i++) {
            if(ing_id==providersList.get(i).getIng_id()) {
                ProvidersButton[i] = new Button();
                ProvidersButton[i].setText("Пост:" + providersList.get(i).getOrgname() + " цена:" + providersList.get(i).getPrice()+"руб/кг");
                if(providersList.get(i).getStatus()){
                    ProvidersButton[i].setStyle("-fx-background-color: #18A4E0;");
                }
                ProvidersButton[i].setPrefWidth(300);
                int id = providersList.get(i).getId();
                ProvidersButton[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                    public void handle(ActionEvent actionEvent) {
                       onProviderChange(id);
                }
                });
                Providers.getChildren().add(ProvidersButton[i]);
            }
        }

    }
    public void onSelect(int id){
        selecteditem = id;
        updateProviders(id);
        for (int i = 0; i < ingredsList.size(); i++) {
            if(ingredsList.get(i).getIng_id()==id) {
                ingredTextField.setText(ingredsList.get(i).getName());
            }
        }
    }
    public void onProviderChange(int id){
        DatabaseController.updateProviderStatus(selecteditem,id);
        updateProviders(selecteditem);
    }
    public void ingredInsert(){
        if(!Objects.equals(ingredTextField.getText(), "")){
            DatabaseController.insertIngredient(ingredTextField.getText());

        }
        updateIngreds();
    }
    public void ingredUpdate(){
        if(selecteditem!=0 && !Objects.equals(ingredTextField.getText(), "")){
            DatabaseController.updateIngredient(selecteditem,ingredTextField.getText());
        }
        updateIngreds();
    }
    public void ingredDelete(){
        if(selecteditem!=0){
            DatabaseController.deleteIngredient(selecteditem);

        }
        updateIngreds();
    }
}
