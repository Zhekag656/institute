package com.example.institute;

public class Person {
    private String surname;
    private String firstName;
    private String middleName;

    @Override
    public String toString() {
        return "Person{" +
                "surname='" + surname + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                '}';
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Person(String surname, String firstName, String middleName) {
        this.surname = surname;
        this.firstName = firstName;
        this.middleName = middleName;
    }
}
