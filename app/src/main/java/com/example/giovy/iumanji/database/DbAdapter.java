package com.example.giovy.iumanji.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by martina on 06/02/17.
 */

public class DbAdapter {
    @SuppressWarnings("unused")
    private static final String LOG_TAG = DbAdapter.class.getSimpleName();

    private Context context;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    //Database fields
    private static final String UTENTI = "utente";
    private static final String GRUPPI = "gruppo";
    private static final String PARTECIPAZIONI = "partecipa";

/*
    public static final String KEY_CONTACTID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_SURNAME = "surname";
    public static final String KEY_SEX = "sex";
    public static final String KEY_BIRTH_DATE = "birth_date";*/

    public DbAdapter(Context context) {
        this.context = context;
    }

    public DbAdapter open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private ContentValues createContentValues(String name, String surname, String sex, String birth_date) {
        ContentValues values = new ContentValues();
        /*values.put( KEY_NAME, date );
        values.put( KEY_SURNAME, flag_send_sms );
        values.put( KEY_SEX, text_send_sms );
        values.put( KEY_BIRTH_DATE, date_sent_sms );*/

        return values;
    }
    /*
    //create a contact
    public long createContact(String name, String surname, String sex, String birth_date ) {
        ContentValues initialValues = createContentValues(name, surname, sex, birth_date);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    //update a contact
    public boolean updateContact( long contactID, String name, String surname, String sex, String birth_date ) {
        ContentValues updateValues = createContentValues(name, surname, sex, birth_date);
        return database.update(DATABASE_TABLE, updateValues, KEY_ CONTACTID + "=" + contactID, null) > 0;
    }

    //delete a contact
    public boolean deleteContact(long contactID) {
        return database.delete(DATABASE_TABLE, KEY_ CONTACTID + "=" + contactID, null) > 0;
    }

    //fetch all contacts
    public Cursor fetchAllContacts() {
        return database.query(DATABASE_TABLE, new String[] { KEY_CONTACTID, KEY_NAME, KEY_SURNAME, KEY_SEX, KEY_BIRTH_DATE}, null, null, null, null, null);
    }

    //fetch contacts filter by a string
    public Cursor fetchContactsByFilter(String filter) {
        Cursor mCursor = database.query(true, DATABASE_TABLE, new String[] {
                        KEY_CONTACTID, KEY_NAME, KEY_SURNAME, KEY_SEX, KEY_BIRTH_DATE },
                KEY_NAME + " like '%"+ filter + "%'", null, null, null, null, null);

        return mCursor;
    }*/

}