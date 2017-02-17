package com.example.giovy.iumanji;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.giovy.iumanji.database.DbAdapter;
import com.example.giovy.iumanji.database.Persona;
import com.example.giovy.iumanji.database.Pietanza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RiepilogoActivity extends AppCompatActivity {

    private DbAdapter helper;
    private Cursor cursor;
    private List<Pietanza> pietanzaList = new ArrayList<>();
    private ListView listview_nome;
    private Double somma =0.00;
    private TextView textviewSomma;
    private TextView nomeGruppo;
    Button tornaHome;
    boolean ciao = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riepilogo);
        tornaHome = (Button) findViewById(R.id.torna_home_button) ;
        listview_nome = (ListView) findViewById(R.id.nome_pietanza_listview);
        textviewSomma =(TextView)  findViewById(R.id.somma_ordine);
        //Bundle sgnaffoli = getIntent().getExtras();
        //String nome = sgnaffoli.getString("nomeGruppo");

        //nomeGruppo = (TextView) findViewById(R.id.nome_gruppo_membri);
        //nomeGruppo.setText(nome);

        tornaHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showHome = new Intent(RiepilogoActivity.this, VisualizzaGruppoActivity.class);
                showHome.putExtra("ciao", ciao);
                startActivity(showHome);

            }
        });

        helper = DbAdapter.getInstance(this);
        helper.open();


        cursor=helper.fetchLocaliPietanzesByFilter("3");
        //final List<String> listaNomi = new ArrayList<>();


        while (cursor.moveToNext()) {
            Pietanza p = new Pietanza(cursor.getString(0), cursor.getDouble(1));
            //p.setId(cursor.getInt(0));
            pietanzaList.add(p);
            somma=somma+p.getPrezzo();
            System.out.println(somma);
        }
        RiepilogoAdapter adapter2 = new RiepilogoAdapter(this,pietanzaList);
        helper.close();
        cursor.close();

        listview_nome.setAdapter(adapter2);

        textviewSomma.setText("Costo totale: " + somma + "0€");
        /*String[] listContent = listaNomi.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_multiple_choice,
                listContent);*/



        listview_nome.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //listview_nome.setAdapter(adapter);
    }



}
