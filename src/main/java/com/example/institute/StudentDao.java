package com.example.institute;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentDao {
    public static void addStudents(List<Student> students) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String userDir = System.getProperty("user.home");
        Path studentsFilePath = Paths.get(userDir, "students.json");

        List<Student> existingStudents = new ArrayList<>();
        if (Files.exists(studentsFilePath)){
            existingStudents = mapper.readValue(studentsFilePath.toFile(),
                    new TypeReference<List<Student>>(){});
        }

        for (Student student : students) {
            UUID id = UUID.randomUUID();
            student.setId(String.valueOf(id));
            existingStudents.add(student);
        }


        mapper.writeValue(studentsFilePath.toFile(), existingStudents);
    }

    public List<Student> showStudents() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        String userDir = System.getProperty("user.home");
        Path studentsFilePath = Paths.get(userDir, "students.json");
        File file = new File(studentsFilePath.toUri());

        if (!Files.exists(studentsFilePath)){
            file.createNewFile();
            return new ArrayList<>();
        } else {
            return mapper.readValue(file, new TypeReference<List<Student>>() {});
        }
    }

    public static void deleteStudent(Student student) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        String userDir = System.getProperty("user.home");
        Path studentsFilePath = Paths.get(userDir, "students.json");

        List<Student> students = new ArrayList<>();
        if (Files.exists(studentsFilePath)){
            students = mapper.readValue(studentsFilePath.toFile(),
                    new TypeReference<List<Student>>() {});
        }

        students.remove(student);
        mapper.writeValue(studentsFilePath.toFile(), students);
    }

    public void saveStudentAfterUpdate(Student student) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String userDir = System.getProperty("user.home");
        Path teachersFilePath = Paths.get(userDir, "students.json");
        File file = new File(teachersFilePath.toUri());

        List<Student> students = mapper.readValue(file, new TypeReference<List<Student>>(){});

        int index = -1;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(student.getId())) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            students.set(index, student);
        }

        mapper.writeValue(file, students);
    }
}
