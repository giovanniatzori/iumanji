package com.example.giovy.iumanji.database;

import java.io.File;
import java.io.FileNotFoundException;
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
    private HashMap<Integer, Pietanza> pietanze = new HashMap<Integer, Pietanza>();
    private HashMap<Integer, Locale> locali = new HashMap<Integer, Locale>();

    /*aggiorna il database con i dati salvati nei txt, occhio a modificare i file perché
    basta un carattere fuori posto e tutto crasha*/
    public Database aggiornaDatabase () {

        //PERSONE

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
                p.setImmagine(scanner.next());
                a.put(p.getId(), p);
            }

            this.setPersone(a);
            scanner.close();
        }
        catch (FileNotFoundException e){
            System.out.println("notfound");
        }

        //PIETANZE

        f = new File ("res/Pietanze");

        try(Scanner scanner = new Scanner(f).useDelimiter("'")){

            HashMap<Integer, Pietanza> a = new HashMap<Integer, Pietanza>();

            while (scanner.hasNext()){
                Pietanza p = new Pietanza();
                scanner.next();
                p.setId(scanner.nextInt());
                p.setNome(scanner.next());
                p.setPrezzo(Double.parseDouble(scanner.next()));
                a.put(p.getId(), p);
            }

            this.setPietanze(a);
            scanner.close();
        }
        catch (FileNotFoundException e){
            System.out.println("notfound");
        }

        //LOCALI

        f = new File ("res/Locali");

        try(Scanner scanner = new Scanner(f).useDelimiter("'")){

            HashMap<Integer, Locale> a = new HashMap<Integer, Locale>();

            while (scanner.hasNext()){
                Locale l = new Locale();
                scanner.next();
                l.setId(scanner.nextInt());
                l.setNome(scanner.next());
                l.setImmagine(scanner.next());

                Scanner s = new Scanner(scanner.next()).useDelimiter("-");
                //dati pietanze associate al locale
                while(s.hasNext()){
                    Pietanza p = (this.getPietanze()).get(s.nextInt());
                    (l.getPietanze()).add(p);//collego la pietanza al locale
                }

                s.close();
                a.put(l.getId(), l);
            }

            this.setLocali(a);
            scanner.close();
        }
        catch (FileNotFoundException e){
            System.out.println("notfound");
        }

        //GRUPPI E COLLEGAMENTO CON UTENTI E LOCALI

        f = new File("res/Gruppi");

        try(Scanner scanner = new Scanner(f).useDelimiter("'")){

            HashMap<Integer, Gruppo> a = new HashMap<Integer, Gruppo>();
            //dati gruppo
            while (scanner.hasNext()){
                Gruppo g = new Gruppo();
                scanner.next();
                g.setId(scanner.nextInt());
                g.setNome(scanner.next());
                g.setImmagine(scanner.next());

                Scanner s = new Scanner(scanner.next()).useDelimiter("-");
                //dati componenti del gruppo
                while(s.hasNext()){
                    Persona p = (this.getPersone()).get(s.nextInt());
                    g.add(p);//collego la persona col gruppo
                    (p.getGruppi()).add(g);//collego il gruppo alla persona
                }

                s.close();

                s = new Scanner(scanner.next()).useDelimiter("-");
                //dati locali del gruppo
                while(s.hasNext()){
                    Locale l = (this.getLocali()).get(s.nextInt());
                    (g.getLocali()).add(l);//collego il locale al gruppo
                }

                s.close();
                a.put(g.getId(), g);
            }

            this.setGruppi(a);
            scanner.close();

        } catch (FileNotFoundException e) {
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

    public HashMap<Integer, Pietanza> getPietanze() {
        return pietanze;
    }

    public void setPietanze(HashMap<Integer, Pietanza> pietanze) {
        this.pietanze = pietanze;
    }

    public HashMap<Integer, Locale> getLocali() {
        return locali;
    }

    public void setLocali(HashMap<Integer, Locale> locali) {
        this.locali = locali;
    }
}
