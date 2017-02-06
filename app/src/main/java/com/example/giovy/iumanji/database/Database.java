package com.example.giovy.iumanji.database;

import com.example.giovy.iumanji.Gruppo;
import com.example.giovy.iumanji.Persona;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by martina on 06/02/17.
 */

/*Gestire un mini database era complicato, questa classe si occupa di gestire i dati salvati su file txt*/
public class Database {

    //c'è una sola istanza del database
    private static Database singleton;
    public static Database getInstance() {
        if (singleton == null) {
            singleton = new Database();
        }
        return singleton;
    }

    private ArrayList<Persona> utenti;
    private ArrayList<Gruppo> gruppi;

    public Database popolaDatabase () {

        File f = new File ("res/Utenti");
        try(Scanner scanner = new Scanner(f).useDelimiter(" ")){

            ArrayList<Persona> a = new ArrayList<Persona>();

            while (scanner.hasNext()){
                Persona p = new Persona();
                p.setId(Integer.parseInt (scanner.next()));
                p.setNome(scanner.next());
                p.setCognome(scanner.next());
                p.setEmail(scanner.next());
                p.setPassword(scanner.next());
                a.add(p);
            }

            this.setUtenti(a);
            scanner.close();
        }
        catch (FileNotFoundException e){
            System.out.println("notfound");
        }

        f = new File("res/Gruppi");

        try(Scanner scanner = new Scanner(f).useDelimiter(" ")){

            ArrayList<Gruppo> a = new ArrayList<Gruppo>();

            while (scanner.hasNext()){
                Gruppo g = new Gruppo();
                g.setId(scanner.nextInt());
                g.setNome(scanner.next());
                a.add(g);
            }

            this.setGruppi(a);
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("notfound");
        }

        return singleton;

    }

    /**
     * @return the gruppi
     */
    public ArrayList<Gruppo> getGruppi() {
        return gruppi;
    }

    /**
     * @return the utenti
     */
    public ArrayList<Persona> getUtenti() {
        return utenti;
    }

    /**
     * @param gruppi the gruppi to set
     */
    public void setGruppi(ArrayList<Gruppo> gruppi) {
        this.gruppi = gruppi;
    }

    /**
     * @param utenti the utenti to set
     */
    public void setUtenti(ArrayList<Persona> utenti) {
        this.utenti = utenti;
    }
}
