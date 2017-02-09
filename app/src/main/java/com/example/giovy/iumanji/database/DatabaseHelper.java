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

    // Lo statement SQL di creazione del database
    private static final String DATABASE_CREATE =
            //Tabella gruppi
            "create table gruppo (_idGruppo integer primary key autoincrement, " +
            "nomeGruppo text not null, immagineGruppo text );" +
            //Tabella locali
            "create table locale(_idLocale integer primary key autoincrement, " +
            "nomeLocale text not null, immagineLocale text );" +
            //Tabella pietanze
            "create table pietanza(_idPietanza integer primary key autoincrement, " +
            "nomePietanza text not null, prezzoPietanza decimal(8,2));" +
            //Tabella persone
            "create table persona(_idPersona integer primary key autoincrement, " +
            "nomePersona text not null, cognomePersona text not null, emailPersona text not null, pesswordPersona text not null," +
            " immaginePersona text ); + " +
            //Tabella locali per ogni gruppo
            "create table gruppoLocale (gruppo integer, gocale integer, primary key (gruppo, locale), " +
            "foreign key(gruppo) references (idGruppo) , foreign key(locale) references (idLocale))" +
            //Tabella membri di ogni gruppo
            "create table gruppoMembri (gruppo integer, persona integer,primary key (gruppo, persona)," +
                    " foreign key(gruppo) references (idGruppo) , foreign key(persona) references (idPersona))" +
            //Tabella pietanza per ogni locale
            "crete table localePietanza (locale integer, pietanza integer, primary key (locale, pietanza)," +
                    " foreign key(locale) references (idLocale), foreign key(pietanza) references (idPietanza)) ";

    // Costruttore
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Questo metodo viene chiamato durante la creazione del database
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    // Questo metodo viene chiamato durante l'upgrade del database, ad esempio quando viene incrementato il numero di versione
    @Override
    public void onUpgrade( SQLiteDatabase database, int oldVersion, int newVersion ) {

        database.execSQL("DROP TABLE IF EXISTS gruppo");
        database.execSQL("DROP TABLE IF EXISTS locale");
        database.execSQL("DROP TABLE IF EXISTS pietanza");
        database.execSQL("DROP TABLE IF EXISTS persona");
        database.execSQL("DROP TABLE IF EXISTS gruppoLocale");
        onCreate(database);

    }
}
