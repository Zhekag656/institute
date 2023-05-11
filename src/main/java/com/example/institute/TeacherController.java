package com.example.institute;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TeacherController {
    public static void saveTeachers (List<Teacher> teachers) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        String userDir = System.getProperty("user.home");
        Path teachersFilePath = Paths.get(userDir, "teachers.json");

        List<Teacher> existingTeachers = new ArrayList<>();
        if (Files.exists(teachersFilePath)){
            existingTeachers = mapper.readValue(teachersFilePath.toFile(),
                    new TypeReference<List<Teacher>>() {});
        }
        existingTeachers.addAll(teachers);

        mapper.writeValue(teachersFilePath.toFile(), existingTeachers);
    }
}
