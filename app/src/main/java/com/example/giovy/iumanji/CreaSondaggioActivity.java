package com.example.giovy.iumanji;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.giovy.iumanji.database.DbAdapter;
import com.example.giovy.iumanji.database.Locale;

import org.w3c.dom.Text;


public class CreaSondaggioActivity extends AppCompatActivity {
    private Boolean flag = false;
    private Button crea_sondaggio;
    private DbAdapter helper;
    private Cursor cursor;
    private EditText timerInput;
    private Integer idGruppo;
    private List<Locale> localeList = new ArrayList<>();
    private ListView listview;
    private long timer;
    private String nomeGruppo;
    private TextView nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_sondaggio);

        Bundle bundle = getIntent().getExtras();
        idGruppo = bundle.getInt("idGruppo");
        nomeGruppo = bundle.getString("nomeGruppo");
        timerInput = (EditText) findViewById(R.id.cronometro_crea_sondaggio);

        nome = (TextView) findViewById(R.id.nome_gruppo_crea_sondaggio);
        nome.setText(nomeGruppo);

        crea_sondaggio = (Button) findViewById(R.id.crea_sondaggio_button) ;
        crea_sondaggio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent showMainMenu = new Intent(CreaSondaggioActivity.this, VisualizzaGruppoActivity.class);

            String t = timerInput.getText().toString();
            if(!(t.isEmpty()) && flag) {
                timer = Long.parseLong(t);
                showMainMenu.putExtra("timerValue", timer);
                showMainMenu.putExtra("nomeGruppo", nomeGruppo);
                showMainMenu.putExtra("idGruppo", idGruppo);
                startActivity(showMainMenu);
            } else {
                if (t.isEmpty()) timerInput.setError("Campo obbligatorio");
                if (!(flag)) {
                    TextView error = (TextView) findViewById(R.id.errore_sondaggio);
                    error.setVisibility(View.VISIBLE);
                }
            }
            }
        });

        listview = (ListView) findViewById(R.id.lista_locali_crea_sondaggio);

        helper = DbAdapter.getInstance(this);
        helper.open();

        cursor=helper.fetchGroupLocalsByFilter(idGruppo.toString());

        final List<String> listaNomi = new ArrayList<>();
        while (cursor.moveToNext()) {
            Locale l = new Locale(cursor.getString(0), cursor.getString(1));

            localeList.add(l);
            listaNomi.add(l.getNome());
            }

        helper.close();
        cursor.close();
        String[] listContent = listaNomi.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_single_choice,
                listContent);

        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                flag = true;
            }
        });

    }

}
