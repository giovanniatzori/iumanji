package com.example.giovy.iumanji;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.giovy.iumanji.database.DbAdapter;
import com.example.giovy.iumanji.database.Persona;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreaGruppoPag2Activity extends AppCompatActivity {

    Button crea_gruppo2;
    private ListView listview;
    private Cursor cursor;
    private DbAdapter helper;
    private List<Integer> idSelect = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_gruppo_pag2);


        crea_gruppo2 = (Button) this.findViewById(R.id.crea_gruppo2_button);
        crea_gruppo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nomeGruppo = ((EditText) findViewById(R.id.nomeGruppo)).getText().toString();

                Context context = getApplicationContext();

                helper = DbAdapter.getInstance(context);
                helper.open();

                Integer i = (helper.fetchMaxIdGruppo())+1;

                helper.deleteGroup("5");

                if(!(nomeGruppo).isEmpty()){
                    helper.createGroup(i.toString(), nomeGruppo, "");
                    for(Integer j : idSelect){
                        helper.createGroupMember(i.toString(), j.toString());
                        System.out.println(i.toString() + " " + j.toString());
                    }
                }

                helper.close();

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
            if(personeSelect.get(s)) {
                nomi.add(s);
                getIdSelect(s);
            }
        }

        return nomi.toArray(new String[0]);

    }

    public void getIdSelect(String nomecognome){
        helper = DbAdapter.getInstance(this);
        helper.open();

        Integer i = helper.getIdPersonaNomeCognome(nomecognome);
        idSelect.add(i);
        System.out.println(nomecognome + " " + i);

    }

}
