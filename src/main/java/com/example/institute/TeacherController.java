package com.example.institute;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TeacherController {

    List<Teacher> teachers = new ArrayList<>();

    TeacherDao teacherDao = new TeacherDao();

    public void addTeachersWindow(){
        Stage stage = new Stage();
        stage.setTitle("Додавання викладача");

        VBox container = new VBox();
        container.setSpacing(10);
        container.setPadding(new Insets(10));

        TextField teachersLastNameField = new TextField();
        teachersLastNameField.setPromptText("Прізвище");
        container.getChildren().add(teachersLastNameField);

        TextField teachersFirstNameField = new TextField();
        teachersFirstNameField.setPromptText("Ім'я");
        container.getChildren().add(teachersFirstNameField);

        TextField teachersMiddleNameField = new TextField();
        teachersMiddleNameField.setPromptText("По-батькові");
        container.getChildren().add(teachersMiddleNameField);

        Label degreeLabel = new Label("Науковий ступінь:");
        container.getChildren().add(degreeLabel);
        ObservableList<String> degreeList = FXCollections.observableArrayList(
                "Кандидат наук",
                "Доктор наук"
        );
        ComboBox<String> degreeComboBox = new ComboBox<>(degreeList);
        container.getChildren().add(degreeComboBox);

        Label positionLabel = new Label("Науковий ступінь:");
        container.getChildren().add(positionLabel);
        ObservableList<String> positionList = FXCollections.observableArrayList(
                "Викладач",
                "Старший викладач",
                "Доцент",
                "Професор"
        );
        ComboBox<String> positionComboBox = new ComboBox<>(positionList);
        container.getChildren().add(positionComboBox);

        Button teacherSaveButton = new Button("Зберегти");
        teacherSaveButton.setOnAction(e -> {
            try {
                UUID id = UUID.randomUUID();
                Teacher newTeacher = new Teacher(
                        id,
                        teachersLastNameField.getText(),
                        teachersFirstNameField.getText(),
                        teachersMiddleNameField.getText(),
                        degreeComboBox.getValue(),
                        positionComboBox.getValue());
                teachers.add(newTeacher);
                TeacherDao.addTeachers(teachers);
                stage.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        container.getChildren().add(teacherSaveButton);

        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();
    }

    public void outputTeachers(){
        Stage stage = new Stage();
        stage.setTitle("Список викладачів");

        TableView<Teacher> teachersTable = new TableView<>();
        teachersTable.setEditable(true);


        TableColumn<Teacher, String> lastNameCol = new TableColumn<>("Прізвище");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setOnEditCommit(event -> {
            Teacher teacher = event.getRowValue();
            teacher.setLastName(event.getNewValue());
            try {
                teacherDao.saveTeacherAfterUpdate(teacher);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        TableColumn<Teacher, String> firstNameCol = new TableColumn<>("Ім'я");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit(event -> {
            Teacher teacher = event.getRowValue();
            teacher.setFirstName(event.getNewValue());
            try {
                teacherDao.saveTeacherAfterUpdate(teacher);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        TableColumn<Teacher, String> middleNameCol = new TableColumn<>("По-батькові");
        middleNameCol.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        middleNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        middleNameCol.setOnEditCommit(event -> {
            Teacher teacher = event.getRowValue();
            teacher.setMiddleName(event.getNewValue());
            try {
                teacherDao.saveTeacherAfterUpdate(teacher);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        TableColumn<Teacher, String> degreeCol = new TableColumn<>("Вчений ступінь");
        ObservableList<String> academicDegrees = FXCollections.observableArrayList("Кандидат наук", "Доктор наук");
        ComboBoxTableCell<Teacher, String> comboBoxDegreeCell = new ComboBoxTableCell<>(academicDegrees);
        degreeCol.setCellFactory(ComboBoxTableCell.forTableColumn(academicDegrees));
        degreeCol.setCellValueFactory(new PropertyValueFactory<>("academicDegree"));
        degreeCol.setOnEditCommit(event -> {
            Teacher teacher = event.getRowValue();
            teacher.setAcademicDegree(String.valueOf(event.getNewValue()));
            try {
                teacherDao.saveTeacherAfterUpdate(teacher);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        TableColumn<Teacher, String> positionCol = new TableColumn<>("Посада");
        ObservableList<String> positions = FXCollections.observableArrayList("Викладач", "Старший викладач", "Доцент", "Професор");
        ComboBoxTableCell<Teacher, String> comboBoxCell = new ComboBoxTableCell<>(positions);
        positionCol.setCellFactory(ComboBoxTableCell.forTableColumn(positions));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        positionCol.setOnEditCommit(event -> {
            Teacher teacher = event.getRowValue();
            teacher.setPosition(event.getNewValue());
            try {
                teacherDao.saveTeacherAfterUpdate(teacher);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        teachersTable.getColumns().addAll(lastNameCol, firstNameCol, middleNameCol, degreeCol, positionCol);

        TableColumn<Teacher, Void> deleteColumn = new TableColumn<>("Звільнення");

        deleteColumn.setCellFactory(col -> {
            TableCell<Teacher, Void> cell = new TableCell<>(){
              private final Button deleteButton = new Button("Звільнити");

                {
                    deleteButton.setOnAction(event -> {
                        Teacher selectedTeacher = teachersTable.getSelectionModel().getSelectedItem();
                        try {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Підтвердження звільнення");
                            alert.setHeaderText("Звільнення викладача");
                            alert.setContentText("Ви впевнені, що хочете звільнити обраного викладача?");

                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.isPresent() && result.get() == ButtonType.OK){
                                teachers.remove(selectedTeacher);
                                TeacherDao.deleteTeacher(selectedTeacher);
                                stage.close();
                            }
                        } catch (IOException e){
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

        teachersTable.getColumns().add(deleteColumn);

        List<Teacher> teachers = null;
        try {
            teachers = teacherDao.showTeachers();
        } catch (IOException e){
            e.printStackTrace();
        }

        if (teachers != null){
            teachersTable.getItems().addAll(teachers);
        }

        Scene scene = new Scene(teachersTable);
        stage.setScene(scene);
        stage.show();
    }
}
