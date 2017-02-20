package com.example.giovy.iumanji;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.giovy.iumanji.database.DbAdapter;
import com.example.giovy.iumanji.database.Gruppo;
import com.example.giovy.iumanji.database.Persona;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity {
    Button creareGruppoButton;
    ImageButton vaiGruppo;
    private RecyclerView recyclerView;
    private MainMenuAdapter menuAdapter;
    private DbAdapter helper;
    private Cursor cursor;
    private List<Gruppo> groupList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //RESET DATABASE
        //getApplicationContext().deleteDatabase("iumangiDb1.db");

        creareGruppoButton = (Button) this.findViewById(R.id.creare_gruppo_button);

        creareGruppoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showCreaGruppo = new Intent(MainMenu.this, CreaGruppoActivity.class);

                startActivity(showCreaGruppo);
            }
        });

        helper = DbAdapter.getInstance(this);
        helper.open();
        //helper.updatePietanza("14", "cheesecake", "3.50");

        cursor=helper.fetchAllGroups();

        while (cursor.moveToNext()) {
            Gruppo a = new Gruppo(cursor.getString(1));
            a.setImmagine(cursor.getString(2));
            a.setId(cursor.getInt(0));
            groupList.add(a);
        }
        helper.close();
        cursor.close();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_gruppi);

        menuAdapter = new MainMenuAdapter(this, groupList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(menuAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch(id)
        {
            case R.id.MENU_1_inviti:
                Intent showInviti = new Intent(MainMenu.this, InvitiActivity.class);
                startActivity(showInviti);
                break;
            case R.id.MENU_2_account:
                Intent showAccount = new Intent(MainMenu.this, AccountActivity.class);
                startActivity(showAccount);
                break;
            case R.id.MENU_3_logout:
                Intent showLogin = new Intent(MainMenu.this, LoginActivity.class);
                startActivity(showLogin);
                break;
        }
        return false;
    }
}