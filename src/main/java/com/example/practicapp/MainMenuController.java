package com.example.practicapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    @FXML
    Button CustomerButton1;
    @FXML
    Button CustomerButton11;
    @FXML
    Button CustomerButton12;
    @FXML
    Button CustomerButton13;
    @FXML
    BorderPane UIWorkSpace;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Icons/cart.png")));
        CustomerButton1.setGraphic(new ImageView(image));
        //
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Icons/order.png")));
        CustomerButton11.setGraphic(new ImageView(image));
        //
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Icons/settings.png")));
        CustomerButton12.setGraphic(new ImageView(image));
        //
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Icons/exit.png")));
        CustomerButton13.setGraphic(new ImageView(image));
    }
    public void setProductListPane() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("MainMenuPages/ProductList.fxml"));
        Pane anchorPane = fxmlLoader.load();
        ProductListController controller = fxmlLoader.getController();
        controller.setData(MainMenuController.this);
         UIWorkSpace.setCenter(anchorPane);
    }

    public void exitApplication(){
        System.exit(1);
    }
}
