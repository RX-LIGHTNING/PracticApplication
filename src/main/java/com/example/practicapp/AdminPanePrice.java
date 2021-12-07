package com.example.practicapp;

import com.example.practicapp.objects.Ingredient;
import com.example.practicapp.objects.Product;
import com.example.practicapp.objects.Provider;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.List;

public class AdminPanePrice {
    @FXML private PieChart priceChart;
    @FXML private VBox productVbox;
    @FXML private Text resultText;
    @FXML private PieChart IngredChart;
    private AdminPaneController adminPaneController;
    private Button[] Products;
    private int summary = 0;
    double DirectPrice = 0;
    public void setData(AdminPaneController adminPaneController){
        this.adminPaneController = adminPaneController;
        updateProductVbox();
    }
    public void updateProductVbox(){
        List<Product> productList = DatabaseController.getProducts();
        summary = 0;
        Products = new Button[productList.size()];
        for (int i = 0; i < productList.size(); i++) {
            summary +=productList.get(i).getQuantity();
            Products[i]= new Button();
            Products[i].setText(productList.get(i).getName());
            Products[i].setId(String.valueOf(productList.get(i).getRecipe()));
            Products[i].setPrefWidth(100);
            Product temp = productList.get(i);
            Products[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    onSelectProduct(temp);
                }
            });
            productVbox.getChildren().add(Products[i]);
        }
    }
    public  void onSelectProduct(Product product){
        DirectPrice=0;
        priceChart.getData().clear();
        IngredChart.getData().clear();
        double inditectcost = (double) (DatabaseController.getIndirectCost() / summary);
        List<Ingredient> temp = DatabaseController.getRecipeIngredients(product.getRecipe());
        List<Provider> temp2 = DatabaseController.getProviders();
        for (int i = 0; i < temp2.size(); i++) {
            for (Ingredient ingredient : temp) {
                if (temp2.get(i).getIng_id() == ingredient.getIng_id()&&temp2.get(i).isStatus()&& ingredient.getQuantity()!=0) {
                    double price =  (((double)temp2.get(i).getPrice()/1000)*ingredient.getQuantity());
                    PieChart.Data slice = new PieChart.Data(ingredient.getName(), price);
                    DirectPrice+=price;
                    IngredChart.getData().add(slice);
                }
            }
        }
        PieChart.Data slice1 = new PieChart.Data("Косвенные расходы",inditectcost);
        PieChart.Data slice2 = new PieChart.Data("Прямые расходы",DirectPrice);
        System.out.println(inditectcost);
        resultText.setText("Итого: "+(DirectPrice+inditectcost)+" Рублей");
        priceChart.getData().add(slice1);
        priceChart.getData().add(slice2);
    }
}

