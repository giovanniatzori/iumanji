package com.example.giovy.iumanji.database;

import com.example.giovy.iumanji.Gruppo;
import com.example.giovy.iumanji.Persona;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
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
    private HashMap<Integer, Persona> persone = new HashMap<Integer, Persona>();
    private HashMap<Integer, Gruppo> gruppi = new HashMap<Integer, Gruppo>();

    //aggiorna il database con i dati salvati nei txt
    public Database aggiornaDatabase () {

        File f = new File ("res/Utenti");

        try(Scanner scanner = new Scanner(f).useDelimiter("'")){

            HashMap<Integer, Persona> a = new HashMap<Integer, Persona>();

            while (scanner.hasNext()){
                Persona p = new Persona();
                scanner.next();
                p.setId(scanner.nextInt());
                p.setNome(scanner.next());
                p.setCognome(scanner.next());
                p.setEmail(scanner.next());
                p.setPassword(scanner.next());
                a.put(p.getId(), p);
            }

            this.setPersone(a);
            scanner.close();
        }
        catch (FileNotFoundException e){
            System.out.println("notfound");
        }

        f = new File("res/Gruppi");

        try(Scanner scanner = new Scanner(f).useDelimiter("'")){

            HashMap<Integer, Gruppo> a = new HashMap<Integer, Gruppo>();

            while (scanner.hasNext()){
                Gruppo g = new Gruppo();
                scanner.next();
                g.setId(scanner.nextInt());
                g.setNome(scanner.next());
                a.put(g.getId(), g);
            }

            this.setGruppi(a);
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("notfound");
        }



        f = new File ("res/Partecipa");

        try(Scanner scanner = new Scanner(f).useDelimiter("'")){

            while(scanner.hasNext()){
                scanner.next();
                Gruppo g = (this.getGruppi()).get(scanner.nextInt());

                Scanner s = new Scanner(scanner.next()).useDelimiter("-");

                while(s.hasNext()){
                    Persona p = (this.getPersone()).get(s.nextInt());
                    g.add(p);
                    (p.getGruppi()).add(g);
                    this.getPersone().put(p.getId(), p);
                }

                s.close();
                this.getGruppi().put(g.getId(), g);
            }

            scanner.close();

        } catch (FileNotFoundException e){
            System.out.println("notfound");
        }


        return singleton;

    }

    /**
     * @return the persone
     */
    public HashMap<Integer, Persona> getPersone() {
        return persone;
    }

    /**
     * @param gruppi the gruppi to set
     */
    public void setGruppi(HashMap<Integer, Gruppo> gruppi) {
        this.gruppi = gruppi;
    }

    /**
     * @param persone the persone to set
     */
    public void setPersone(HashMap<Integer, Persona> persone) {
        this.persone = persone;
    }

    /**
     * @return the gruppi
     */
    public HashMap<Integer, Gruppo> getGruppi() {
        return gruppi;
    }

}
