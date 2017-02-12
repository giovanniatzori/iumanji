package com.example.giovy.iumanji.database;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


/**
 * Created by alessandrotola on 09/02/17.
 */

public class DatabaseHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "iumangiDb1.db";
    private static final int DATABASE_VERSION = 8;

    // Costruttore
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


}
