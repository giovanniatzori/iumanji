package com.example.giovy.iumanji;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.giovy.iumanji.database.Persona;

import java.util.ArrayList;
import java.util.Map;

public class CreaGruppoPag2Activity extends AppCompatActivity {

    Button crea_gruppo2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_gruppo_pag2);

        crea_gruppo2 = (Button) this.findViewById(R.id.crea_gruppo2_button);
        crea_gruppo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showCreaGruppoPag2 = new Intent(CreaGruppoPag2Activity.this, MainMenu.class);
                startActivity(showCreaGruppoPag2);

            }
        });
    }

    public ArrayList<Persona> getUtentiSelezionati(){
        ArrayList<Persona> personaList = (ArrayList<Persona>) getIntent().getSerializableExtra("ARRAY_PERSONE");
        ArrayList<Persona> personaChecked = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("Contatti", MODE_PRIVATE);
        Map<String, Boolean> pref = (Map<String, Boolean>) sharedPreferences.getAll();

        for (Persona p : personaList) {
            String id = p.getId().toString();
            if (pref.containsKey(id) && pref.get(id)) {
                personaChecked.add(p);
            }
        }

        return personaChecked;
    }
}
