package com.example.giovy.iumanji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.CountDownTimer;
import android.widget.EditText;
import android.widget.Toast;


public class CreaSondaggioActivity extends AppCompatActivity {
    Button crea_sondaggio;

    EditText minuti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_sondaggio);

        crea_sondaggio = (Button) this.findViewById(R.id.crea_sondaggio_button);
        crea_sondaggio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minuti = (EditText) findViewById(R.id.minutiId);
                final long minutes = Integer.parseInt(minuti.getText().toString());

                Intent GruppoActivity = new Intent(CreaSondaggioActivity.this, VisualizzaGruppoActivity.class);
                GruppoActivity.putExtra("timerValue", minutes);
                startActivity(GruppoActivity);
            }

        });
    }


}
