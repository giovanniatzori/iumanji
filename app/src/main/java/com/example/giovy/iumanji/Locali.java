package com.example.giovy.iumanji;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.giovy.iumanji.database.DbAdapter;
import com.example.giovy.iumanji.database.Locale;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Locali extends AppCompatActivity {

    ImageButton aggiungi_locale_button;
    private DbAdapter helper;
    private Cursor cursor;
    private ListView listview;
    private ArrayList<Locale> localeList = new ArrayList<>();
    private TextView nomeGruppo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locali);
        Bundle bundle = getIntent().getExtras();
        Integer id = bundle.getInt("idGruppo");
        String nome = bundle.getString("nomeGruppo");

        nomeGruppo = (TextView) findViewById(R.id.nome_gruppo_locali);
        nomeGruppo.setText(nome);


        aggiungi_locale_button = (ImageButton) findViewById(R.id.aggiungi_locale_button);
        aggiungi_locale_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showCreaGruppo = new Intent(Locali.this, AggiungiLocale.class);
                startActivity(showCreaGruppo);
            }
        });

        listview = (ListView) findViewById(R.id.lista_locali_view);

        helper = DbAdapter.getInstance(this);
        helper.open();

        cursor=helper.fetchGroupLocalsByFilter(id.toString());

        final List<String> listaNomi = new ArrayList<>();
        while (cursor.moveToNext()) {
            Locale l = new Locale(cursor.getString(0), cursor.getString(1));
            l.setId(Integer.parseInt(cursor.getString(2)));
            localeList.add(l);
            listaNomi.add(l.getNome());
        }

        String[] listContent = listaNomi.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listContent);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nomeLocale = (String) listview.getItemAtPosition(position);
                String idLocale;

                Intent showCreaGruppo = new Intent(Locali.this, RiepilogoLocale.class);

                for(Locale l : localeList){
                    if((l.getNome()).equals(nomeLocale)) {
                        idLocale = l.getId().toString();
                        showCreaGruppo.putExtra("idLocale", idLocale);
                        showCreaGruppo.putExtra("nomeLocale", nomeLocale);
                        startActivity(showCreaGruppo);
                    }
                }

            }
        });

    }
}
