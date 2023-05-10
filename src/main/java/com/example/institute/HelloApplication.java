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


        primaryStage.setTitle("Anket App");
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label titleLabel = new Label("Anket App");
        titleLabel.setStyle("-fx-font-size: 24pt; -fx-font-weight: bold;");
        GridPane.setConstraints(titleLabel, 0, 0, 4, 1);

        Label peopleLabel = new Label("People");
        peopleLabel.setStyle("-fx-font-weight: bold;");
        GridPane.setConstraints(peopleLabel, 0, 1);
        GridPane.setConstraints(listView, 0, 2, 4, 1);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addPerson());
        GridPane.setConstraints(addButton, 0, 3);

        Button editButton = new Button("Edit");
        editButton.setOnAction(e -> editPerson());
        GridPane.setConstraints(editButton, 1, 3);

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deletePerson());
        GridPane.setConstraints(deleteButton, 2, 3);

        Label roleLabel = new Label("Role");
        GridPane.setConstraints(roleLabel, 0, 7);
        positionBox.setItems(FXCollections.observableArrayList("Student", "Teacher"));
        GridPane.setConstraints(positionBox, 1, 7, 3, 1);

        Label lastNameLabel = new Label("Last name");
        GridPane.setConstraints(lastNameLabel, 0, 4);
        GridPane.setConstraints(lastNameField, 1, 4, 3, 1);

        Label firstNameLabel = new Label("First name");
        GridPane.setConstraints(firstNameLabel, 0, 5);
        GridPane.setConstraints(firstNameField, 1, 5, 3, 1);

        Label middleNameLabel = new Label("Middle name");
        GridPane.setConstraints(middleNameLabel, 0, 6);
        GridPane.setConstraints(middleNameField, 1, 6, 3, 1);

        Label positionLabel = new Label("Position");
        GridPane.setConstraints(positionLabel, 0, 7);
        positionBox.setItems(FXCollections.observableArrayList("Professor", "Associate Professor", "Assistant Professor", "Lecturer"));
        GridPane.setConstraints(positionBox, 1, 7, 3, 1);

        Label rankLabel = new Label("Rank");
        GridPane.setConstraints(rankLabel, 0, 8);
        GridPane.setConstraints(rankField, 1, 8, 3, 1);

        Label birthdateLabel = new Label("Birthdate (dd.MM.yyyy)");
        GridPane.setConstraints(birthdateLabel, 0, 9);
        GridPane.setConstraints(birthdateField, 1, 9, 3, 1);

        Label admissionYearLabel = new Label("Admission year");
        GridPane.setConstraints(admissionYearLabel, 0, 10);
        GridPane.setConstraints(admissionYearField, 1, 10, 3, 1);

        Label graduationYearLabel = new Label("Graduation year");
        GridPane.setConstraints(graduationYearLabel, 0, 11);
        GridPane.setConstraints(graduationYearField, 1, 11, 3, 1);

        gridPane.getChildren().addAll(titleLabel, peopleLabel, listView, addButton, editButton, deleteButton,
                lastNameLabel, lastNameField, firstNameLabel, firstNameField, middleNameLabel, middleNameField,
                positionLabel, positionBox, rankLabel, rankField, birthdateLabel, birthdateField,
                admissionYearLabel, admissionYearField, graduationYearLabel, graduationYearField);

        Scene scene = new Scene(gridPane, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addPerson() {
        String lastName = lastNameField.getText().trim();
        String firstName = firstNameField.getText().trim();
        String middleName = middleNameField.getText().trim();
        String position = positionBox.getValue();
        String rank = rankField.getText().trim();
        LocalDate birthdate = LocalDate.parse(birthdateField.getText().trim(), dateFormatter);
        int admissionYear = Integer.parseInt(admissionYearField.getText().trim());
        int graduationYear = Integer.parseInt(graduationYearField.getText().trim());

        Person person = new Person(lastName, firstName, middleName);
        people.add(person);
        saveData();

        clearFields();
    }

    private void editPerson() {
        Person selectedPerson = listView.getSelectionModel().getSelectedItem();
        if (selectedPerson == null) {
            return;
        }

        String lastName = lastNameField.getText().trim();
        String firstName = firstNameField.getText().trim();
        String middleName = middleNameField.getText().trim();
        String position = positionBox.getValue();
        String rank = rankField.getText().trim();
        LocalDate birthdate = LocalDate.parse(birthdateField.getText().trim(), dateFormatter);
        int admissionYear = Integer.parseInt(admissionYearField.getText().trim());
        int graduationYear = Integer.parseInt(graduationYearField.getText().trim());

//        selectedPerson.setSurname(lastName);
//        selectedPerson.setFirstName(firstName);
//        selectedPerson.setMiddleName(middleName);
//        selectedPerson.setPosition(position);
//        selectedPerson.setRank(rank);
//        selectedPerson.setBirthdate(birthdate);
//        selectedPerson.setAdmissionYear(admissionYear);
//        selectedPerson.setGraduationYear(graduationYear);

        listView.refresh();
        saveData();
        clearFields();
    }

    private void saveData() {
        try {
            String json = gson.toJson(people);
            Files.writeString(Paths.get(FILE_NAME), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        lastNameField.setText("");
        firstNameField.setText("");
        middleNameField.setText("");
        rankField.setText("");
        positionBox.getSelectionModel().clearSelection();
        birthdateField.setText("");
        admissionYearField.setText("");
        graduationYearField.setText("");
    }

    private void deletePerson() {
        Person selectedPerson = listView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            people.remove(selectedPerson);
            saveData();
        }
    }
}