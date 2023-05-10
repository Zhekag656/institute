package com.example.institute;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveService {
    private static final String STUDENT_JSON_FILE = "students.json";
    private static final String TEACHER_JSON_FILE = "teachers.json";

    public static void saveStudents(List<Student> students) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(STUDENT_JSON_FILE), students);
    }

    public static List<Student> showStudents() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(STUDENT_JSON_FILE);
        if (!file.exists()){
            return new ArrayList<>();
        }
        return mapper.readValue(file, new TypeReference<List<Student>>() {});
    }

    public static void saveTeachers(List<Teacher> teachers) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(TEACHER_JSON_FILE), teachers);
    }

    public static List<Teacher> showTeachers() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(TEACHER_JSON_FILE);
        if (!file.exists()){
            return new ArrayList<>();
        }
        return mapper.readValue(file, new TypeReference<List<Teacher>>() {});
    }
}
