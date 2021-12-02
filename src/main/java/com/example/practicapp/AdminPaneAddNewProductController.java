package com.example.practicapp;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
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
    private int mode;
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
            DatabaseController.ProductInsert(nameField.getText(),
                    Integer.parseInt(priceField.getText()),
                    prodDescription.getText(),
                    new File(picPath),
                    Integer.parseInt(recipeField.getText()));
        }
    }

    public void setData(AdminPaneController adminPaneController, int mode) {
        this.adminPaneController = adminPaneController;
        this.mode = mode;
    }
}
