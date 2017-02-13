package com.example.giovy.iumanji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class CreaSondaggioActivity extends AppCompatActivity {
    Button crea_sondaggio;
    private ImageView home_pizza_immagine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_sondaggio);
        home_pizza_immagine = (ImageView) findViewById(R.id.Locale_1_image);
        home_pizza_immagine.setImageResource(R.drawable.home_pizza);

        crea_sondaggio = (Button) this.findViewById(R.id.crea_sondaggio_button);
        crea_sondaggio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showCreaGruppo = new Intent(CreaSondaggioActivity.this, VisualizzaGruppoActivity.class);
                startActivity(showCreaGruppo);
            }

        });
    }
}
