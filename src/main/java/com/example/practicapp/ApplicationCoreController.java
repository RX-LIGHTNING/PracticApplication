package com.example.practicapp;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class ApplicationCoreController extends Application {
    private static double xOffset = 0;
    private static double yOffset = 0;
    private static Stage st;
    @Override
    public void start(Stage stage) throws IOException {
        st = new Stage();
        ShowSingInMenu();
    }
    public static void ShowSingInMenu() throws IOException {
        st.hide();
        st = new Stage();
        Parent root = FXMLLoader.load(ApplicationCoreController.class.getResource("SignIn.fxml"));
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                st.setX(event.getScreenX() - xOffset);
                st.setY(event.getScreenY() - yOffset);
            }
        });
        st.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        st.setTitle("Vkid");
        st.setScene(scene);
        st.show();
    }
    public static void ShowSingUpMenu() throws IOException {
        st.hide();
        st = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(ApplicationCoreController.class.getResource("SignUp.fxml")));
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                st.setX(event.getScreenX() - xOffset);
                st.setY(event.getScreenY() - yOffset);
            }
        });
        st.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        st.setTitle("Vkid");
        st.setScene(scene);
        st.show();
    }
    public static void ShowMainMenu() throws IOException {
        st.hide();
        st = new Stage();

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(ApplicationCoreController.class.getResource("MainMenu.fxml")));
        Parent root = (Parent)loader.load();
        MainMenuController controller =loader.getController();
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) { 
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                st.setX(event.getScreenX() - xOffset);
                st.setY(event.getScreenY() - yOffset);
            }
        });
        st.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        st.setTitle("Vkid");
        st.setScene(scene);
        st.show();
        controller.setData(st);
    }
    public static void main(String[] args) {
        launch();
    }
}