package com.example.giovy.iumanji.database;

import java.io.Serializable;

/**
 * Created by martina on 07/02/17.
 */

public class Pietanza implements Serializable {

    private Integer id;
    private String nome;
    private Double prezzo;
    private Integer quantita=0;
    private Double totale;

    public Pietanza(String nome, double prezzo){
        this.nome = nome;
        this.prezzo = prezzo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the prezzo
     */
    public Double getPrezzo() {
        return prezzo;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param prezzo the prezzo to set
     */
    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
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

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public Double getTotale() {
        return totale;
    }

    public void setTotale(Double totale) {
        this.totale = totale;
    }
}
