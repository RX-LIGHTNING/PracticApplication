package com.example.practicapp;

import com.example.practicapp.objects.Ingredient;
import com.example.practicapp.objects.Order;
import com.example.practicapp.objects.Product;
import com.example.practicapp.objects.Provider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class AdminPaneOrders {
    @FXML
    private TextField searchField;
    @FXML private GridPane gridPane;
    @FXML private ComboBox<String> ingredientBox;
    private int sort=0;
    AdminPaneController mainMenuController;

    public void setData(AdminPaneController parentController){
        mainMenuController = parentController;
        List<Product> temp = DatabaseController.getProducts();
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
        List<Order> providers = DatabaseController.getAllOrders();
        int row = 1;
        try {
            for (int i = 0; i < providers.size(); i++) {
                if(providers.get(i).getProduct().contains(ingredient)&&
                    providers.get(i).getOrganzition().toLowerCase(Locale.ROOT).contains(filter)){

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("AdminPanelOrdersItem.fxml"));
                    Pane anchorPane = fxmlLoader.load();
                    AdminPanelOrderItem controller = fxmlLoader.getController();
                    controller.setData( providers.get(i), mainMenuController);
                    row++;
                    if(providers.get(i).getStatus().contains("Отмененно")&&sort==1){
                        gridPane.add(anchorPane, 0, row);
                    }
                    if(providers.get(i).getStatus().contains("Ожидание выполнения")&&sort==2){
                        gridPane.add(anchorPane, 0, row);
                    }
                    if(providers.get(i).getStatus().contains("Проверка")&&sort==3){
                        gridPane.add(anchorPane, 0, row);
                    }
                    if(sort==0){
                        gridPane.add(anchorPane, 0, row);
                    }
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
    public void sortOnlyCanceled(){
        sort = 1;
        updategrid(searchField.getText(),ingredientBox.getValue());
    }
    public void sortOnlyVerifying(){
        sort = 2;
        updategrid(searchField.getText(),ingredientBox.getValue());
    }
    public void sortOnlyChecking(){
        sort = 3;
        updategrid(searchField.getText(),ingredientBox.getValue());
    }
    public void searchReset(){
        sort = 0;
        updategrid("","");
    }
    public void printOrders() throws IOException {
        String filePath = "1.xlsx";
        List<Order> OrderList = DatabaseController.getAllOrders();
        Workbook excelWookBook = new XSSFWorkbook();
        Sheet employeeSheet = excelWookBook.createSheet("Orders");
        Row headerRow = employeeSheet.createRow(0);

        headerRow.createCell(0).setCellValue("Organisation");
        headerRow.createCell(1).setCellValue("Contacts");
        headerRow.createCell(2).setCellValue("OrderDate");
        headerRow.createCell(3).setCellValue("Quantity");
        headerRow.createCell(4).setCellValue("Status");
        int size = OrderList.size();
        for(int i=0;i<size;i++)
        {
            Order eDto = OrderList.get(i);
            Row row = employeeSheet.createRow(i+1);
            row.createCell(0).setCellValue(eDto.getOrganzition());
            row.createCell(1).setCellValue(eDto.getContact());
            row.createCell(2).setCellValue(eDto.getDate().toString());
            row.createCell(3).setCellValue(eDto.getQuantity());
            row.createCell(4).setCellValue(eDto.getStatus());

        }
        FileOutputStream fOut = new FileOutputStream(filePath);
        excelWookBook.write(fOut);
        fOut.close();
    }

}
