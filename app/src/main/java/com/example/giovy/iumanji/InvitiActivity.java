package com.example.giovy.iumanji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InvitiActivity extends AppCompatActivity {

    Button accettaInvitoButton;
    Button rifiutaInvitoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inviti);

        accettaInvitoButton = (Button) this.findViewById(R.id.invito_accetta_button);
        rifiutaInvitoButton = (Button) this.findViewById(R.id.invito_rifiuta_button);

        accettaInvitoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accettaInvito = new Intent(InvitiActivity.this, MainMenu.class);
                startActivity(accettaInvito);

            }
        } );

        rifiutaInvitoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rifiutaInvito = new Intent(InvitiActivity.this, MainMenu.class);
                startActivity(rifiutaInvito);

            }
        } );
    }
}