package com.example.institute;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StudentController {
    List<Student> students = new ArrayList<>();
    StudentService studentDao = new StudentService();
    public void outputStudents() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Список студентів");



        TableView<Student> studentsTable = new TableView<>();
        studentsTable.setEditable(true);


        TableColumn<Student, String> lastNameCol = new TableColumn<>("Прізвище");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setOnEditCommit(event -> {
            Student student = event.getRowValue();
            student.setLastName(event.getNewValue());
            try {
                studentDao.saveStudentAfterUpdate(student);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        TableColumn<Student, String> firstNameCol = new TableColumn<>("Ім'я");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit(event -> {
            Student student = event.getRowValue();
            student.setFirstName(event.getNewValue());
            try {
                studentDao.saveStudentAfterUpdate(student);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        TableColumn<Student, String> middleNameCol = new TableColumn<>("По-батькові");
        middleNameCol.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        middleNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        middleNameCol.setOnEditCommit(event -> {
            Student student = event.getRowValue();
            student.setMiddleName(event.getNewValue());
            try {
                studentDao.saveStudentAfterUpdate(student);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        TableColumn<Student, String> birthDateCol = new TableColumn<>("Дата народження");
        birthDateCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        birthDateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        birthDateCol.setOnEditCommit(event -> {
            Student student = event.getRowValue();
            student.setDateOfBirth(event.getNewValue());
            try {
                studentDao.saveStudentAfterUpdate(student);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        TableColumn<Student, String> yearOfEntryCol = new TableColumn<>("Рік вступу");
        yearOfEntryCol.setCellValueFactory(new PropertyValueFactory<>("yearOfAdmission"));
        yearOfEntryCol.setCellFactory(TextFieldTableCell.forTableColumn());
        yearOfEntryCol.setOnEditCommit(event -> {
            Student student = event.getRowValue();
            student.setYearOfAdmission(event.getNewValue());
            try {
                studentDao.saveStudentAfterUpdate(student);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        TableColumn<Student, String> yearOfGraduationCol = new TableColumn<>("Рік закінчення");
        yearOfGraduationCol.setCellValueFactory(new PropertyValueFactory<>("yearOfGraduation"));
        yearOfGraduationCol.setCellFactory(TextFieldTableCell.forTableColumn());
        yearOfEntryCol.setOnEditCommit(event -> {
            Student student = event.getRowValue();
            student.setYearOfGraduation(event.getNewValue());
            try {
                studentDao.saveStudentAfterUpdate(student);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        studentsTable.getColumns().addAll(lastNameCol, firstNameCol, middleNameCol, birthDateCol, yearOfEntryCol, yearOfGraduationCol);

        TableColumn<Student, Void> actionsColumn = new TableColumn<>("Відрахування");

        actionsColumn.setCellFactory(col -> {
            TableCell<Student, Void> cell = new TableCell<>() {
                private final Button deleteButton = new Button("Відрахувати");

                {
                    deleteButton.setStyle("-fx-background-color: red; -fx-font-size: 10pt; -fx-text-fill: #000000; -fx-pref-width: 100px; -fx-pref-height: 20px;");
                    deleteButton.setOnAction(event -> {
                        Student selectedStudent = studentsTable.getSelectionModel().getSelectedItem();
                        try {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Підтвердження відрахування");
                            alert.setHeaderText("Відрахування студента");
                            alert.setContentText("Ви впевнені, що хочете відрахувати цього студента?");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                students.remove(selectedStudent);
                                StudentService.deleteStudent(selectedStudent);
                                stage.close();
                            }
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    });
                }
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(deleteButton);
                    }
                }
            };
            return cell;
        });

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


        TextField searchField = new TextField();
        searchField.setPromptText("Введіть прізвище студента");
        Button searchButton = new Button("Пошук");
        searchButton.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 14pt; -fx-text-fill: #000000; -fx-pref-width: 400px; -fx-pref-height: 30px;");

        ObjectMapper mapper = new ObjectMapper();
        String userDir = System.getProperty("user.home");
        Path studentsFilePath = Paths.get(userDir, "students.json");

        List<Student> existingStudents = new ArrayList<>();
        if (Files.exists(studentsFilePath)){
            existingStudents = mapper.readValue(studentsFilePath.toFile(),
                    new TypeReference<List<Student>>(){});
        }
        ObservableList<Student> studentObservableList = FXCollections.observableList(existingStudents);

        FilteredList<Student> filteredData = new FilteredList<>(studentObservableList, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(student -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (student.getLastName().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }

                return false;
            });
        });

        SortedList<Student> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(studentsTable.comparatorProperty());

        studentsTable.setItems(sortedData);

        searchButton.setOnAction(e -> {
            String searchValue = searchField.getText().trim();
            if (!searchValue.isEmpty()){
                filteredData.setPredicate(student -> student.getLastName().toLowerCase().contains(searchValue.toLowerCase()));
            }
        });

        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(searchField, searchButton, studentsTable);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox, 900, 800);
        stage.setScene(scene);
        stage.show();
    }

    public void addStudentsWindow(){
        Stage stage = new Stage();
        stage.setTitle("Додавання нового студента");

        VBox container = new VBox();
        container.setSpacing(10);
        container.setPadding(new Insets(10));
        container.setAlignment(Pos.CENTER);

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
        studentSaveButton.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 14pt; -fx-text-fill: #000000; -fx-pref-width: 400px; -fx-pref-height: 30px;");
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
                StudentService.addStudents(students);
                stage.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        container.getChildren().add(studentSaveButton);

        Scene scene = new Scene(container, 900, 800);
        stage.setScene(scene);
        stage.show();
    }
}
