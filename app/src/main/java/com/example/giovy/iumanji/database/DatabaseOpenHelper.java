package com.example.giovy.iumanji.database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by martina on 11/02/17.
 */

public class DatabaseOpenHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "iumangiDb.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
