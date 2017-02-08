package com.example.giovy.iumanji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class VisualizzaGruppoActivity extends AppCompatActivity {
    Button crea_sondaggio;
    ImageButton vai_locali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_gruppo);
        vai_locali = (ImageButton) this.findViewById(R.id.vai_locali_gruppo);
        vai_locali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showCreaGruppo = new Intent(VisualizzaGruppoActivity.this, Locali.class);
                startActivity(showCreaGruppo);
            }

        });

        crea_sondaggio = (Button) this.findViewById(R.id.crea_sondaggio_button);
        crea_sondaggio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showCreaGruppo = new Intent(VisualizzaGruppoActivity.this, CreaSondaggioActivity.class);
                startActivity(showCreaGruppo);
            }

        });
    }
}
