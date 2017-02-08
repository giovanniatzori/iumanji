package com.example.giovy.iumanji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class VisualizzaGruppoActivity extends AppCompatActivity {
    ImageButton vauGruppo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_gruppo);

        ImageButton vaiGruppo = (ImageButton) findViewById(R.id.vai_membri_button);
        vaiGruppo.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             Intent showGruppo = new Intent(VisualizzaGruppoActivity.this, MembriGruppoActivity.class);
                                             startActivity(showGruppo);
                                         }
                                     }

        );
    }
}
