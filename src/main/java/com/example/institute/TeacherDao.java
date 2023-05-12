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

public class TeacherDao {
    public static void addTeachers(List<Teacher> teachers) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        String userDir = System.getProperty("user.home");
        Path teachersFilePath = Paths.get(userDir, "teachers.json");

        List<Teacher> existingTeachers = new ArrayList<>();
        if (Files.exists(teachersFilePath)){
            existingTeachers = mapper.readValue(teachersFilePath.toFile(),
                    new TypeReference<List<Teacher>>() {});
        }

        for (Teacher teacher : teachers){
            UUID id = UUID.randomUUID();
            teacher.setId(String.valueOf(id));
            existingTeachers.add(teacher);
        }

        mapper.writeValue(teachersFilePath.toFile(), existingTeachers);
    }

    public List<Teacher> showTeachers() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        String userDir = System.getProperty("user.home");
        Path teachersFilePath = Paths.get(userDir, "teachers.json");
        File file = new File(teachersFilePath.toUri());

        if (!Files.exists(teachersFilePath)){
            file.createNewFile();
            return new ArrayList<>();
        } else {
            return mapper.readValue(file, new TypeReference<List<Teacher>>() {});
        }
    }
}
