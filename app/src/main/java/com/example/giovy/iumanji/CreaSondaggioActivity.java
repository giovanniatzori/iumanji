package com.example.giovy.iumanji;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.example.giovy.iumanji.database.DbAdapter;
import com.example.giovy.iumanji.database.Locale;


public class CreaSondaggioActivity extends AppCompatActivity {
    Button crea_sondaggio;
    private DbAdapter helper;
    private Cursor cursor;
    private final List<Locale> localeList = new ArrayList<>();
    private ListView listview;
    private EditText timerInput;
    private long timer;
    Button creasondaggio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_sondaggio);
        Bundle idGruppo = getIntent().getExtras();
        Integer id = idGruppo.getInt("idGruppo");
        timerInput = (EditText) findViewById(R.id.cronometro_crea_sondaggio);

        crea_sondaggio = (Button) findViewById(R.id.crea_sondaggio_button) ;
        crea_sondaggio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showMainMenu = new Intent(CreaSondaggioActivity.this, VisualizzaGruppoActivity.class);
                timer = Long.parseLong(timerInput.getText().toString());
                showMainMenu.putExtra("timerValue", timer);

                startActivity(showMainMenu);

            }
        });

        listview = (ListView) findViewById(R.id.lista_locali_crea_sondaggio);

        helper = DbAdapter.getInstance(this);
        helper.open();

        cursor=helper.fetchGroupLocalsByFilter(id.toString());
        //cursor=helper.fetchAllLocals();


        final List<String> listaNomi = new ArrayList<>();
        while (cursor.moveToNext()) {
            Locale l = new Locale(cursor.getString(0), cursor.getString(1));

            localeList.add(l);
            listaNomi.add(l.getNome());
            }


        String[] listContent = listaNomi.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_single_choice,
                listContent);

        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listview.setAdapter(adapter);

        }

}
