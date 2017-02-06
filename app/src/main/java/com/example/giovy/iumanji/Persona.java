package com.example.giovy.iumanji;

import java.io.Serializable;
import java.util.List;

/**
 * Created by giovy on 27/01/2017.
 */

public class Persona implements Serializable {

    private Integer id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    //private List<Gruppo> gruppi;


    public Persona(){}

    public Persona(String nome, String cognome, String email, String password) {
        this.setNome(nome);
        this.setCognome(cognome);
        this.setEmail(email);
        this.setPassword(password);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;}

    public String getEmail(){ return email; }

    public void setEmail(String email) {this.email=email;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
