package com.example.giovy.iumanji;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.giovy.iumanji.database.DbAdapter;
import com.example.giovy.iumanji.database.Locale;

public class RisultatoSondaggioActivity extends AppCompatActivity {

    Bundle idGruppo;
    Integer id = 3;
    private DbAdapter helper;
    private Cursor cursor;
    TextView vincitore;
    Locale vincitoreLoc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        idGruppo = getIntent().getExtras();
        //id = idGruppo.getInt("id");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risultato_sondaggio);
        vincitore =(TextView) findViewById(R.id.nomeLocaleVincitore);
        helper = DbAdapter.getInstance(this);
        helper.open();
        cursor = helper.fetchLocalssByFilter(id.toString());
        while (cursor.moveToNext()){
            vincitoreLoc = new Locale(cursor.getString(1), cursor.getString(2));
            System.out.println(cursor.getString(1));
        }
        vincitore.setText(vincitoreLoc.getNome());
    }
}
