package com.example.giovy.iumanji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class VisualizzaGruppoActivity extends AppCompatActivity {
    ImageButton vaiLocali1;
    ImageButton vaiGruppo;
    Button visualizzaSondaggio;
    Button creaSondaggio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_gruppo);

        vaiLocali1 = (ImageButton) findViewById(R.id.vai_locali_button);
        vaiLocali1.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             Intent showGruppo = new Intent(VisualizzaGruppoActivity.this, Locali.class);
                                             startActivity(showGruppo);
                                         }
                                     }

        );
        vaiGruppo = (ImageButton) findViewById(R.id.vai_membri_button);
        vaiGruppo.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             Intent showGruppo = new Intent(VisualizzaGruppoActivity.this, MembriGruppoActivity.class);
                                             startActivity(showGruppo);
                                         }
                                     }

        );

        visualizzaSondaggio = (Button) findViewById(R.id.visualizza_sondaggio_button);
        visualizzaSondaggio.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             Intent showGruppo = new Intent(VisualizzaGruppoActivity.this, VisualizzaSondaggioActivity.class);
                                             startActivity(showGruppo);
                                         }
                                     }

        );

        creaSondaggio = (Button) findViewById(R.id.crea_sondaggio_button);
        creaSondaggio.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View view) {
                                                       Intent showGruppo = new Intent(VisualizzaGruppoActivity.this, CreaSondaggioActivity.class);
                                                       startActivity(showGruppo);
                                                   }
                                               }

        );
    }
}