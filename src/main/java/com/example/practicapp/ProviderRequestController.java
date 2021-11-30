package com.example.practicapp;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Objects;

public class ProviderRequestController {

        @FXML
        private TextField ryePrice;

        @FXML
        private TextField saltPrice;

        @FXML
        private TextField wheatPrice;

        @FXML
        private TextField yeastPrice;
        @FXML
        private Text checktext;
        MainMenuController mainMenuController;
        public void setData(MainMenuController mainMenuController){
            this.mainMenuController = mainMenuController;
        }
        public void submitRequest() throws IOException {
                if (
                        !Objects.equals(ryePrice.getText(), "") &&
                        !Objects.equals(saltPrice.getText(), "") &&
                        !Objects.equals(wheatPrice.getText(), "") &&
                        !Objects.equals(yeastPrice.getText(), "")
                )
                {

                        DatabaseController.ProviderRequestInsert(
                                ryePrice.getText(),
                                saltPrice.getText(),
                                wheatPrice.getText(),
                                yeastPrice.getText()
                        );
                        mainMenuController.setProvidersPane();
                }
                else {
                        checktext.setVisible(true);
                }
        }
}
