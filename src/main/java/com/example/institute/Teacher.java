package com.example.institute;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.UUID;

public class Teacher extends Person{
    @SerializedName("degree")
    private String academicDegree;
    @SerializedName("position")
    private String position;
    public Teacher(@JsonProperty("id") UUID id,
                   @JsonProperty("last_name") String surname,
                   @JsonProperty("first_name") String firstName,
                   @JsonProperty("middle_name") String middleName,
                   @JsonProperty("degree") String academicDegree,
                   @JsonProperty("position") String position) {
        super(String.valueOf(id), surname, firstName, middleName);
        this.academicDegree = academicDegree;
        this.position = position;
    }
    @JsonProperty("degree")
    public String getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }

    @JsonProperty("position")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(getId(), teacher.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(academicDegree, position);
    }
}
