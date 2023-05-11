//package com.example.institute;
//
//import javafx.collections.FXCollections;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.GridPane;
//
//import java.io.IOException;
//
//public class test {
//    primaryStage.setTitle("Інститут");
//    GridPane gridPane = new GridPane();
//        gridPane.setPadding(new Insets(10, 10, 10, 10));
//        gridPane.setHgap(10);
//        gridPane.setVgap(10);
//
//    TabPane tabPane = new TabPane();
//        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
//        tabPane.prefWidthProperty().bind(primaryStage.widthProperty());
//
//    Tab teacherTab = new Tab("Додати вчителя");
//        teacherTab.setClosable(false);
//    GridPane teacherPane = new GridPane();
//        teacherPane.setPadding(new Insets(10, 10, 10, 10));
//        teacherPane.setHgap(10);
//        teacherPane.setVgap(10);
//
//    Label teachersLastNameLabel = new Label("Прізвище");
//        GridPane.setConstraints(teachersLastNameLabel, 0, 0);
//    TextField teachersLastNameField = new TextField();
//        GridPane.setConstraints(teachersLastNameField, 1, 0);
//
//    Label teachersFirstNameLabel = new Label("Ім'я");
//        GridPane.setConstraints(teachersFirstNameLabel, 0, 1);
//    TextField teachersFirstNameField = new TextField();
//        GridPane.setConstraints(teachersFirstNameField, 1, 1);
//
//    Label teachersMiddleNameLabel = new Label("По-батькові");
//        GridPane.setConstraints(teachersMiddleNameLabel, 0, 2);
//    TextField teachersMiddleNameField = new TextField();
//        GridPane.setConstraints(teachersMiddleNameField, 1, 2);
//
//    Label gradeLabel = new Label("Вчене звання");
//        GridPane.setConstraints(gradeLabel, 0, 4);
//    ChoiceBox<String> gradeChoiceBox = new ChoiceBox<>();
//        gradeChoiceBox.getItems().addAll("Кандидат наук", "Доктор наук");
//        GridPane.setConstraints(gradeChoiceBox, 1, 4);
//
//    Label positionLabel = new Label("Посада");
//        GridPane.setConstraints(positionLabel, 0, 6);
//    ChoiceBox<String> positionChoiceBox = new ChoiceBox<>();
//        positionChoiceBox.getItems().addAll("Викладач", "Старший викладач", "Доцент", "Професор");
//        GridPane.setConstraints(positionChoiceBox, 1, 6);
//
//    Button saveButton1 = new Button("Зберегти");
//        saveButton1.setOnAction(e -> {
//        try {
//            saveTeacher(
//                    teachersLastNameField.getText(),
//                    teachersFirstNameField.getText(),
//                    teachersMiddleNameField.getText(),
//                    gradeChoiceBox.getValue(),
//                    positionChoiceBox.getValue());
//        } catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }
//    });
//        GridPane.setConstraints(saveButton1, 0, 8);
//
//        teacherPane.getChildren().addAll(
//            teachersLastNameLabel,
//            teachersLastNameField,
//            teachersFirstNameLabel,
//            teachersFirstNameField,
//            teachersMiddleNameLabel,
//            teachersMiddleNameField,
//            gradeLabel,
//            gradeChoiceBox,
//            positionLabel,
//            positionChoiceBox,
//            saveButton1);
//
//        teacherTab.setContent(teacherPane);
//        tabPane.getTabs().add(teacherTab);
//
//
//
//
//
//    Tab studentTab = new Tab("Додати студента");
//        studentTab.setClosable(false);
//
//    GridPane studentPane = new GridPane();
//        studentPane.setPadding(new Insets(10, 10, 10, 10));
//        studentPane.setHgap(10);
//        studentPane.setVgap(10);
//
//    Label studentsLastNameLabel = new Label("Прізвище:");
//        GridPane.setConstraints(studentsLastNameLabel, 0, 0);
//    TextField studentsLastNameField = new TextField();
//        GridPane.setConstraints(studentsLastNameField, 1, 0);
//
//    Label studentsFirstNameLabel = new Label("Ім'я:");
//        GridPane.setConstraints(studentsFirstNameLabel, 0, 1);
//    TextField studentsFirstNameField = new TextField();
//        GridPane.setConstraints(studentsFirstNameField, 1, 1);
//
//    Label studentsMiddleNameLabel = new Label("По-батькові:");
//        GridPane.setConstraints(studentsMiddleNameLabel, 0, 2);
//    TextField studentsMiddleNameField = new TextField();
//        GridPane.setConstraints(studentsMiddleNameField, 1, 2);
//
//    Label dateOfBirthLabel = new Label("Дата народження(рррр-мм-дд):");
//        GridPane.setConstraints(dateOfBirthLabel, 0, 3);
//    TextField dateOfBirthField = new TextField();
//        GridPane.setConstraints(dateOfBirthField, 1, 3);
//
//    Label yearOfAdmissionLabel = new Label("Рік вступу :");
//        GridPane.setConstraints(yearOfAdmissionLabel, 0, 4);
//    TextField yearOfAdmissionField = new TextField();
//        GridPane.setConstraints(yearOfAdmissionField, 1, 4);
//
//    Label yearOfGraduationLabel = new Label("Рік випуску:");
//        GridPane.setConstraints(yearOfGraduationLabel, 0, 5);
//    TextField yearOfGraduationField = new TextField();
//        GridPane.setConstraints(yearOfGraduationField, 1, 5);
//
//    Button studentSaveButton = new Button("Зберегти");
//        studentSaveButton.setOnAction(e -> {
//        try {
//            saveStudent(
//                    studentsLastNameField.getText(),
//                    studentsFirstNameField.getText(),
//                    studentsMiddleNameField.getText(),
//                    dateOfBirthField.getText(),
//                    Integer.parseInt(yearOfAdmissionField.getText()),
//                    yearOfGraduationField.getText());
//        } catch (IOException ex) {
//            throw new RuntimeException(ex);
//        }
//    });
//        GridPane.setConstraints(studentSaveButton, 0, 10);
//
//        studentPane.getChildren().addAll(
//            studentsLastNameLabel,
//            studentsLastNameField,
//            studentsFirstNameLabel,
//            studentsFirstNameField,
//            studentsMiddleNameLabel,
//            studentsMiddleNameField,
//            dateOfBirthLabel,
//            dateOfBirthField,
//            yearOfAdmissionLabel,
//            yearOfAdmissionField,
//            yearOfGraduationLabel,
//            yearOfGraduationField,
//            studentSaveButton);
//
//        studentTab.setContent(studentPane);
//
//        tabPane.getTabs().add(studentTab);
//
//
//
//
//
//
//
//    Tab viewTeachersTab = new Tab("Всі вчителі");
//
//    TableView<Teacher> teachersTable = new TableView<>();
//        teachersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//
//    TableColumn<Teacher, String> lastNameColumn = new TableColumn<>("Прізвище");
//        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("teachersLastName"));
//        lastNameColumn.setMinWidth(100);
//
//    TableColumn<Teacher, String> firstNameColumn = new TableColumn<>("Ім'я");
//        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("teachersFirstName"));
//        firstNameColumn.setMinWidth(100);
//
//    TableColumn<Teacher, String> middleNameColumn = new TableColumn<>("По-батькові");
//        middleNameColumn.setCellValueFactory(new PropertyValueFactory<>("teachersMiddleName"));
//        middleNameColumn.setMinWidth(100);
//
//    TableColumn<Teacher, String> birthdayColumn = new TableColumn<>("Вчене звання");
//        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("gradeLevel"));
//        birthdayColumn.setMinWidth(100);
//
//    TableColumn<Teacher, String> departmentColumn = new TableColumn<>("Посада");
//        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
//        departmentColumn.setMinWidth(100);
//
//        teachersTable.getColumns().addAll(lastNameColumn, firstNameColumn, middleNameColumn, birthdayColumn, departmentColumn);
//
//        teachersTable.setItems(FXCollections.observableArrayList(teachers));
//
//    ScrollPane scrollPane = new ScrollPane(teachersTable);
//        scrollPane.setFitToWidth(true);
//
//    BorderPane viewTeachersPane = new BorderPane();
//        viewTeachersPane.setCenter(scrollPane);
//
//        viewTeachersTab.setContent(viewTeachersPane);
//
//        tabPane.getTabs().add(viewTeachersTab);
//
//
//
//
//
//
//    Tab viewStudentsTab = new Tab("Всі учні");
//
//
//    TableView<Student> studentsTable = new TableView<>();
//        studentsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//
//    TableColumn<Student, String> studentsLastNameColumn = new TableColumn<>("Прізвище");
//        studentsLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentLastName"));
//        studentsLastNameColumn.setMinWidth(100);
//
//    TableColumn<Student, String> studentsFirstNameColumn = new TableColumn<>("Ім'я");
//        studentsFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentFirstName"));
//        studentsFirstNameColumn.setMinWidth(100);
//
//    TableColumn<Student, String> studentsMiddleNameColumn = new TableColumn<>("По-батькові");
//        studentsMiddleNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentMiddleName"));
//        studentsMiddleNameColumn.setMinWidth(100);
//
//    TableColumn<Student, String> dateOfBirthColumn = new TableColumn<>("Дата народження");
//        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));
//        dateOfBirthColumn.setMinWidth(100);
//
//    TableColumn<Student, String> yearOfAdmissionColumn = new TableColumn<>("Рік вступу");
//        yearOfAdmissionColumn.setCellValueFactory(new PropertyValueFactory<>("admission"));
//        yearOfAdmissionColumn.setMinWidth(100);
//
//    TableColumn<Student, String> yearOfGraduationColumn = new TableColumn<>("Рік випуску");
//        yearOfGraduationColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
//        yearOfGraduationColumn.setMinWidth(100);
//
//        studentsTable.getColumns().addAll(
//            studentsLastNameColumn,
//            studentsFirstNameColumn,
//            studentsMiddleNameColumn,
//            dateOfBirthColumn,
//            yearOfAdmissionColumn,
//            yearOfGraduationColumn);
//
//        studentsTable.setItems(FXCollections.observableArrayList(students)); // assuming teachers is a List<Teacher> containing all the teachers
//
//    ScrollPane studentsScrollPane = new ScrollPane(studentsTable);
//        studentsScrollPane.setFitToWidth(true);
//
//    BorderPane viewStudentsPane = new BorderPane();
//        viewStudentsPane.setCenter(studentsScrollPane);
//
//        viewStudentsTab.setContent(viewStudentsPane);
//
//        tabPane.getTabs().add(viewStudentsTab);
//
//
//
//
//
//    Scene scene = new Scene(tabPane, 400, 400);
//        primaryStage.setScene(scene);
//
//        primaryStage.setTitle("Institute");
//        primaryStage.show();
//}
//
//    private void saveStudent(String text, String text1, String text2, String parseInt, int text3, String text4) throws IOException {
//        Student newStudent = new Student(text, text1, text2, parseInt, text3, text4);
//
//        students.add(newStudent);
//
//        SaveService.saveStudents(students);
//    }
//
//
//    private void saveTeacher(String lastName, String firstName, String middleName, String grade, String position) throws IOException {
//        Teacher newTeacher = new Teacher(lastName, firstName, middleName, grade, position);
//
//        teachers.add(newTeacher);
//
//        SaveService.saveTeachers(teachers);
//    }
//}
