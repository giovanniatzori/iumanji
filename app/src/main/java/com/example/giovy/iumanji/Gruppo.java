package com.example.giovy.iumanji;

import java.util.ArrayList;

/**
 * Created by martina on 06/02/17.
 */

public class Gruppo extends ArrayList<Persona> {

    private Integer id;
    private String nome;
    //private String immagine;

    public Gruppo (){
        this.nome = "";
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
}

