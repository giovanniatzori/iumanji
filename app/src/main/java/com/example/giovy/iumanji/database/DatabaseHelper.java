package com.example.giovy.iumanji.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by martina on 06/02/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table contact (_id integer primary key autoincrement, name text not null, surname text not null, sex text not null, birth_date text not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade( SQLiteDatabase database, int oldVersion, int newVersion ) {

        database.execSQL("DROP TABLE IF EXISTS contact");
        onCreate(database);

    }
}
