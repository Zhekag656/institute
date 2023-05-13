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
import java.nio.file.Path;

public class InstituteApplication extends Application {
    private final StudentController studentController = new StudentController();
    private final TeacherController teacherController = new TeacherController();

    private final TeacherService teacherService = new TeacherService();

    @Override
    public void start(Stage primaryStage) throws IOException {
        String homePath = System.getProperty("user.home");
        String studentsFilePath = homePath + File.separator + "students.json";
        String teachersFilePath = homePath + File.separator + "teachers.json";

        if (!Files.exists(Path.of(studentsFilePath))) {
            createEmptyJsonFile(studentsFilePath);
        }

        if (!Files.exists(Path.of(teachersFilePath))) {
            createEmptyJsonFile(teachersFilePath);
        }
        primaryStage.setTitle("Інформаційна система");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        Button addStudentsButton = new Button("Додати студента");
        Button studentListButton = new Button("Список студентів");
        Button addTeacherButton = new Button("Додати викладача");
        Button teacherListButton = new Button("Список викладачів");
        Button showProfessorsButton = new Button("Показати доцентів та професорів");

        addStudentsButton.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 14pt; -fx-text-fill: #000000; -fx-pref-width: 400px; -fx-pref-height: 30px;");
        studentListButton.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 14pt; -fx-text-fill: #000000; -fx-pref-width: 400px; -fx-pref-height: 30px;");
        addTeacherButton.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 14pt; -fx-text-fill: #000000; -fx-pref-width: 400px; -fx-pref-height: 30px;");
        teacherListButton.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 14pt; -fx-text-fill: #000000; -fx-pref-width: 400px; -fx-pref-height: 30px;");
        showProfessorsButton.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 14pt; -fx-text-fill: #000000; -fx-pref-width: 400px; -fx-pref-height: 30px;");

        addStudentsButton.setOnAction(e -> {
            studentController.addStudentsWindow();
        });

        studentListButton.setOnAction(e -> {
            studentController.outputStudents();
        });

        addTeacherButton.setOnAction(e -> {
            teacherController.addTeachersWindow();
        });

        teacherListButton.setOnAction(e -> {
            teacherController.outputTeachers();
        });

        showProfessorsButton.setOnAction(e -> {
            teacherService.showAcademicTeachers();
        });

        gridPane.add(addStudentsButton, 0, 0);
        gridPane.add(studentListButton, 0, 1);
        gridPane.add(addTeacherButton, 0, 2);
        gridPane.add(teacherListButton, 0, 3);
        gridPane.add(showProfessorsButton, 0, 4);

        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(gridPane, 900, 800);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void createEmptyJsonFile(String filePath) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write("[]");
        }
    }
}