package com.example.giovy.iumanji.database;

import java.util.ArrayList;

/**
 * Created by martina on 07/02/17.
 */

public class Locale {

    private Integer id;
    private String nome;
    private String immagine;
    private ArrayList<Pietanza> pietanze = new ArrayList<Pietanza>();

    public Locale(){
        this.nome = "Nome Gruppo";
        this.immagine= "";
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the pietanze
     */
    public ArrayList<Pietanza> getPietanze() {
        return pietanze;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param pietanze the pietanze to set
     */
    public void setPietanze(ArrayList<Pietanza> pietanze) {
        this.pietanze = pietanze;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the immagine
     */
    public String getImmagine() {
        return immagine;
    }

    /**
     * @param immagine the immagine to set
     */
    public void setImmagine(String immagine) {
        this.immagine = immagine;
    }

}
