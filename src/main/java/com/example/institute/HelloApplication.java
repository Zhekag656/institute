package com.example.institute;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

        String[] data = {
                "student, Ivanov, Ivan, Ivanovich, 10.10.2001, 2019, 2024",
                "student, Ivanov, Ivan, Ivanovich, kandidat, vikladach"
        };
        DataSaver.saveData("data.txt", data);
    }
}