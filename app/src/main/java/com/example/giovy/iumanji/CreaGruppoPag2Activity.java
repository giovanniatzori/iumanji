package com.example.giovy.iumanji;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.giovy.iumanji.database.Persona;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreaGruppoPag2Activity extends AppCompatActivity {

    Button crea_gruppo2;
    private ListView listview;

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

        listview = (ListView) findViewById(R.id.lista_persone_scelte);

        String[] listContent =  getPersoneSelect();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listContent);

        listview.setAdapter(adapter);
    }

    public String[] getPersoneSelect (){
        ArrayList<String> nomi = new ArrayList<>();
        HashMap<String, Boolean> personeSelect = (HashMap<String, Boolean>) getIntent().getSerializableExtra("personeSelect");

        for(String s : personeSelect.keySet()){
            if(personeSelect.get(s))
                nomi.add(s);
        }

        return nomi.toArray(new String[0]);

    }

}
