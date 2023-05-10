package com.example.institute;

import java.time.LocalDate;

public class Student extends Person{
    private String dateOfBirth;
    private int yearOfAdmission;
    private int yearOfGraduation;

    public Student(String surname, String firstName, String middleName, String dateOfBirth, int yearOfAdmission, int yearOfGraduation) {
        super(surname, firstName, middleName);
        this.dateOfBirth = dateOfBirth;
        this.yearOfAdmission = yearOfAdmission;
        this.yearOfGraduation = yearOfGraduation;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getYearOfAdmission() {
        return yearOfAdmission;
    }

    public void setYearOfAdmission(int yearOfAdmission) {
        this.yearOfAdmission = yearOfAdmission;
    }

    public int getYearOfGraduation() {
        return yearOfGraduation;
    }

    public void setYearOfGraduation(int yearOfGraduation) {
        this.yearOfGraduation = yearOfGraduation;
    }

    @Override
    public String toString() {
        return "Student{" +
                "dateOfBirth='" + dateOfBirth + '\'' +
                ", yearOfAdmission=" + yearOfAdmission +
                ", yearOfGraduation=" + yearOfGraduation +
                '}';
    }
}
