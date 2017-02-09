package com.example.giovy.iumanji.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
/**
 * Created by alessandrotola on 09/02/17.
 */

public class DbAdapterLocale {

    @SuppressWarnings("unused")
    private static final String LOG_TAG = DbAdapterGruppo.class.getSimpleName();

    private Context context;
    private SQLiteDatabase iumangiDb;
    private DatabaseHelper dbHelper;

    //Tabella locale
    private static final String DATABASE_TABLE = "locale";
    public static final String ID_LOCALE = "_idLocale";
    public static final String NOME_LOCALE = "nomeLocale";
    public static final String IMMAGINE_LOCALE = "immagineLocale";

    public DbAdapterLocale(Context context) {
        this.context = context;
    }

    public DbAdapterLocale open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        iumangiDb = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private ContentValues createContentValues(String idLocale, String nomeLocale, String immagineLocale ) {
        ContentValues values = new ContentValues();
        values.put( ID_LOCALE, idLocale );
        values.put( NOME_LOCALE, nomeLocale );
        values.put( IMMAGINE_LOCALE, immagineLocale );
        return values;
    }

    //create a contact
    public long createContact(String idLocale, String nomeLocale, String immagineLocale  ) {
        ContentValues initialValues = createContentValues(idLocale, nomeLocale, immagineLocale);
        return iumangiDb.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    //update a contact
    public boolean updateContact( String idLocale, String nomeLocale, String immagineLocale) {
        ContentValues updateValues = createContentValues(idLocale,nomeLocale, immagineLocale);
        return iumangiDb.update(DATABASE_TABLE, updateValues, ID_LOCALE + "=" + idLocale, null) > 0;
    }

    //delete a contact
    public boolean deleteContact(String idLocale) {
        return iumangiDb.delete(DATABASE_TABLE, ID_LOCALE + "=" + idLocale, null) > 0;
    }

    //fetch all contacts
    public Cursor fetchAllContacts() {
        return iumangiDb.query(DATABASE_TABLE, new String[] { ID_LOCALE, NOME_LOCALE, IMMAGINE_LOCALE}, null, null, null, null, null);
    }

    //fetch contacts filter by a string
    public Cursor fetchContactsByFilter(String filter) {
        Cursor mCursor = iumangiDb.query(true, DATABASE_TABLE, new String[] {
                        ID_LOCALE, NOME_LOCALE, IMMAGINE_LOCALE},
                NOME_LOCALE + " like '%"+ filter + "%'", null, null, null, null, null);

        return mCursor;
    }

}
