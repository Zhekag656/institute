package com.example.institute;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.UUID;

public class Student extends Person{
    @SerializedName("birthday")
    private String dateOfBirth;
    @SerializedName("admission_name")
    private String yearOfAdmission;
    @SerializedName("graduation_year")
    private String yearOfGraduation;

    @JsonCreator
    public Student(@JsonProperty("id") UUID id,
                   @JsonProperty("last_name") String surname,
                   @JsonProperty("first_name") String firstName,
                   @JsonProperty("middle_name") String middleName,
                   @JsonProperty("birthday") String dateOfBirth,
                   @JsonProperty("admission_year") String yearOfAdmission,
                   @JsonProperty("graduation_year") String yearOfGraduation) {
        super(String.valueOf(id), surname, firstName, middleName);
        this.dateOfBirth = dateOfBirth;
        this.yearOfAdmission = yearOfAdmission;
        this.yearOfGraduation = yearOfGraduation;
    }

    @JsonProperty("birthday")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @JsonProperty("admission_year")
    public String getYearOfAdmission() {
        return yearOfAdmission;
    }

    public void setYearOfAdmission(String yearOfAdmission) {
        this.yearOfAdmission = yearOfAdmission;
    }

    @JsonProperty("graduation_year")
    public String getYearOfGraduation() {
        return yearOfGraduation;
    }

    public void setYearOfGraduation(String yearOfGraduation) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(dateOfBirth, student.dateOfBirth) && Objects.equals(yearOfAdmission, student.yearOfAdmission) && Objects.equals(yearOfGraduation, student.yearOfGraduation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfBirth, yearOfAdmission, yearOfGraduation);
    }
}
