package com.example.giovy.iumanji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VisualizzaSondaggioActivity extends AppCompatActivity {
    Button visualizza_sondaggio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_sondaggio);

        visualizza_sondaggio=(Button) this.findViewById(R.id.vota_button);
        visualizza_sondaggio.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
            Intent showMainMenu = new Intent(VisualizzaSondaggioActivity.this,MainMenu.class);
            startActivity(showMainMenu);
                finish();
            }
        });
    }
}
