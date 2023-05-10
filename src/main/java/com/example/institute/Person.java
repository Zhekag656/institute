package com.example.institute;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Person {
    private String lastName;
    private String firstName;
    private String middleName;

    @Override
    public String toString() {
        return "Person{" +
                "surname='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                '}';
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("middle_name")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Person(String lastName, String firstName, String middleName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }
}
