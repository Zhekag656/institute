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

public class StudentController {

    public static void saveStudents(List<Student> students) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String userDir = System.getProperty("user.home");
        Path studentsFilePath = Paths.get(userDir, "students.json");

        List<Student> existingStudents = new ArrayList<>();
        if (Files.exists(studentsFilePath)){
            existingStudents = mapper.readValue(studentsFilePath.toFile(),
                new TypeReference<List<Student>>(){});
        }

        existingStudents.addAll(students);

        mapper.writeValue(studentsFilePath.toFile(), existingStudents);
    }


}
