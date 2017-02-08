package com.example.giovy.iumanji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreaSondaggioActivity extends AppCompatActivity {
    Button crea_sondaggio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_sondaggio);

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
