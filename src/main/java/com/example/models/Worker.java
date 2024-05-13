package com.example.models;

public class Worker extends Person {
    private Person person;
    private Boolean isInternal;


    public Worker(String personId, String firstName, String lastName, String telephoneNumber, String email, String pesel, Boolean isInternal) {
        super(personId, firstName, lastName, telephoneNumber, email, pesel);
        this.isInternal = isInternal;
    }

    public Boolean getInternal() {
        return isInternal;
    }

    public void setInternal(Boolean internal) {
        isInternal = internal;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
