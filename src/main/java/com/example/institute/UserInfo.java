package com.example.institute;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class UserInfo extends Application {
    private String[][] students;
    private String[][] teachers;

    @Override
    public void start(Stage stage) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label label = new Label("Введіть прізвище студента:");
        TextField textField = new TextField();
        Button button = new Button("Знайти");
        TextArea textArea = new TextArea();

        gridPane.add(label, 0, 0);
        gridPane.add(textField, 1, 0);
        gridPane.add(button, 2, 0);
        gridPane.add(textArea, 0, 1, 3, 1);

        button.setOnAction(event -> {
            String surname = textField.getText();
            String[] studentData = searchStudent(surname, students);
            if (studentData != null) {
                // виведення даних про студента
                textArea.setText(String.format(
                        "Студент: %s %s %s\n" +
                                "Дата народження: %s\n" +
                                "Рік вступу: %s\n" +
                                "Рік закінчення: %s\n",
                        studentData[0], studentData[1], studentData[2],
                        studentData[3], studentData[4], studentData[5]));
            } else {
                // повідомлення про помилку
                textArea.setText("Студента з таким прізвищем не знайдено!");
            }
        });

        Scene scene = new Scene(gridPane, 500, 200);
        stage.setScene(scene);
        stage.show();
    }

    public static Student searchStudent(String surname, Student[] students) {
        for (int i = 0; i < students.length; i++) {
            if (students[i].getSurname().equals(surname)) {
                return students[i];
            }
        }
        return null;
    }

}