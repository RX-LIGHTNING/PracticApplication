package com.example.practicapp;

import com.example.practicapp.objects.Product;
import com.example.practicapp.objects.Provider;
import com.example.practicapp.objects.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    Button CustomerButton1;
    @FXML
    Button CustomerButton11;
    @FXML
    Button SettingButton;
    @FXML
    Button ExitButton;
    @FXML
    Button ProviderButton;
    @FXML
    Button ProviderButton1;
    @FXML
    Button ProviderButton2;
    @FXML
    BorderPane UIWorkSpace;
    @FXML
    VBox NavBar;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            Image image;
            image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Icons/cart.png")));
            CustomerButton1.setGraphic(new ImageView(image));
            //
            image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Icons/order.png")));
            CustomerButton11.setGraphic(new ImageView(image));
            //
            image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Icons/settings.png")));
            SettingButton.setGraphic(new ImageView(image));
            //
            image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Icons/exit.png")));
            ExitButton.setGraphic(new ImageView(image));
            //
            image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Icons/providers.png")));
            ProviderButton.setGraphic(new ImageView(image));
            //
            image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Icons/createticket.png")));
            ProviderButton1.setGraphic(new ImageView(image));
            //
            image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Icons/tickets.png")));
            ProviderButton2.setGraphic(new ImageView(image));
        if(User.getFlag() == 1){
            NavBar.getChildren().remove(ProviderButton);
            NavBar.getChildren().remove(ProviderButton1);
            NavBar.getChildren().remove(ProviderButton2);
        }
        else if(User.getFlag() == 2){
            NavBar.getChildren().remove(CustomerButton1);
            NavBar.getChildren().remove(CustomerButton11);
        }
    }
    public void setProductListPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ProductList.fxml"));
        Pane anchorPane = fxmlLoader.load();
        ProductListController controller = fxmlLoader.getController();
        controller.setData(MainMenuController.this);
         UIWorkSpace.setCenter(anchorPane);
    }
    public void setProvidersPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Providers.fxml"));
        Pane anchorPane = fxmlLoader.load();
        ProvidersController controller = fxmlLoader.getController();
        controller.setData(MainMenuController.this);
        UIWorkSpace.setCenter(anchorPane);
    }
    public void setOrderPane(Product product) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Order.fxml"));
        Pane anchorPane = fxmlLoader.load();
        OrderController controller = fxmlLoader.getController();
        controller.setData(MainMenuController.this, product);
        UIWorkSpace.setCenter(anchorPane);
    }
    public void setMyOrderPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("MyOrders.fxml"));
        Pane anchorPane = fxmlLoader.load();
        MyOrderController controller = fxmlLoader.getController();
        controller.setData(MainMenuController.this);
        UIWorkSpace.setCenter(anchorPane);
//        File fi = new File("1.jpg");
//        byte[] fileContent = Files.readAllBytes(fi.toPath());
    }
    public void setProviderMorePane(Provider provider) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ProviderMore.fxml"));
        Pane anchorPane = fxmlLoader.load();
        ProviderMoreController controller = fxmlLoader.getController();
        controller.setData(MainMenuController.this, provider);
        UIWorkSpace.setCenter(anchorPane);
    }
    public void setProviderRequestRegistryPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("ProviderRequest.fxml"));
        Pane anchorPane = fxmlLoader.load();
        ProviderRequestController controller = fxmlLoader.getController();
        controller.setData(MainMenuController.this);
        UIWorkSpace.setCenter(anchorPane);
    }
    public void setMyProviderRequestPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("MyProviderRequest.fxml"));
        Pane anchorPane = fxmlLoader.load();
        MyProviderRequest controller = fxmlLoader.getController();
        controller.setData(MainMenuController.this);
        UIWorkSpace.setCenter(anchorPane);
    }
    public void exitApplication(){
        System.exit(1);
    }
}
