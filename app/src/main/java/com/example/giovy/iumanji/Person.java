package com.example.giovy.iumanji;

import java.io.Serializable;
import java.util.List;

/**
 * Created by giovy on 27/01/2017.
 */

public class Person implements Serializable {

    private String name;
    private String surname;
    private String email;
    //private List<Gruppo> gruppi;


    public Person(){
        this.setName("");
        this.setSurname("");
        this.setEmail("");
    }

    public Person(String name, String surname, String email) {
        this.setName(name);
        this.setSurname(surname);
        this.setEmail(email);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;}

    public String getEmail(){ return email; }

    public void setEmail(String email) {this.email=email;}
    }
