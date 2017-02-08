package com.example.giovy.iumanji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Locali extends AppCompatActivity {

    ImageButton aggiungi_locale_button;
    ImageButton vai_locale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locali);

        aggiungi_locale_button = (ImageButton) findViewById(R.id.aggiungi_locale_button);
        aggiungi_locale_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showCreaGruppo = new Intent(Locali.this, AggiungiLocale.class);
                startActivity(showCreaGruppo);


            }
        });
        vai_locale = (ImageButton) findViewById(R.id.vai_locale_button);
        vai_locale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showCreaGruppo = new Intent(Locali.this, RiepilogoLocale.class);
                startActivity(showCreaGruppo);


            }
        });
    }
}
