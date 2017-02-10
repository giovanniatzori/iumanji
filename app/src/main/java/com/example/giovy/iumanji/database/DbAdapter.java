package com.example.giovy.iumanji.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by alessandrotola on 09/02/17.
 */

public class DbAdapter {
    @SuppressWarnings("unused")
    private static final String LOG_TAG = DbAdapter.class.getSimpleName();

    private Context context;
    private SQLiteDatabase iumangiDb;
    private DatabaseHelper dbHelper;

    // Tabella gruppo
    private static final String TABLE_GRUPPO = "gruppo";
    public static final String ID_GRUPPO = "_idGruppo";
    public static final String NOME_GRUPPO = "nomeGruppo";
    public static final String IMMAGINE_GRUPPO = "immagineGruppo";
    //Tabella locale
    private static final String TABLE_LOCALE = "locale";
    public static final String ID_LOCALE = "_idLocale";
    public static final String NOME_LOCALE = "nomeLocale";
    public static final String IMMAGINE_LOCALE = "immagineLocale";
    //Tabella locale
    private static final String TABLE_PERSONA = "persona";
    public static final String ID_PEROSNA = "_idPersona";
    public static final String NOME_PEROSNA = "_nomePersona";
    public static final String COGNOME_PEROSNA = "_cognomePersona";
    public static final String EMAIL_PEROSNA = "_emailPersona";
    public static final String PASSWORD_PEROSNA = "_passwordPersona";
    public static final String IMMAGINE_PERSONA = "immaginePersona";
    // Tabella gruppo
    private static final String TABLE_PIETANZA = "pietanza";
    public static final String ID_PIETANZA = "_idPietanza";
    public static final String NOME_PIETANZA = "nomePietanza";
    public static final String PREZZO_PIETANZA = "prezzoPietanza";

    public DbAdapter(Context context) {
        this.context = context;
    }

    public DbAdapter open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        iumangiDb = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }
    //Operazioni tabelle gruppo
    private ContentValues createGroupValues(String idGruppo, String nomeGruppo,  String immagineGruppo ) {

        ContentValues values = new ContentValues();
        values.put( ID_GRUPPO, idGruppo );
        values.put( NOME_GRUPPO, nomeGruppo );
        values.put( IMMAGINE_GRUPPO, immagineGruppo );
        return values;
    }

    //Istanziare un gruppo
    public long createGroup(String idGruppo, String nomeGruppo,  String immagineGruppo ) {
        ContentValues initialValues = createGroupValues(idGruppo, nomeGruppo, immagineGruppo);
        return iumangiDb.insertOrThrow(TABLE_GRUPPO, null, initialValues);
    }

    //Aggiornare un gruppo
    public boolean updateGroup( String idGruppo, String nomeGruppo, String ImmagineGruppo) {
        ContentValues updateValues = createGroupValues(idGruppo,nomeGruppo, ImmagineGruppo);
        return iumangiDb.update(TABLE_GRUPPO, updateValues, ID_GRUPPO + "=" + idGruppo, null) > 0;
    }

    //Cancellareun gruppo
    public boolean deleteGroup(String idGruppo) {
        return iumangiDb.delete(TABLE_GRUPPO, ID_GRUPPO + "=" + idGruppo, null) > 0;
    }

    //Tirare su tutti i gruppi
    public Cursor fetchAllGroups() {
        return iumangiDb.query(TABLE_GRUPPO, new String[] { ID_GRUPPO, NOME_GRUPPO, IMMAGINE_GRUPPO}, null, null, null, null, null);
    }

    //Tirare su tutti i gruppi filtrando da stringa
    public Cursor fetchGroupByFilter(String filter) {
        Cursor mCursor = iumangiDb.query(true, TABLE_GRUPPO, new String[] {
                        ID_GRUPPO, NOME_GRUPPO, IMMAGINE_GRUPPO},
                NOME_GRUPPO + " like '%"+ filter + "%'", null, null, null, null, null);

        return mCursor;
    }

    //Operazioni tabelle locale
    private ContentValues createLocaleValues(String idLocale, String nomeLocale, String immagineLocale ) {
        ContentValues values = new ContentValues();
        values.put( ID_LOCALE, idLocale );
        values.put( NOME_LOCALE, nomeLocale );
        values.put( IMMAGINE_LOCALE, immagineLocale );
        return values;
    }

    //Istanziare un locale
    public long createLocal(String idLocale, String nomeLocale, String immagineLocale  ) {
        ContentValues initialValues = createLocaleValues(idLocale, nomeLocale, immagineLocale);
        return iumangiDb.insertOrThrow(TABLE_LOCALE, null, initialValues);
    }

    //Aggiornare un locale
    public boolean updateLocal( String idLocale, String nomeLocale, String immagineLocale) {
        ContentValues updateValues = createLocaleValues(idLocale,nomeLocale, immagineLocale);
        return iumangiDb.update(TABLE_LOCALE, updateValues, ID_LOCALE + "=" + idLocale, null) > 0;
    }

    //Cancellare un locale
    public boolean deleteLocal(String idLocale) {
        return iumangiDb.delete(TABLE_LOCALE, ID_LOCALE + "=" + idLocale, null) > 0;
    }

    //Tirare su tutti i locali
    public Cursor fetchAllLocals() {
        return iumangiDb.query(TABLE_LOCALE, new String[] { ID_LOCALE, NOME_LOCALE, IMMAGINE_LOCALE}, null, null, null, null, null);
    }

    //Tirare su tutti i locali filtrando da stringa
    public Cursor fetchLocalssByFilter(String filter) {
        Cursor mCursor = iumangiDb.query(true, TABLE_LOCALE, new String[] {
                        ID_LOCALE, NOME_LOCALE, IMMAGINE_LOCALE},
                NOME_LOCALE + " like '%"+ filter + "%'", null, null, null, null, null);

        return mCursor;
    }

    //Operazioni tabelle Persona
    private ContentValues createPersonValues(String idPersona, String nomePersona, String cognomePersona,
                                              String emailPersona, String passwordPersona, String immaginePersona ) {
        ContentValues values = new ContentValues();
        values.put( ID_PEROSNA, idPersona );
        values.put( NOME_PEROSNA, nomePersona );
        values.put( COGNOME_PEROSNA, cognomePersona);
        values.put( EMAIL_PEROSNA, emailPersona);
        values.put( PASSWORD_PEROSNA, passwordPersona);
        values.put( IMMAGINE_PERSONA, immaginePersona );

        return values;
    }

    //Istanziare un persona
    public long createPerson(String idPersona, String nomePersona, String cognomePersona,
                              String emailPersona, String passwordPersona, String immaginePersona) {
        ContentValues initialValues = createPersonValues(idPersona, nomePersona, cognomePersona, emailPersona, passwordPersona,immaginePersona);
        return iumangiDb.insertOrThrow(TABLE_PERSONA, null, initialValues);
    }

    //Aggionrare una persona
    public boolean updatePerson( String idPersona, String nomePersona, String cognomePersona,
                                  String emailPersona, String passwordPersona, String immaginePersona) {
        ContentValues updateValues = createPersonValues(idPersona, nomePersona, cognomePersona, emailPersona, passwordPersona,immaginePersona);
        return iumangiDb.update(TABLE_PERSONA, updateValues, ID_PEROSNA + "=" + idPersona, null) > 0;
    }

    //Cancellare una persona
    public boolean deletePerson(String idPersona) {
        return iumangiDb.delete(TABLE_PERSONA, ID_PEROSNA + "=" + idPersona, null) > 0;
    }

    //Tirare su tutte le persone
    public Cursor fetchAllPersons() {
        return iumangiDb.query(TABLE_PERSONA, new String[] { ID_PEROSNA, NOME_PEROSNA, COGNOME_PEROSNA,
                EMAIL_PEROSNA, PASSWORD_PEROSNA, IMMAGINE_PERSONA}, null, null, null, null, null);
    }

    //Tirare su tutte le persone filtrando da stringa
    public Cursor fetchPersonsByFilter(String filter) {
        Cursor mCursor = iumangiDb.query(true, TABLE_PERSONA, new String[] {
                        ID_PEROSNA, NOME_PEROSNA, COGNOME_PEROSNA,
                        EMAIL_PEROSNA, PASSWORD_PEROSNA, IMMAGINE_PERSONA},
                NOME_PEROSNA + " like '%"+ filter + "%'", null, null, null, null, null);

        return mCursor;
    }

    //Operazione sulle pietanza
    private ContentValues createPietanzaValues(String idPietanza, String nomePietanza, String prezzoPietanza ) {
        ContentValues values = new ContentValues();
        values.put( ID_PIETANZA, idPietanza );
        values.put( NOME_PIETANZA, nomePietanza );
        values.put( PREZZO_PIETANZA, prezzoPietanza );
        return values;
    }

    //creare una pietanza
    public long createPietanza(String idPietanza, String nomePietanza,  String prezzoPietanza ) {
        ContentValues initialValues = createPietanzaValues(idPietanza, nomePietanza, prezzoPietanza);
        return iumangiDb.insertOrThrow(TABLE_PIETANZA, null, initialValues);
    }

    //aggiornare una pietanza
    public boolean updatePietanza( String idPietanza, String nomePietanza, String prezzoPietanza) {
        ContentValues updateValues = createPietanzaValues(idPietanza,nomePietanza, prezzoPietanza);
        return iumangiDb.update(TABLE_PIETANZA, updateValues, ID_PIETANZA + "=" + idPietanza, null) > 0;
    }

    //eliminare una pietanza
    public boolean deletePietanza(String idPietanza) {
        return iumangiDb.delete(TABLE_PIETANZA, ID_PIETANZA + "=" + idPietanza, null) > 0;
    }

    //tirare su le pietanza
    public Cursor fetchAllPietanze() {
        return iumangiDb.query(TABLE_PIETANZA, new String[] { ID_PIETANZA, NOME_PIETANZA, PREZZO_PIETANZA}, null, null, null, null, null);
    }

    //tirare su pietanze filtrando per stringa
    public Cursor fetchPietanzaByFilter(String filter) {
        Cursor mCursor = iumangiDb.query(true, TABLE_PIETANZA, new String[] {
                        ID_PIETANZA, NOME_PIETANZA, PREZZO_PIETANZA},
                NOME_PIETANZA + " like '%"+ filter + "%'", null, null, null, null, null);

        return mCursor;
    }

}
