package com.example.giovy.iumanji.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;

/**
 * Created by alessandrotola on 09/02/17.
 */

public class DbAdapter {
    @SuppressWarnings("unused")
    private static final String LOG_TAG = DbAdapter.class.getSimpleName();


    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase iumangiDb;
    private static DbAdapter instance;

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

    //Tabella persona
    private static final String TABLE_PERSONA = "persona";
    public static final String ID_PERSONA = "_idPersona";
    public static final String NOME_PERSONA = "nomePersona";
    public static final String COGNOME_PERSONA = "cognomePersona";
    public static final String EMAIL_PERSONA = "emailPersona";
    public static final String PASSWORD_PERSONA = "passwordPersona";
    public static final String IMMAGINE_PERSONA = "immaginePersona";

    // Tabella gruppo
    private static final String TABLE_PIETANZA = "pietanza";
    public static final String ID_PIETANZA = "_idPietanza";
    public static final String NOME_PIETANZA = "nomePietanza";
    public static final String PREZZO_PIETANZA = "prezzoPietanza";

    //Tabella gruppoLocali
    private static final String TABLE_GRUPPOLOCALI = "gruppoLocali";
    public static final String GRUPPO_ID1 = "gruppo";
    public static final String LOCALE_ID1 = "locale";

    //Tabella gruppoMembri
    private static final String TABLE_GRUPPOMEMBRI = "gruppoMembri";
    public static final String GRUPPO_ID2 = "gruppo";
    public static final String PERSONA_ID = "persona";

    //Tabella localePietanze
    private static final String TABLE_LOCALEPIETANZA = "localePietanze";
    public static final String LOCALE_ID2 = "locale";
    public static final String PIETANZA_ID = "pietanza";

    private DbAdapter(Context context) {
        this.openHelper = new DatabaseHelper(context);
    }


    public static DbAdapter getInstance(Context context) {
        if (instance == null) {
            instance = new DbAdapter(context);
        }
        return instance;
    }
    public void open() {
        this.iumangiDb = openHelper.getWritableDatabase();
    }

    public void close() {
        if (iumangiDb != null) {
            this.iumangiDb.close();
        }
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
    public void insertByString(String insert){
        iumangiDb.execSQL(insert);
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
                ID_GRUPPO + "=" + filter , null, null, null, null, null);

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
        values.put( ID_PERSONA, idPersona );
        values.put( NOME_PERSONA, nomePersona );
        values.put( COGNOME_PERSONA, cognomePersona);
        values.put( EMAIL_PERSONA, emailPersona);
        values.put( PASSWORD_PERSONA, passwordPersona);
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
        return iumangiDb.update(TABLE_PERSONA, updateValues, ID_PERSONA + "=" + idPersona, null) > 0;
    }

    //Cancellare una persona
    public boolean deletePerson(String idPersona) {
        return iumangiDb.delete(TABLE_PERSONA, ID_PERSONA + "=" + idPersona, null) > 0;
    }

    //Tirare su tutte le persone
    public Cursor fetchAllPersons() {
        return iumangiDb.query(TABLE_PERSONA, new String[] { ID_PERSONA, NOME_PERSONA, COGNOME_PERSONA, EMAIL_PERSONA, PASSWORD_PERSONA, IMMAGINE_PERSONA}, null, null, null, null, null);
    }

    //Tirare su tutte le persone filtrando da stringa
    public Cursor fetchPersonsByFilter(String filter) {
        Cursor mCursor = iumangiDb.query(true, TABLE_PERSONA, new String[] {
                        ID_PERSONA, NOME_PERSONA, COGNOME_PERSONA,
                        EMAIL_PERSONA, PASSWORD_PERSONA, IMMAGINE_PERSONA},
                NOME_PERSONA + " like '%"+ filter + "%'", null, null, null, null, null);

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

    //Operazioni tabelle gruppoLocali
    private ContentValues createGroupLocaleValues(String idGruppo, String idLocale) {
        ContentValues values = new ContentValues();
        values.put( GRUPPO_ID1, idGruppo );
        values.put( LOCALE_ID1, idLocale );
        return values;
    }

    //Istanziare un locale
    public long createGroupLocal(String idGruppo, String idLocale) {
        ContentValues initialValues = createGroupLocaleValues(idGruppo, idLocale);
        return iumangiDb.insertOrThrow(TABLE_GRUPPOLOCALI, null, initialValues);
    }

    //Aggiornare un locale
    public boolean updateGroupLocal( String idGruppo, String idLocale) {
        ContentValues updateValues = createGroupLocaleValues(idGruppo, idLocale);
        return iumangiDb.update(
                TABLE_GRUPPOLOCALI, updateValues,
                GRUPPO_ID1 + "=" + idGruppo + " AND " + ID_LOCALE + "=" + idLocale,
                null) > 0;
    }

    //Cancellare un locale
    public boolean deleteGroupLocal(String idGruppo, String idLocale) {
        return iumangiDb.delete(
                TABLE_GRUPPOLOCALI,
                GRUPPO_ID1 + "=" + idGruppo + " AND " + ID_LOCALE + "=" + idLocale,
                null) > 0;
    }

    //Tirare su tutti i locali
    public Cursor fetchAllGroupLocals() {
        return iumangiDb.query(TABLE_GRUPPOLOCALI, new String[] { GRUPPO_ID1, LOCALE_ID1 }, null, null, null, null, null);
    }

    //Tirare su tutti i locali filtrando da stringa
    public Cursor fetchGroupLocalsByFilter(String filter1) {

        Cursor mCursor = iumangiDb.query(true, TABLE_GRUPPOLOCALI  + " JOIN " + TABLE_LOCALE + " ON " + TABLE_GRUPPOLOCALI +"."+
                LOCALE_ID1 + " = " + TABLE_LOCALE+"."+ID_LOCALE, new String[] {
                        TABLE_LOCALE+"."+NOME_LOCALE, TABLE_LOCALE+"."+IMMAGINE_LOCALE},
                TABLE_GRUPPOLOCALI+"."+GRUPPO_ID1 + "=" + filter1 , null, null, null, null, null);
        return mCursor;
    }

    //Operazioni tabelle gruppoMembri
    private ContentValues createGroupMemberValues(String idGruppo, String idPersona) {
        ContentValues values = new ContentValues();
        values.put( GRUPPO_ID2, idGruppo );
        values.put( PERSONA_ID, idPersona );
        return values;
    }

    //Istanziare un gruppoMembri
    public long createGroupMember(String idGruppo, String idPersona) {
        ContentValues initialValues = createGroupMemberValues(idGruppo, idPersona);
        return iumangiDb.insertOrThrow(TABLE_GRUPPOMEMBRI, null, initialValues);
    }

    //Aggiornare un gruppoMembri
    public boolean updateGroupMember( String idGruppo, String idPersona) {
        ContentValues updateValues = createGroupLocaleValues(idGruppo, idPersona);
        return iumangiDb.update(
                TABLE_GRUPPOMEMBRI, updateValues,
                GRUPPO_ID2 + "=" + idGruppo + " AND " + PERSONA_ID + "=" + idPersona,
                null) > 0;
    }

    //Cancellare un gruppoMembri
    public boolean deleteGroupMember(String idGruppo, String idPersona) {
        return iumangiDb.delete(
                TABLE_GRUPPOMEMBRI,
                GRUPPO_ID2 + "=" + idGruppo + " AND " + PERSONA_ID + "=" + idPersona,
                null) > 0;
    }

    //Tirare su tutti i gruppoMembri
    public Cursor fetchAllGroupMember() {
        return iumangiDb.query(TABLE_GRUPPOMEMBRI, new String[] { GRUPPO_ID2, PERSONA_ID }, null, null, null, null, null);
    }

    //Tirare su tutti i gruppoMembri filtrando da stringa
    public Cursor fetchGroupMembersByFilter(String filter1, String filter2) {
        Cursor mCursor = iumangiDb.query(true, TABLE_GRUPPOMEMBRI, new String[] {
                        GRUPPO_ID2, PERSONA_ID},
                GRUPPO_ID2 + " like '%"+ filter1 + "%' AND " +
                        PERSONA_ID + " like '%"+ filter2 + "%'", null, null, null, null, null);

        return mCursor;
    }

    //Operazioni tabelle localiPietanze
    private ContentValues createlocaliPietanzeValues(String idLocale, String idPietanza) {
        ContentValues values = new ContentValues();
        values.put( GRUPPO_ID2, idLocale );
        values.put( PERSONA_ID, idPietanza );
        return values;
    }

    //Istanziare un localiPietanze
    public long createlocaliPietanze(String idLocale, String idPietanza) {
        ContentValues initialValues = createlocaliPietanzeValues(idLocale, idPietanza);
        return iumangiDb.insertOrThrow(TABLE_LOCALEPIETANZA, null, initialValues);
    }

    //Aggiornare un localiPietanze
    public boolean updateLocaliPietanze( String idLocale, String idPietanza) {
        ContentValues updateValues = createGroupLocaleValues(idLocale, idPietanza);
        return iumangiDb.update(
                TABLE_LOCALEPIETANZA, updateValues,
                LOCALE_ID2 + "=" + idLocale + " AND " + PIETANZA_ID + "=" + idPietanza,
                null) > 0;
    }

    //Cancellare un localiPietanze
    public boolean deleteLocaliPietanze(String idLocale, String idPietanza) {
        return iumangiDb.delete(
                TABLE_GRUPPOMEMBRI,
                LOCALE_ID2 + "=" + idLocale + " AND " + PIETANZA_ID + "=" + idPietanza,
                null) > 0;
    }

    //Tirare su tutti i localiPietanze
    public Cursor fetchAllLocaliPietanze() {
        return iumangiDb.query(TABLE_LOCALEPIETANZA, new String[] { LOCALE_ID2, PIETANZA_ID }, null, null, null, null, null);
    }

    //Tirare su tutti i localiPietanze filtrando da stringa
    public Cursor fetchLocaliPietanzesByFilter(String filter1, String filter2) {
        Cursor mCursor = iumangiDb.query(true, TABLE_LOCALEPIETANZA, new String[] {
                        LOCALE_ID2, PIETANZA_ID},
                LOCALE_ID2 + " like '%"+ filter1 + "%' AND " +
                        PIETANZA_ID + " like '%"+ filter2 + "%'", null, null, null, null, null);

        return mCursor;
    }


}

