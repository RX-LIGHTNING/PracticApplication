package com.example.practicapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Icons/cart.png")));
        CustomerButton1.setGraphic(new ImageView(image));
        CustomerButton1.graphicProperty().getValue().setScaleX(0.5);
        CustomerButton1.graphicProperty().getValue().setScaleY(0.5);
        //
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Icons/order.png")));
        CustomerButton11.setGraphic(new ImageView(image));
        CustomerButton11.graphicProperty().getValue().setScaleX(0.5);
        CustomerButton11.graphicProperty().getValue().setScaleY(0.5);
        //
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Icons/settings.png")));
        CustomerButton12.setGraphic(new ImageView(image));
        CustomerButton12.graphicProperty().getValue().setScaleX(0.3);
        CustomerButton12.graphicProperty().getValue().setScaleY(0.3);
        //
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Icons/exit.png")));
        CustomerButton13.setGraphic(new ImageView(image));
        CustomerButton13.graphicProperty().getValue().setScaleX(0.5);
        CustomerButton13.graphicProperty().getValue().setScaleY(0.5);
    }
}
