package com.example.institute;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Institute extends Application {
    public ArrayList<Teacher> teachers = new ArrayList<>();
    public ArrayList<Student> students = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws IOException {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        Button button1 = new Button("Cписок вчителів");
        Button button2 = new Button("Список студентів");
        Button button3 = new Button("Пошук студентів");
        Button button4 = new Button("Переглянути всіх професорів та доцентів");


        button1.setOnAction(e -> {

        });
        button2.setOnAction(e -> {

        });

        button3.setOnAction(e -> {
            // Відкриття форми 3
        });

        button4.setOnAction(e -> {
            // Відкриття форми 4
        });

        gridPane.add(button1, 0, 0);
        gridPane.add(button2, 0, 1);
        gridPane.add(button3, 0, 2);
        gridPane.add(button4, 0, 3);

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(gridPane, 300, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}