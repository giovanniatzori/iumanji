package com.example.giovy.iumanji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AggiungiLocale extends AppCompatActivity {

    Button aggiungi_locale_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_locale);

        aggiungi_locale_button = (Button) findViewById(R.id.aggiungi_locale_button);
        aggiungi_locale_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showCreaGruppo = new Intent(AggiungiLocale.this, Locali.class);
                startActivity(showCreaGruppo);
            }
        });


    }
}
