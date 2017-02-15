package com.example.giovy.iumanji;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.giovy.iumanji.database.DbAdapter;
import com.example.giovy.iumanji.database.Locale;


public class CreaSondaggioActivity extends AppCompatActivity {
    Button crea_sondaggio;
    private RecyclerView recyclerView;
    private CreaSondaggioAdapter adapter;
    private DbAdapter helper;
    private Cursor cursor;
    private List<Locale> localeList = new ArrayList<>();
    private EditText timerInput;
    private long timer;


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

        helper = DbAdapter.getInstance(this);
        helper.open();
        System.out.println(id.toString() +"qui");
        cursor=helper.fetchGroupLocalsByFilter(id.toString());


        while (cursor.moveToNext()) {
            Locale l = new Locale(cursor.getString(0), cursor.getString(1));
            localeList.add(l);
            System.out.println(cursor.getString(0)+ " " +cursor.getString(1));
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_crea_sondaggio);

        adapter = new CreaSondaggioAdapter(this, localeList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}
