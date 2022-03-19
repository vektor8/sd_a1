package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Scene scene;
    private static Stage window;

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        scene = new Scene(loadFXML("hello-view"));
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml, Integer v, Integer v1) throws IOException {
        window.setMinWidth(v);
        window.setMinHeight(v1);
        scene.setRoot(loadFXML(fxml));
    }

    static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) { launch();}
}