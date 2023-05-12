package com.example.institute;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Institute extends Application {
    StudentController studentController = new StudentController();
    TeacherController teacherController = new TeacherController();

    TeacherDao teacherDao = new TeacherDao();

    @Override
    public void start(Stage primaryStage) throws IOException {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        Button button1 = new Button("Додати студента");
        Button button2 = new Button("Список студентів");
        Button button3 = new Button("Додати викладача");
        Button button4 = new Button("Список викладачів");
        Button button5 = new Button("Показати доцентів та професорів");


        button1.setOnAction(e -> {
            studentController.addStudentsWindow();
        });
        button2.setOnAction(e -> {
            try {
                studentController.outputStudents();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        button3.setOnAction(e -> {
            teacherController.addTeachersWindow();
        });

        button4.setOnAction(e -> {
            teacherController.outputTeachers();
        });

        button5.setOnAction(e -> {
            try {
                teacherDao.showAcademicTeachers();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        gridPane.add(button1, 0, 0);
        gridPane.add(button2, 0, 1);
        gridPane.add(button3, 0, 2);
        gridPane.add(button4, 0, 3);
        gridPane.add(button5, 0, 4);

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(gridPane, 300, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}