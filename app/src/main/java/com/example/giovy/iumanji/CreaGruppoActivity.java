package com.example.giovy.iumanji;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import com.example.giovy.iumanji.database.DbAdapter;
import com.example.giovy.iumanji.database.Persona;

import java.io.Serializable;

public class CreaGruppoActivity extends AppCompatActivity {

    Button avanti_crea_gruppo;
    private RecyclerView recyclerView;
    private CreaGruppoAdapter adapter;
    private DbAdapter helper;
    private Cursor cursor;
    private List<Persona> personaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_gruppo);

        avanti_crea_gruppo = (Button) this.findViewById(R.id.crea_gruppo_avanti_button);

        avanti_crea_gruppo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showCreaGruppoPag2 = new Intent(CreaGruppoActivity.this, CreaGruppoPag2Activity.class);
                startActivity(showCreaGruppoPag2);

            }
        });

        helper = DbAdapter.getInstance(this);
        helper.open();

        cursor=helper.fetchAllPersons();

        while (cursor.moveToNext()) {
            Persona a = new Persona(cursor.getString(1), cursor.getString(2),"","");
            personaList.add(a);
        }
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_crea_gruppo);

        adapter = new CreaGruppoAdapter(this, personaList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


    }



}
