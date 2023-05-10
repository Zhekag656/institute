package com.example.institute;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class JsonUtil {
    private static final String STUDENTS_JSON_FILE_PATH = "students.json";

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static List<Student> readStudentsFromJson() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(STUDENTS_JSON_FILE_PATH)));
        Student[] students = gson.fromJson(content, Student[].class);
        return Arrays.asList(students);
    }

    public static void saveStudentsToJson(List<Student> students) throws IOException{
        String json = gson.toJson(students);
        Files.write(Paths.get(STUDENTS_JSON_FILE_PATH), json.getBytes());
    }
}
