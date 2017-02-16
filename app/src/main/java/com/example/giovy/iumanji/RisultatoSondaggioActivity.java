package com.example.giovy.iumanji;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    Button scegliPietanza;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        idGruppo = getIntent().getExtras();
        //id = idGruppo.getInt("id");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risultato_sondaggio);
        vincitore =(TextView) findViewById(R.id.nomeLocaleVincitore);

        scegliPietanza = (Button) findViewById(R.id.scegli_pietanza_button);
        scegliPietanza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showGruppo = new Intent(RisultatoSondaggioActivity.this, ScegliPietanza.class);
                startActivity(showGruppo);
            }
        });
        
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
