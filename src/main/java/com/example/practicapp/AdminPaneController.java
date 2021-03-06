package com.example.practicapp;

import com.example.practicapp.MainMenuController;
import com.example.practicapp.objects.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminPaneController {
    private MainMenuController mainMenuController;
    @FXML
    private BorderPane AdminPane;

    public void showProductsPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AdminPanelProducts.fxml"));
        Pane anchorPane = fxmlLoader.load();
        AdminPaneProductController controller = fxmlLoader.getController();
        controller.setData(AdminPaneController.this);
        AdminPane.setCenter(anchorPane);
    }
    public void showRecipesPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AdminPanelRecipes.fxml"));
        Pane anchorPane = fxmlLoader.load();
        AdminPaneRecipesController controller = fxmlLoader.getController();
        controller.setData(AdminPaneController.this);
        AdminPane.setCenter(anchorPane);
    }
    public void showInformationPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AdminPanelIngred.fxml"));
        Pane anchorPane = fxmlLoader.load();
        AdminPaneIngredients controller = fxmlLoader.getController();
        controller.setData(AdminPaneController.this);
        AdminPane.setCenter(anchorPane);
    }
        public void showProductPricePane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AdminPanelPrices.fxml"));
        Pane anchorPane = fxmlLoader.load();
        AdminPanePrice controller = fxmlLoader.getController();
        controller.setData(AdminPaneController.this);
        AdminPane.setCenter(anchorPane);
    }
    public void showOrdersPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AdminPaneOrders.fxml"));
        Pane anchorPane = fxmlLoader.load();
        AdminPaneOrders controller = fxmlLoader.getController();
        controller.setData(AdminPaneController.this);
        AdminPane.setCenter(anchorPane);
    }
    public void showProductAdd(Product mode) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AdminPanelAddNewProduct.fxml"));
        Pane anchorPane = fxmlLoader.load();
        AdminPaneAddNewProductController controller = fxmlLoader.getController();
        controller.setData(AdminPaneController.this, mode);
        AdminPane.setCenter(anchorPane);
    }
    public void showProductModify(Product mode) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AdminPanelAddNewProduct.fxml"));
        Pane anchorPane = fxmlLoader.load();
        AdminPaneAddNewProductController controller = fxmlLoader.getController();
        controller.setData(AdminPaneController.this, mode);
        AdminPane.setCenter(anchorPane);
    }
    public void showStatistics() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("AdminPanelStat.fxml"));
        Pane anchorPane = fxmlLoader.load();
        AdminPaneStats controller = fxmlLoader.getController();
        controller.setData(AdminPaneController.this);
        AdminPane.setCenter(anchorPane);
    }
    public void setData(MainMenuController  mainMenuController) {
        this.mainMenuController = mainMenuController;
    }


}