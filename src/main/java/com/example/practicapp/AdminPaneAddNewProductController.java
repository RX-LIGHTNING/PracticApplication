package com.example.practicapp;

import com.example.practicapp.objects.Product;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class AdminPaneAddNewProductController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private String picPath;
    @FXML
    private TextArea prodDescription;
    @FXML
    private TextField recipeField;
    private AdminPaneController adminPaneController;
    private byte[] temp;
    Product product;
    public void FileSelecter() throws IOException {

        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        Path pathAbsolute = Paths.get(fileChooser.showOpenDialog(stage).getPath());
        picPath=pathAbsolute.toString();
    }
    public void createNewProduct() throws IOException {
        if(!Objects.equals(prodDescription.getText(), "") &&!Objects.equals(recipeField.getText(), "") && !Objects.equals(priceField.getText(), "") &&!Objects.equals(nameField.getText(), "")&&!picPath.isEmpty()) {
            if(product==null) {
                DatabaseController.ProductInsert(nameField.getText(),
                        Integer.parseInt(priceField.getText()),
                        prodDescription.getText(),
                        new File(picPath),
                        Integer.parseInt(recipeField.getText()));
            }
            else if(picPath.equals("1")){
                DatabaseController.ProductUpdate(nameField.getText(),
                        Integer.parseInt(priceField.getText()),
                        prodDescription.getText(),
                        temp,
                        Integer.parseInt(recipeField.getText()),
                        product.getId());
            }
            else {
                File filefromURL = new File(picPath);
                byte[] file = Files.readAllBytes(filefromURL.toPath());
                    DatabaseController.ProductUpdate(nameField.getText(),
                            Integer.parseInt(priceField.getText()),
                            prodDescription.getText(),
                            file,
                            Integer.parseInt(recipeField.getText()),
                            product.getId());
            }
            adminPaneController.showProductsPane();
        }
    }

    public void setData(AdminPaneController adminPaneController, Product product) {
        this.adminPaneController = adminPaneController;
        if(product != null) {
            this.product = product;
            nameField.setText(product.getName());
            recipeField.setText(String.valueOf(product.getRecipe()));
            prodDescription.setText(product.getDescription());
            priceField.setText(String.valueOf(product.getPrice()));
            temp = product.getImageBytes();
            picPath="1";
        }
    }
}
