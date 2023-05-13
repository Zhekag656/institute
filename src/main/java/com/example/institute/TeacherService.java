package com.example.institute;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TeacherService {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String userDir = System.getProperty("user.home");
    private static final Path teachersFilePath = Paths.get(userDir, "teachers.json");

    public static void addTeachers(List<Teacher> teachers) throws IOException {

        List<Teacher> existingTeachers = mapper.readValue(teachersFilePath.toFile(),
                new TypeReference<>() {
                });

        for (Teacher teacher : teachers) {
            UUID id = UUID.randomUUID();
            teacher.setId(String.valueOf(id));
            existingTeachers.add(teacher);
        }

        mapper.writeValue(teachersFilePath.toFile(), existingTeachers);
    }

    public List<Teacher> showTeachers() throws IOException{
        File file = new File(teachersFilePath.toUri());

        return mapper.readValue(file, new TypeReference<>() {
        });
    }

    public static void deleteTeacher(Teacher teacher) throws IOException{
        List<Teacher> teachers = mapper.readValue(teachersFilePath.toFile(),
                new TypeReference<>() {
                });

        teachers.remove(teacher);
        mapper.writeValue(teachersFilePath.toFile(), teachers);
    }

    public void saveTeacherAfterUpdate(Teacher teacher) throws IOException {
        File file = new File(teachersFilePath.toUri());

        List<Teacher> teachers = mapper.readValue(file, new TypeReference<>() {
        });

        for (Teacher t : teachers) {
            if (Objects.equals(t.getId(), teacher.getId())) {
                t.setFirstName(teacher.getFirstName());
                t.setLastName(teacher.getLastName());
                t.setMiddleName(teacher.getMiddleName());
                t.setPosition(teacher.getPosition());
                t.setAcademicDegree(teacher.getAcademicDegree());

                break;
            }
        }

        mapper.writeValue(file, teachers);
    }

    public List<Teacher> getAllTeachers() throws IOException {
        File file = new File(teachersFilePath.toUri());
        return Arrays.asList(mapper.readValue(file, Teacher[].class));
    }

    public void showAcademicTeachers() {
        TableView<Teacher> academicTable = new TableView<>();
        TableColumn<Teacher, String> lastNameCol = new TableColumn<>("Прізвище");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<Teacher, String> firstNameCol = new TableColumn<>("Ім'я");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<Teacher, String> middleNameCol = new TableColumn<>("По-батькові");
        middleNameCol.setCellValueFactory(new PropertyValueFactory<>("middleName"));
        TableColumn<Teacher, String> degreeCol = new TableColumn<>("Вчений ступінь");
        degreeCol.setCellValueFactory(new PropertyValueFactory<>("academicDegree"));
        TableColumn<Teacher, String> positionCol = new TableColumn<>("Посада");
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));

        academicTable.getColumns().addAll(lastNameCol, firstNameCol, middleNameCol, degreeCol, positionCol);


        List<Teacher> teachers = new ArrayList<>();
        try {
            teachers = getAllTeachers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObservableList<Teacher> academicTeachers = FXCollections.observableArrayList();
        for (Teacher teacher : teachers) {
            String position = teacher.getPosition();
            if (position.equals("Доцент") || position.equals("Професор")) {
                academicTeachers.add(teacher);
            }
        }
        academicTable.setItems(academicTeachers);

        Stage academicStage = new Stage();
        academicStage.setTitle("Доценти і професори");
        Scene academicScene = new Scene(academicTable, 900, 800);
        academicStage.setScene(academicScene);
        academicStage.show();

    }
}
