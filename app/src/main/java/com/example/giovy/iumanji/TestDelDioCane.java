package com.example.giovy.iumanji;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.app.LoaderManager;

import com.example.giovy.iumanji.database.DbAdapter;

public class TestDelDioCane extends Activity {

    private DbAdapter dbHelper;
    private Cursor cursor;
    public SQLiteDatabase iumangiDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_del_dio_cane);

        dbHelper = new DbAdapter(this);
        dbHelper.open();

        //dbHelper.createGroup("1", "Paperelle", "paperelle.jpg");

        cursor = dbHelper.fetchAllGroups();
        //dbHelper.close();


        while ( cursor.moveToNext() ) {

            String contactID = cursor.getString( cursor.getColumnIndex(DbAdapter.ID_GRUPPO) );
            Log.d("boh", "group id = " + contactID);
            System.out.println(contactID);
        }
        cursor.close();

    }
}
