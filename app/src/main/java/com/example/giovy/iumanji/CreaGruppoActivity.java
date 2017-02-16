package com.example.giovy.iumanji;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.giovy.iumanji.database.DbAdapter;
import com.example.giovy.iumanji.database.Persona;

import java.io.Serializable;

public class CreaGruppoActivity extends AppCompatActivity {

    Button avanti_crea_gruppo;
    private DbAdapter helper;
    private Cursor cursor;
    private List<Persona> personaList = new ArrayList<>();
    private ListView listview;
    private HashMap<String, Boolean> personeSelect = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_gruppo);

        avanti_crea_gruppo = (Button) this.findViewById(R.id.crea_gruppo_avanti_button);

        avanti_crea_gruppo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showCreaGruppoPag2 = new Intent(CreaGruppoActivity.this, CreaGruppoPag2Activity.class);
                showCreaGruppoPag2.putExtra("personeSelect", (Serializable) personeSelect);
                startActivity(showCreaGruppoPag2);

            }
        });

        listview = (ListView) findViewById(R.id.lista_persone_crea_gruppo);
        helper = DbAdapter.getInstance(this);
        helper.open();

        cursor=helper.fetchAllPersons2();
        final List<String> listaNomi = new ArrayList<>();
        while (cursor.moveToNext()) {
            Persona a = new Persona(cursor.getString(1), cursor.getString(2),"","");
            a.setId(cursor.getInt(0));
            personaList.add(a);
            listaNomi.add(a.getNome() + " " + a.getCognome());
        }

        String[] listContent = listaNomi.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice,
                listContent);

        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nome = (String) listview.getItemAtPosition(position);

                if(personeSelect.containsKey(nome)){
                    if(personeSelect.get(nome))
                        personeSelect.put(nome, false);
                    else
                        personeSelect.put(nome, true);
                } else {
                    personeSelect.put(nome, true);
                }
            }
        });

    }

}
