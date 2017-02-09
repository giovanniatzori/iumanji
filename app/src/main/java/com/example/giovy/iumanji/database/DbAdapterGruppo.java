package com.example.giovy.iumanji.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

/**
 * Created by alessandrotola on 09/02/17.
 */

public class DbAdapterGruppo {
    @SuppressWarnings("unused")
    private static final String LOG_TAG = DbAdapterGruppo.class.getSimpleName();

    private Context context;
    private SQLiteDatabase iumangiDb;
    private DatabaseHelper dbHelper;
    // Tabella gruppo
    private static final String DATABASE_TABLE = "gruppo";
    public static final String ID_GRUPPO = "_idGruppo";
    public static final String NOME_GRUPPO = "nomeGruppo";
    public static final String IMMAGINE_GRUPPO = "immagineGruppo";

    public DbAdapterGruppo(Context context) {
        this.context = context;
    }

    public DbAdapterGruppo open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        iumangiDb = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private ContentValues createContentValues(String idGruppo, String nomeGruppo,  String immagineGruppo ) {
        ContentValues values = new ContentValues();
        values.put( ID_GRUPPO, idGruppo );
        values.put( NOME_GRUPPO, nomeGruppo );
        values.put( IMMAGINE_GRUPPO, immagineGruppo );
        return values;
    }

    //create a contact
    public long createContact(String idGruppo, String nomeGruppo,  String immagineGruppo ) {
        ContentValues initialValues = createContentValues(idGruppo, nomeGruppo, immagineGruppo);
        return iumangiDb.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    //update a contact
    public boolean updateContact( String idGruppo, String nomeGruppo, String ImmagineGruppo) {
        ContentValues updateValues = createContentValues(idGruppo,nomeGruppo, ImmagineGruppo);
        return iumangiDb.update(DATABASE_TABLE, updateValues, ID_GRUPPO + "=" + idGruppo, null) > 0;
    }

    //delete a contact
    public boolean deleteContact(String idGruppo) {
        return iumangiDb.delete(DATABASE_TABLE, ID_GRUPPO + "=" + idGruppo, null) > 0;
    }

    //fetch all contacts
    public Cursor fetchAllContacts() {
        return iumangiDb.query(DATABASE_TABLE, new String[] { ID_GRUPPO, NOME_GRUPPO, IMMAGINE_GRUPPO}, null, null, null, null, null);
    }

    //fetch contacts filter by a string
    public Cursor fetchContactsByFilter(String filter) {
        Cursor mCursor = iumangiDb.query(true, DATABASE_TABLE, new String[] {
                        ID_GRUPPO, NOME_GRUPPO, IMMAGINE_GRUPPO},
                NOME_GRUPPO + " like '%"+ filter + "%'", null, null, null, null, null);

        return mCursor;
    }
}
