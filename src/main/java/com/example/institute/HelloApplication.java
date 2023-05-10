package com.example.institute;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelloApplication extends Application {

    private static final String FILE_NAME = "data.json";

    private ObservableList<Person> people = FXCollections.observableArrayList();
    private ListView<Person> listView = new ListView<>(people);

    private TextField lastNameField = new TextField();
    private TextField firstNameField = new TextField();
    private TextField middleNameField = new TextField();
    private TextField rankField = new TextField();
    private ChoiceBox<String> positionBox = new ChoiceBox<>();
    private TextField birthdateField = new TextField();
    private TextField admissionYearField = new TextField();
    private TextField graduationYearField = new TextField();

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public void start(Stage primaryStage) {


        primaryStage.setTitle("Institute App");
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label titleLabel = new Label("Institute App");
        titleLabel.setStyle("-fx-font-size: 24pt; -fx-font-weight: bold;");
        GridPane.setConstraints(titleLabel, 0, 0, 4, 1);

        Label peopleLabel = new Label("People");
        peopleLabel.setStyle("-fx-font-weight: bold;");
        GridPane.setConstraints(peopleLabel, 0, 1);
        GridPane.setConstraints(listView, 0, 2, 4, 1);

        Button addButton = new Button("Add");
        //addButton.setOnAction(e -> addPerson());
        GridPane.setConstraints(addButton, 0, 3);

        Button editButton = new Button("Edit");
        //editButton.setOnAction(e -> editPerson());
        GridPane.setConstraints(editButton, 1, 3);

        Button deleteButton = new Button("Delete");
        //deleteButton.setOnAction(e -> deletePerson());
        GridPane.setConstraints(deleteButton, 2, 3);

        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.prefWidthProperty().bind(primaryStage.widthProperty());

        Tab teacherTab = new Tab("Add Teacher");
        teacherTab.setClosable(false);
        GridPane teacherPane = new GridPane();
        teacherPane.setPadding(new Insets(10, 10, 10, 10));
        teacherPane.setHgap(10);
        teacherPane.setVgap(10);

        Label lastNameLabel1 = new Label("Last name");
        GridPane.setConstraints(lastNameLabel1, 0, 0);
        TextField lastNameField1 = new TextField();
        GridPane.setConstraints(lastNameField1, 1, 0);

        Label firstNameLabel1 = new Label("First name");
        GridPane.setConstraints(firstNameLabel1, 0, 1);
        TextField firstNameField1 = new TextField();
        GridPane.setConstraints(firstNameField1, 1, 1);

        Label middleNameLabel1 = new Label("Middle name");
        GridPane.setConstraints(middleNameLabel1, 0, 2);
        TextField middleNameField1 = new TextField();
        GridPane.setConstraints(middleNameField1, 1, 2);

        Label birthdayLabel1 = new Label("Birthday (yyyy-mm-dd)");
        GridPane.setConstraints(birthdayLabel1, 0, 3);
        TextField birthdayField1 = new TextField();
        GridPane.setConstraints(birthdayField1, 1, 3);

        Label departmentLabel = new Label("Department");
        GridPane.setConstraints(departmentLabel, 0, 4);
        ChoiceBox<String> departmentChoiceBox = new ChoiceBox<>();
        departmentChoiceBox.getItems().addAll("Math", "Physics", "Chemistry", "Biology", "History", "Geography");
        GridPane.setConstraints(departmentChoiceBox, 1, 4);

        Label salaryLabel = new Label("Salary");
        GridPane.setConstraints(salaryLabel, 0, 5);
        TextField salaryField = new TextField();
        GridPane.setConstraints(salaryField, 1, 5);

        Button saveButton1 = new Button("Save");
        saveButton1.setOnAction(e -> saveTeacher(lastNameField1.getText(), firstNameField1.getText(), middleNameField1.getText(),
                birthdayField1.getText(), departmentChoiceBox.getValue(), Double.parseDouble(salaryField.getText())));
        GridPane.setConstraints(saveButton1, 0, 6);

        teacherPane.getChildren().addAll(lastNameLabel1, lastNameField1, firstNameLabel1, firstNameField1, middleNameLabel1, middleNameField1,
                birthdayLabel1, birthdayField1, departmentLabel, departmentChoiceBox, salaryLabel, salaryField, saveButton1);

        teacherTab.setContent(teacherPane);
        tabPane.getTabs().add(teacherTab);
    }

    private void saveTeacher(String text, String text1, String text2, String text3, String value, double parseDouble) {
    }

}