package com.example.institute;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class StudentService {
    private final static String userDir = System.getProperty("user.home");
    private final static Path studentsFilePath = Paths.get(userDir, "students.json");
    private final static ObjectMapper mapper = new ObjectMapper();

    public static void addStudents(List<Student> students) throws IOException {
        List<Student> existingStudents = mapper.readValue(studentsFilePath.toFile(), new TypeReference<>() {
        });

        for (Student student : students) {
            UUID id = UUID.randomUUID();
            student.setId(String.valueOf(id));
            existingStudents.add(student);
        }

        mapper.writeValue(studentsFilePath.toFile(), existingStudents);
    }

    public List<Student> showStudents() throws IOException {
        File file = new File(studentsFilePath.toUri());

        return mapper.readValue(file, new TypeReference<>() {
        });
    }

    public static void deleteStudent(Student student) throws IOException {
        List<Student> students = mapper.readValue(studentsFilePath.toFile(), new TypeReference<>() {
        });

        students.remove(student);
        mapper.writeValue(studentsFilePath.toFile(), students);
    }

    public void updateStudent(Student student) throws IOException {
        File file = new File(studentsFilePath.toUri());

        List<Student> students = mapper.readValue(file, new TypeReference<>() {
        });

        for (Student s : students) {
            if (Objects.equals(s.getId(), student.getId())) {
                s.setFirstName(student.getFirstName());
                s.setLastName(student.getLastName());
                s.setMiddleName(student.getMiddleName());
                s.setDateOfBirth(student.getDateOfBirth());
                s.setYearOfAdmission(student.getYearOfAdmission());
                s.setYearOfGraduation(student.getYearOfGraduation());

                break;
            }
        }

        saveStudents(students);
    }

    public void saveStudents(List<Student> students) throws IOException {
        File file = new File(studentsFilePath.toUri());
        mapper.writeValue(file, students);
    }
}
