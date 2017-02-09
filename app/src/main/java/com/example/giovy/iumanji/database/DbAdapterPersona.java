package com.example.giovy.iumanji.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by alessandrotola on 09/02/17.
 */

public class DbAdapterPersona {
    @SuppressWarnings("unused")
    private static final String LOG_TAG = DbAdapterGruppo.class.getSimpleName();

    private Context context;
    private SQLiteDatabase iumangiDb;
    private DatabaseHelper dbHelper;

    //Tabella locale
    private static final String DATABASE_TABLE = "persona";
    public static final String ID_PEROSNA = "_idPersona";
    public static final String NOME_PEROSNA = "_nomePersona";
    public static final String COGNOME_PEROSNA = "_cognomePersona";
    public static final String EMAIL_PEROSNA = "_emailPersona";
    public static final String PASSWORD_PEROSNA = "_passwordPersona";
    public static final String IMMAGINE_PERSONA = "immaginePersona";

    public DbAdapterPersona(Context context) {
        this.context = context;
    }

    public DbAdapterPersona open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        iumangiDb = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private ContentValues createContentValues(String idPersona, String nomePersona, String cognomePersona,
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

    //create a contact
    public long createContact(String idPersona, String nomePersona, String cognomePersona,
                              String emailPersona, String passwordPersona, String immaginePersona) {
        ContentValues initialValues = createContentValues(idPersona, nomePersona, cognomePersona, emailPersona, passwordPersona,immaginePersona);
        return iumangiDb.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    //update a contact
    public boolean updateContact( String idPersona, String nomePersona, String cognomePersona,
                                  String emailPersona, String passwordPersona, String immaginePersona) {
        ContentValues updateValues = createContentValues(idPersona, nomePersona, cognomePersona, emailPersona, passwordPersona,immaginePersona);
        return iumangiDb.update(DATABASE_TABLE, updateValues, ID_PEROSNA + "=" + idPersona, null) > 0;
    }

    //delete a contact
    public boolean deleteContact(String idPersona) {
        return iumangiDb.delete(DATABASE_TABLE, ID_PEROSNA + "=" + idPersona, null) > 0;
    }

    //fetch all contacts
    public Cursor fetchAllContacts() {
        return iumangiDb.query(DATABASE_TABLE, new String[] { ID_PEROSNA, NOME_PEROSNA, COGNOME_PEROSNA,
                EMAIL_PEROSNA, PASSWORD_PEROSNA, IMMAGINE_PERSONA}, null, null, null, null, null);
    }

    //fetch contacts filter by a string
    public Cursor fetchContactsByFilter(String filter) {
        Cursor mCursor = iumangiDb.query(true, DATABASE_TABLE, new String[] {
                        ID_PEROSNA, NOME_PEROSNA, COGNOME_PEROSNA,
                        EMAIL_PEROSNA, PASSWORD_PEROSNA, IMMAGINE_PERSONA},
                NOME_PEROSNA + " like '%"+ filter + "%'", null, null, null, null, null);

        return mCursor;
    }
}
