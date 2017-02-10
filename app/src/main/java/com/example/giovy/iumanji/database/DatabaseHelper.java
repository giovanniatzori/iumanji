package com.example.giovy.iumanji.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    public static final String PERSONA_ID = "persona";

    //Colonne localiPietanze
    public static final String LOCALE_ID2 = "locale";
    public static final String PIETANZA_ID = "pietanza";

    //Crazione tabella gruppo
    private static final String CREATE_TABLE_GRUPPO =
            "CREATE TABLE " + TABLE_GRUPPO +
                    "(" + ID_GRUPPO + " INTEGER PRIMARY KEY ," +
                    NOME_GRUPPO + " TEXT NOT NULL," +
                    IMMAGINE_GRUPPO + " TEXT"  + ");";

    //Crazione tabella locale
    private static final String CREATE_TABLE_LOCALE =
            "CREATE TABLE " + TABLE_LOCALE +
                    "(" + ID_LOCALE + " INTEGER PRIMARY KEY ," +
                    NOME_LOCALE + " TEXT NOT NULL," +
                    IMMAGINE_LOCALE + " TEXT"  + ");";

    //Crazione tabella pietanza
    private static final String CREATE_TABLE_PIETANZA =
            "CREATE TABLE " + TABLE_PIETANZA +
                    "(" + ID_PIETANZA + " INTEGER PRIMARY KEY ," +
                    NOME_PIETANZA + " TEXT NOT NULL," +
                    PREZZO_PIETANZA + " TEXT"  + ");";

    //Crazione tabella persona
    private static final String CREATE_TABLE_PERSONA =
            "CREATE TABLE " + TABLE_PERSONA +
                    "(" + ID_PERSONA + " INTEGER PRIMARY KEY ," +
                    NOME_PERSONA + " TEXT NOT NULL," +
                    COGNOME_PERSONA + " TEXT NOT NULL,"  +
                    EMAIL_PERSONA + "TEXT NOT NULL,"  +
                    PASSWORD_PERSONA +"TEXT NOT NULL,"  +
                    IMMAGINE_PERSONA + "TEXT" + ");";

    //Creazione tabella groppoLocali
    private  static final String CREATE_TABLE_GRUPPOLOCALI =
            "CREATE TABLE " + TABLE_GRUPPOLOCALI +
                    "(" + GRUPPO_ID1 + " INTEGER," +
                    LOCALE_ID1 + " INTEGER," +
                    "PRIMARY KEY(" + GRUPPO_ID1 + "," + LOCALE_ID1 +")," +
                    "FOREIGN KEY (" + GRUPPO_ID1 + ") REFERENCES " + TABLE_GRUPPO + " (" + ID_GRUPPO + ")," +
                    "FOREIGN KEY (" + LOCALE_ID1 + ") REFERENCES " + TABLE_LOCALE + " (" + ID_LOCALE + ")" +");";

    //Creazione tabella groppoMembri
    private  static final String CREATE_TABLE_GRUPPOMEMBRI =
            "CREATE TABLE " + TABLE_GRUPPOMEMBRI +
                    "(" + GRUPPO_ID2 + " INTEGER," +
                    PERSONA_ID + " INTEGER," +
                    "PRIMARY KEY(" + GRUPPO_ID2 + "," + PERSONA_ID +")," +
                    "FOREIGN KEY (" + GRUPPO_ID2 + ") REFERENCES " + TABLE_GRUPPO + " (" + ID_GRUPPO + ")," +
                    "FOREIGN KEY (" + PERSONA_ID + ") REFERENCES " + TABLE_PERSONA + " (" + PERSONA_ID + ")" +");";

    //Creazione tabella localePietanza
    private  static final String CREATE_TABLE_LOCALEPIETANZE =
            "CREATE TABLE " + TABLE_LOCALEPIETANZA +
                    "(" + LOCALE_ID2 + " INTEGER," +
                    PIETANZA_ID + " INTEGER," +
                    "PRIMARY KEY(" + LOCALE_ID2 + "," + PIETANZA_ID +")," +
                    "FOREIGN KEY (" + LOCALE_ID2 + ") REFERENCES " + TABLE_LOCALE + " (" + LOCALE_ID2 + ")," +
                    "FOREIGN KEY (" + PIETANZA_ID + ") REFERENCES " + TABLE_PIETANZA + " (" + PIETANZA_ID + ")" +");";



    // Costruttore
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Questo metodo viene chiamato durante la creazione del database
    @Override
    public void onCreate(SQLiteDatabase database) {

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

    }
}
