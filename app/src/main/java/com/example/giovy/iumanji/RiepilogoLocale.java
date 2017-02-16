package com.example.giovy.iumanji;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.giovy.iumanji.database.DbAdapter;
import com.example.giovy.iumanji.database.Locale;
import com.example.giovy.iumanji.database.Persona;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RiepilogoLocale extends AppCompatActivity {

    private ListView listview;
    private String nomeLocale;
    private String idLocale;
    private TextView nome;
    private DbAdapter helper;
    private Cursor cursor;
    private static ArrayList<String> nuovePietanze = new ArrayList<>();
    ImageButton aggiungi_pietanza_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riepilogo_locale);

        Bundle bundle = getIntent().getExtras();
        nomeLocale = bundle.getString("nomeLocale");
        idLocale = bundle.getString("idLocale");

        nome = (TextView) findViewById(R.id.nomeLocale1);
        nome.setText(nomeLocale);
        nome = (TextView) findViewById(R.id.nomeLocale2);
        nome.setText(nomeLocale);

        listview = (ListView) findViewById(R.id.lista_pietanze_view);

        String[] listContent = getPietanze();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listContent);

        listview.setAdapter(adapter);

        aggiungi_pietanza_button = (ImageButton) findViewById(R.id.aggiungi_pietanza_button);
        aggiungi_pietanza_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomePietanza = ((EditText) findViewById(R.id.nome_pietanza)).getText().toString();
                String prezzoPietanza = ((EditText) findViewById(R.id.prezzo_pietanza)).getText().toString();

                Context context = getApplicationContext();

                helper = DbAdapter.getInstance(context);
                helper.open();

                Integer i = (helper.fetchMaxIdPietanza())+1;

                if(check(nomePietanza, prezzoPietanza)) {
                    helper.createPietanza(i.toString(), nomePietanza, prezzoPietanza);
                    helper.createlocaliPietanze(idLocale, i.toString());
                }

                helper.close();

                Intent intent = getIntent();
                finish();
                startActivity(intent);

            }
        });

    }

    public String[] getPietanze (){
        ArrayList<String> lista = new ArrayList<>();

        helper = DbAdapter.getInstance(this);
        helper.open();

        cursor=helper.fetchLocaliPietanzesByFilter(idLocale);

        while (cursor.moveToNext()) {
            String locale = cursor.getString(0) + " - " + cursor.getString(1) + " €";
            lista.add(locale);
        }

        helper.close();

        return lista.toArray(new String[0]);
    }

    public Boolean check(String nome, String prezzo){
        return !(nome.isEmpty() || prezzo.isEmpty() || prezzo.equals("Prezzo") || nome.equals("Nome Pietanza"));
    }
}
