package com.example.institute;

public class Teacher extends Person{
    private String academicDegree;
    private String position;
    public Teacher(String surname, String firstName, String middleName, String academicDegree, String position, String id) {
        super(surname, firstName, middleName, id);
        this.academicDegree = academicDegree;
        this.position = position;
    }
    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "academicDegree='" + academicDegree + '\'' +
                ", position='" + position + '\'' +
                '}';
    }

    public void saveTeacher(){
        return;
    }
}
