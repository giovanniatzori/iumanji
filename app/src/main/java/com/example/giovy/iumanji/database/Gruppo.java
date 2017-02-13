package com.example.giovy.iumanji.database;

import java.util.ArrayList;

/**
 * Created by martina on 06/02/17.
 */

public class Gruppo extends ArrayList<Persona> {

    private Integer id;
    private String nome;
    private String immagine;
    //private ArrayList<Locale> locali = new ArrayList<Locale>();

    public Gruppo (String nome){
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImmagine() {
        return immagine;
    }

    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

    /*public ArrayList<Locale> getLocali() {
        return locali;
    }

    public void setLocali(ArrayList<Locale> locali) {
        this.locali = locali;
    }*/
}

