package com.example.giovy.iumanji.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by alessandrotola on 09/02/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "iumangiDb.db";
    private static final int DATABASE_VERSION = 1;

    //Nome tabelle
    private static final String TABLE_GRUPPO = "gruppo";
    private static final String TABLE_LOCALE = "locale";
    private static final String TABLE_PERSONA = "persona";
    private static final String TABLE_PIETANZA = "pietanza";
    private static final String TABLE_GRUPPOLOCALI = "gruppoLocali";
    private static final String TABLE_GRUPPOMEMBRI = "gruppoMembri";
    private static final String TABLE_LOCALEPIETANZA = "localePietanze";


    //Colonne gruppo
    public static final String ID_GRUPPO = "_idGruppo";
    public static final String NOME_GRUPPO = "nomeGruppo";
    public static final String IMMAGINE_GRUPPO = "immagineGruppo";

    //Colonne locale
    public static final String ID_LOCALE = "_idLocale";
    public static final String NOME_LOCALE = "nomeLocale";
    public static final String IMMAGINE_LOCALE = "immagineLocale";

    //Colonne persona
    public static final String ID_PERSONA = "_idPersona";
    public static final String NOME_PERSONA = "_nomePersona";
    public static final String COGNOME_PERSONA = "_cognomePersona";
    public static final String EMAIL_PERSONA = "_emailPersona";
    public static final String PASSWORD_PERSONA = "_passwordPersona";
    public static final String IMMAGINE_PERSONA = "immaginePersona";

    //Colonne pietanza
    public static final String ID_PIETANZA = "_idPietanza";
    public static final String NOME_PIETANZA = "nomePietanza";
    public static final String PREZZO_PIETANZA = "prezzoPietanza";

    //Colonne gruppoLocali
    public static final String GRUPPO_ID1 = "gruppo";
    public static final String LOCALE_ID1 = "locale";

    //Colonne gruppoMembri
    public static final String GRUPPO_ID2 = "gruppo";
    public static final String PERSONA_ID = "locale";

    //Colonne localiPietanze
    public static final String LOCALE_ID2 = "gruppo";
    public static final String PIETANZA_ID = "locale";

    //Crazione tabella gruppo
    private static final String CREATE_TABLE_GRUPPO =
            "CREATE TABLE " + TABLE_GRUPPO +
                    "(" + ID_GRUPPO + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NOME_GRUPPO + " TEXT NOT NULL," +
                    IMMAGINE_GRUPPO + " TEXT,"  + ")";

    //Crazione tabella locale
    private static final String CREATE_TABLE_LOCALE =
            "CREATE TABLE " + TABLE_LOCALE +
                    "(" + ID_LOCALE + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NOME_LOCALE + " TEXT NOT NULL," +
                    IMMAGINE_LOCALE + " TEXT,"  + ")";

    //Crazione tabella pietanza
    private static final String CREATE_TABLE_PIETANZA =
            "CREATE TABLE " + TABLE_PIETANZA +
                    "(" + ID_PIETANZA + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NOME_PIETANZA + " TEXT NOT NULL," +
                    PREZZO_PIETANZA + " TEXT,"  + ")";

    //Crazione tabella persona
    private static final String CREATE_TABLE_PERSONA =
            "CREATE TABLE " + TABLE_PERSONA +
                    "(" + ID_PERSONA + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NOME_PERSONA + " TEXT NOT NULL," +
                    COGNOME_PERSONA + " TEXT NOT NULL,"  +
                    EMAIL_PERSONA + "TEXT NOT NULL,"  +
                    PASSWORD_PERSONA +"TEXT NOT NULL,"  +
                    IMMAGINE_PERSONA + "TEXT" + ")";

    //Creazione tabella groppoLocali
    private  static final String CREATE_TABLE_GRUPPOLOCALI =
            "CREATE TABLE " + TABLE_GRUPPOLOCALI +
                    "(" + GRUPPO_ID1 + " INTEGER," +
                    LOCALE_ID1 + "INTEGER," +
                    "FOREIGN KEY (" + GRUPPO_ID1 + ") REFERENCES (" + ID_GRUPPO + ")," +
                    "FOREIGN KEY (" + LOCALE_ID1 + ") REFERENCES (" + ID_LOCALE + ")," +")";

    //Creazione tabella groppoMembri
    private  static final String CREATE_TABLE_GRUPPOMEMBRI =
            "CREATE TABLE " + TABLE_GRUPPOMEMBRI +
                    "(" + GRUPPO_ID2 + " INTEGER," +
                    PERSONA_ID + "INTEGER," +
                    "FOREIGN KEY (" + GRUPPO_ID2 + ") REFERENCES (" + ID_GRUPPO + ")," +
                    "FOREIGN KEY (" + PERSONA_ID + ") REFERENCES (" + PERSONA_ID + ")," +")";

    //Creazione tabella localePietanza
    private  static final String CREATE_TABLE_LOCALEPIETANZE =
            "CREATE TABLE " + TABLE_LOCALEPIETANZA +
                    "(" + LOCALE_ID2 + " INTEGER," +
                    PIETANZA_ID + "INTEGER," +
                    "FOREIGN KEY (" + LOCALE_ID2 + ") REFERENCES (" + LOCALE_ID2 + ")," +
                    "FOREIGN KEY (" + PIETANZA_ID + ") REFERENCES (" + PIETANZA_ID + ")," +")";



    // Costruttore
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Questo metodo viene chiamato durante la creazione del database
    @Override
    public void onCreate(SQLiteDatabase database){

        database.execSQL(CREATE_TABLE_GRUPPO);
        database.execSQL(CREATE_TABLE_LOCALE);
        database.execSQL(CREATE_TABLE_PIETANZA);
        database.execSQL(CREATE_TABLE_PERSONA);
        database.execSQL(CREATE_TABLE_GRUPPOLOCALI);
        database.execSQL(CREATE_TABLE_GRUPPOMEMBRI);
        database.execSQL(CREATE_TABLE_LOCALEPIETANZE);
        
    }

    // Questo metodo viene chiamato durante l'upgrade del database, ad esempio quando viene incrementato il numero di versione
    @Override
    public void onUpgrade( SQLiteDatabase database, int oldVersion, int newVersion ) {

        database.execSQL("DROP TABLE IF EXISTS " + TABLE_GRUPPO);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCALE);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONA);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_PIETANZA);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_GRUPPOLOCALI);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_GRUPPOMEMBRI);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCALEPIETANZA);

        onCreate(database);
        resetData(database);

    }
    
    // Questo metodo resetta i dati originali dopo l'upgrade del database, per ora uso una stringa ma poi proverò a usare i file
    public void resetData (SQLiteDatabase database){
        
        String insert = "insert into persona values (1, 'Martina', 'Senis', 'ms@gmail.com', 'martina', 'martina.jpg');" +
                "insert into persona values (2, 'Alessandro', 'Tola', 'at@gmail.com', 'alessandro', 'ale.jpg');" +
                "insert into persona values (3, 'Giovanni', 'Atzori', 'ga@gmail.com', 'giovanni', 'gio.jpg');" +
                "insert into persona values (4, 'Sara Valeria', 'Scardigli', 'svs@gmail.com', 'valeria', 'sara.jpg');" +
                "insert into persona values (5, 'Lady', 'Oscar', 'lo@gmail.com', 'oscar', 'oscar.jpg');" +
                "insert into persona values (6, 'Giorgio', 'Sgnaffoli', 'gs@gmail.com', 'sgnaffoli', 'sgnaffoli.jpg');" +
                "insert into persona values (7, 'Giannee', 'Phenoo', 'gp@gmail.com', 'giannee', 'giannee.jpg');" +
                "insert into persona values (8, 'Morgan', 'Freeman', 'mf@gmail.com', 'morgan', 'morgan.jpg');" +
                "insert into persona values (9, 'Davide', 'Spano', 'ds@gmail.com', 'davide', 'davide.jpg');" +
                "insert into persona values (10, 'Alessio', 'Muvvu', 'am@gmail.com', 'muvvu', 'muvvu.jpg');" +
                "insert into persona values (11, 'Elisabetta II', 'Windsor', 'ew@gmail.com', 'lizzy', 'eli.jpg');"+"insert into pietanza values (1, 'panino al prosciutto', 1.50);\n" +
                "insert into pietanza values (2, 'panino con cotoletta', 2.50);" +
                "insert into pietanza values (3, 'panino crudo/mozzarella/insalata', 3.50);" +
                "insert into pietanza values (4, 'panino salame/fontina', 2.50);" +
                "insert into pietanza values (5, 'focaccia', 2.50);" +
                "insert into pietanza values (6, 'pizza margherita', 5.00);" +
                "insert into pietanza values (7, 'menù pizzetta normale', 3.99);" +
                "insert into pietanza values (8, 'menù pizzetta maxi', 4.99);" +
                "insert into pietanza values (9, 'cupcake', 2.50);" +
                "insert into pietanza values (10, 'torta', 3.50);" +
                "insert into gruppo values (1, 'Paperelle', 'paperelle.jpg');" +
                "insert into gruppo values (2, 'BatPranzo', 'batpranzo.jpg');" +
                "insert into gruppo values (3, 'SGNAFFOLI', 'SGNAFFOLI.jpg');" +
                "insert into gruppo values (4, 'Pane Amore e Tuleepanee', 'tuleepanee.jpg');" +
                "insert into locale values (1, 'Bar Ghiani', 'ghiani.jpg');" +
                "insert into locale values (2, 'Home Peetza', 'peetza.jpg');" +
                "insert into locale values (3, 'Coccodee', 'coccodee.jpg');" +
                "insert into gruppoLocali values (1, 3);" +
                "insert into gruppoLocali values (2, 1);" +
                "insert into gruppoLocali values (2, 2);" +
                "insert into gruppoLocali values (2, 3);" +
                "insert into gruppoLocali values (3, 1);" +
                "insert into gruppoLocali values (3, 3);" +
                "insert into gruppoLocali values (4, 1);" +
                "insert into gruppoLocali values (4, 2);" +
                "insert into gruppoLocali values (4, 3);" +
                "insert into gruppoMembri values (1, 1);" +
                "insert into gruppoMembri values (1, 2);" +
                "insert into gruppoMembri values (1, 3);" +
                "insert into gruppoMembri values (1, 4);" +
                "insert into gruppoMembri values (2, 1);" +
                "insert into gruppoMembri values (2, 2);" +
                "insert into gruppoMembri values (2, 3);" +
                "insert into gruppoMembri values (2, 4);" +
                "insert into gruppoMembri values (2, 5);" +
                "insert into gruppoMembri values (2, 6);" +
                "insert into gruppoMembri values (2, 7);" +
                "insert into gruppoMembri values (2, 8);" +
                "insert into gruppoMembri values (2, 9);" +
                "insert into gruppoMembri values (2, 10);" +
                "insert into gruppoMembri values (2, 11);" +
                "insert into gruppoMembri values (3, 1);" +
                "insert into gruppoMembri values (3, 4);" +
                "insert into gruppoMembri values (3, 5);" +
                "insert into gruppoMembri values (3, 6);" +
                "insert into gruppoMembri values (4, 2);" +
                "insert into gruppoMembri values (4, 3);" +
                "insert into gruppoMembri values (4, 7);" +
                "insert into gruppoMembri values (4, 8);" +
                "insert into gruppoMembri values (4, 11);" +
                "insert into localiPietanze values (1, 1);";
        
        database.execSQL(insert);
        
    }
}
