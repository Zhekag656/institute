package com.example.institute;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentController {
    List<Student> students = new ArrayList<>();
    StudentDao studentDao = new StudentDao();
    public void outputStudents(){
        Stage stage = new Stage();
        stage.setTitle("List of students");

        TableView<Student> studentsTable = new TableView<>();
        TableColumn<Student, String> lastNameCol = new TableColumn<>("Прізвище");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<Student, String> firstNameCol = new TableColumn<>("Ім'я");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<Student, String> middleNameCol = new TableColumn<>("По-батькові");
        middleNameCol.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        TableColumn<Student, LocalDate> birthDateCol = new TableColumn<>("Дата народження");
        birthDateCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        TableColumn<Student, Integer> yearOfEntryCol = new TableColumn<>("Рік вступу");
        yearOfEntryCol.setCellValueFactory(new PropertyValueFactory<>("yearOfAdmission"));
        TableColumn<Student, Integer> yearOfGraduationCol = new TableColumn<>("Рік закінчення");
        yearOfGraduationCol.setCellValueFactory(new PropertyValueFactory<>("yearOfGraduation"));

        studentsTable.getColumns().addAll(lastNameCol, firstNameCol, middleNameCol, birthDateCol, yearOfEntryCol, yearOfGraduationCol);

        TableColumn<Student, Void> actionsColumn = new TableColumn<>("Редагування");

// Встановлюємо фабрику комірок, яка повертає нову комірку з кнопкою
        actionsColumn.setCellFactory(col -> {
            TableCell<Student, Void> cell = new TableCell<>() {
                private final Button editButton = new Button("Редагувати");

                {
                    editButton.setOnAction(event -> {
                        // Обробник події для кнопки
                        Student student = getTableView().getItems().get(getIndex());
                        // дії, які виконуються при натисканні на кнопку "Редагувати"
                    });
                }

                // Описуємо вміст комірки (в даному випадку кнопка "Редагувати")
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(editButton);
                    }
                }
            };
            return cell;
        });

        // Додаємо стовпець до таблиці
        studentsTable.getColumns().add(actionsColumn);

        List<Student> students = null;
        try {
            students = studentDao.showStudents();
        } catch (IOException e){
            e.printStackTrace();
        }

        if (students != null){
            studentsTable.getItems().addAll(students);
        }

        Scene scene = new Scene(studentsTable);
        stage.setScene(scene);
        stage.show();
    }

    public void addStudentsWindow(){
            Stage stage = new Stage();
            stage.setTitle("Додавання нового студента");

            // Створення контейнера для форми
            VBox container = new VBox();
            container.setSpacing(10);
            container.setPadding(new Insets(10));

            // Додавання полів вводу
            TextField studentsLastNameField = new TextField();
            studentsLastNameField.setPromptText("Прізвище");
            container.getChildren().add(studentsLastNameField);

            TextField studentsFirstNameField = new TextField();
            studentsFirstNameField.setPromptText("Ім'я");
            container.getChildren().add(studentsFirstNameField);

            TextField studentsMiddleNameField = new TextField();
            studentsMiddleNameField.setPromptText("По-батькові");
            container.getChildren().add(studentsMiddleNameField);

            TextField dateOfBirthField = new TextField();
            dateOfBirthField.setPromptText("Дата народження (рррр-мм-дд)");
            container.getChildren().add(dateOfBirthField);

            TextField yearOfAdmissionField = new TextField();
            yearOfAdmissionField.setPromptText("Рік вступу");
            container.getChildren().add(yearOfAdmissionField);

            TextField yearOfGraduationField = new TextField();
            yearOfGraduationField.setPromptText("Рік закінчення");
            container.getChildren().add(yearOfGraduationField);

            Button studentSaveButton = new Button("Зберегти");
            studentSaveButton.setOnAction(e -> {
                try {
                    UUID id = UUID.randomUUID();
                    Student newStudent = new Student(
                            id,
                            studentsLastNameField.getText(),
                            studentsFirstNameField.getText(),
                            studentsMiddleNameField.getText(),
                            dateOfBirthField.getText(),
                            yearOfAdmissionField.getText(),
                            yearOfGraduationField.getText());
                    students.add(newStudent);
                    StudentDao.addStudents(students);
                    stage.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            container.getChildren().add(studentSaveButton);

            Scene scene = new Scene(container);
            stage.setScene(scene);
            stage.show();
    }
}
