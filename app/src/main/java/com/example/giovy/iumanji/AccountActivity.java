package com.example.giovy.iumanji;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;



import java.util.ArrayList;
import java.util.List;

import com.example.giovy.iumanji.database.Persona;

import java.io.Serializable;

public class AccountActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AccountAdapter adapter;
    private List<Persona> personaList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_account);

        personaList = new ArrayList<>();
        adapter = new AccountAdapter(this, personaList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        preparePersona();
    }

    private void preparePersona() {

        Persona a = new Persona("Giovanni", "Atzori", "gio83.atzo@libero.it","12345");
        personaList.add(a);

        a = new Persona("Martina","Senis","mart@gmail.com","54321");
        personaList.add(a);

        adapter.notifyDataSetChanged();
    }


}
