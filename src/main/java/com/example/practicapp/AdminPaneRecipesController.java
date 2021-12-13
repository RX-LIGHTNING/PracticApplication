package com.example.practicapp;

import com.example.practicapp.objects.Ingredient;
import com.example.practicapp.objects.Recipe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Objects;

public class AdminPaneRecipesController {

    @FXML
    private VBox Recipes;

    @FXML
    private VBox ingredients;
    @FXML
            private Button exampleButton;
    AdminPaneController adminPaneController;
    TextField[] ingredientFields = new TextField[DatabaseController.getIngredients().size()+1];
    Button[] recipes;
    Text[] ingred_name =new Text[ingredientFields.length];
    int temp;
    public void setData(AdminPaneController adminPaneController){
        this.adminPaneController = adminPaneController;
        List<Ingredient> ingredient = DatabaseController.getIngredients();
        update(ingredient,temp);
    }
    public  void update( List<Ingredient> ingredient , int t) {
        ingredients.getChildren().clear();
        Recipes.getChildren().clear();
        List<Ingredient> ingredient2 = DatabaseController.getIngredients();
        List<Recipe> temp = DatabaseController.getRecipes();
        for (int i = 0; i < temp.size(); i++) {

            recipes = new Button[temp.size()];
            recipes[i] = new Button();
            recipes[i].setText(String.valueOf(temp.get(i).getRecipeid() + " " + temp.get(i).getName()));
            Recipes.getChildren().add(recipes[i]);
            int id = temp.get(i).getRecipeid();
            recipes[i].setPrefWidth(150);
            recipes[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    onSelect(id);
                }
            });
        }
        for (int i = 0; i < ingredient2.size(); i++) {

            ingredientFields[i] = new TextField();
            ingred_name[i] = new Text();
            ingred_name[i].setText(ingredient2.get(i).getName());
            ingredientFields[i].setPromptText(ingredient2.get(i).getName());
            ingredientFields[i].setId(String.valueOf(ingredient2.get(i).getIng_id()));
            ingredientFields[ingredient2.size()] = new TextField();
            ingred_name[ingred_name.length-1] = new Text();
            ingred_name[ingred_name.length-1].setText("Название");
            for (int j = 0; j < ingredient.size(); j++) {
                if (ingredient.get(j).getIng_id() == ingredient2.get(i).getIng_id()) {
                    ingredientFields[i].setText(String.valueOf(ingredient.get(j).getQuantity()));
                }
            }
            for (int k = 0; k < temp.size(); k++) {
                if (temp.get(k).getRecipeid() == t) {
                    ingredientFields[ingredientFields.length - 1].setText(temp.get(k).getName());

                }

            }
            ingredients.getChildren().add(ingred_name[i]);
            ingredients.getChildren().add(ingredientFields[i]);

        }
        ingredients.getChildren().add(ingred_name[ingred_name.length-1]);
        ingredients.getChildren().add(ingredientFields[ingredient2.size()]);
    }
    public  void onSelect(int id){
        temp = id;
        List<Ingredient> ingredient  = DatabaseController.getRecipeIngredients(id);
        update( ingredient,temp);
    }
    public  void onRecipeUpdate(){
        for (int i = 0; i < ingredientFields.length-1; i++) {
            if(Validator.isNumber(ingredientFields[i].getText())&& !Objects.equals(ingredientFields[i].getText(),"")) {
                DatabaseController.updateRecipe(temp,
                        Integer.parseInt(ingredientFields[i].getId()),
                        Integer.parseInt(ingredientFields[i].getText()),
                        ingredientFields[ingredientFields.length - 1].getText());
            }
            else{
                DatabaseController.updateRecipe(temp,
                        Integer.parseInt(ingredientFields[i].getId()),
                        0,
                        ingredientFields[ingredientFields.length - 1].getText());
            }

        }
        List<Ingredient> ingredient  = DatabaseController.getRecipeIngredients(temp);
        update( ingredient,temp);
    }
    public  void onRecipeAdded(){
        DatabaseController.insertRecipe(ingredientFields[ingredientFields.length-1].getText());
        List<Ingredient> ingredient  = DatabaseController.getRecipeIngredients(temp);
        update( ingredient,temp);
    }
    public  void onRecipeDelete(){
        DatabaseController.deleteRecipe(temp);
        List<Ingredient> ingredient  = DatabaseController.getRecipeIngredients(temp);
        update( ingredient,temp);
    }
    public  void onRecipeRefresh(){
        List<Ingredient> ingredient  = DatabaseController.getRecipeIngredients(temp);
        update( ingredient,temp);
    }
}
