package com.example.giovy.iumanji;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
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
    private Double somma = 0.00;
    private TextView textviewSomma;
    private TextView nomeGruppoText;
    private Button tornaHome;
    private boolean ciao = true;
    private Bundle bundle;
    private Integer idGruppo;
    private String nomeGruppo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riepilogo);

        bundle = getIntent().getExtras();
        idGruppo = bundle.getInt("idGruppo");
        nomeGruppo = bundle.getString("nomeGruppo");

        tornaHome = (Button) findViewById(R.id.torna_home_button);
        nomeGruppoText = (TextView) findViewById(R.id.NomeLocaleRiepilogo);
        listview_nome = (ListView) findViewById(R.id.nome_pietanza_listview);
        textviewSomma = (TextView) findViewById(R.id.somma_ordine);
        ArrayList<Pietanza> pietanzeScelte = (ArrayList<Pietanza>) getIntent().getSerializableExtra("listaPietanze");

        nomeGruppoText.setText(nomeGruppo);

        tornaHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showHome = new Intent(RiepilogoActivity.this, VisualizzaGruppoActivity.class);
                showHome.putExtra("ciao", ciao);
                showHome.putExtra("idGruppo", idGruppo);
                showHome.putExtra("nomeGruppo", nomeGruppo);
                startActivity(showHome);
            }
        });

        helper = DbAdapter.getInstance(this);
        helper.open();

        cursor = helper.fetchLocaliPietanzesByFilter("3");

        Boolean isPresent;
        while (cursor.moveToNext()) {
            Pietanza p = new Pietanza(cursor.getString(0), cursor.getDouble(1));
            isPresent = false;

            for (Pietanza pieta : pietanzeScelte) {
                if (pieta.getNome().equals(cursor.getString(0)) && pieta.getQuantita() != 0) {
                    isPresent = true;
                    pietanzaList.add(pieta);
                    somma = somma + (pieta.getPrezzo() * pieta.getQuantita());
                    break;
                }
            }

            if (!isPresent) {
                p.setQuantita(1);
                pietanzaList.add(p);
                somma = somma + p.getPrezzo();
            }

        }
        RiepilogoAdapter adapter2 = new RiepilogoAdapter(this, pietanzaList);
        helper.close();
        cursor.close();

        listview_nome.setAdapter(adapter2);

        textviewSomma.setText("Costo totale: " + somma + " €");

        listview_nome.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
