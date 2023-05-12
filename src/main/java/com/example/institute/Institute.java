package com.example.institute;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class Institute extends Application {
    StudentController studentController = new StudentController();
    TeacherController teacherController = new TeacherController();

    TeacherDao teacherDao = new TeacherDao();

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            String homePath = System.getProperty("user.home");

            File studentsFile = new File(homePath + File.separator + "students.json");
            if (!studentsFile.exists()) {
                studentsFile.createNewFile();
                FileWriter fileWriter = new FileWriter(studentsFile);
                fileWriter.write("[]");
                fileWriter.close();
            }

            File teachersFile = new File(homePath + File.separator + "teachers.json");
            if (!teachersFile.exists()) {
                teachersFile.createNewFile();
                FileWriter fileWriter = new FileWriter(teachersFile);
                fileWriter.write("[]");
                fileWriter.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Інформаційна система");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);



        Button button1 = new Button("Додати студента");
        Button button2 = new Button("Список студентів");
        Button button3 = new Button("Додати викладача");
        Button button4 = new Button("Список викладачів");
        Button button5 = new Button("Показати доцентів та професорів");

        button1.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 14pt; -fx-text-fill: #000000; -fx-pref-width: 400px; -fx-pref-height: 30px;");
        button2.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 14pt; -fx-text-fill: #000000; -fx-pref-width: 400px; -fx-pref-height: 30px;");
        button3.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 14pt; -fx-text-fill: #000000; -fx-pref-width: 400px; -fx-pref-height: 30px;");
        button4.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 14pt; -fx-text-fill: #000000; -fx-pref-width: 400px; -fx-pref-height: 30px;");
        button5.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 14pt; -fx-text-fill: #000000; -fx-pref-width: 400px; -fx-pref-height: 30px;");


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

        Scene scene = new Scene(gridPane, 900, 800);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}