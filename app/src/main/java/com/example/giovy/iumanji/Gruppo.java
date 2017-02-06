package com.example.giovy.iumanji;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martina on 06/02/17.
 */

public class Gruppo extends ArrayList<Person> {

    private String nome;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

