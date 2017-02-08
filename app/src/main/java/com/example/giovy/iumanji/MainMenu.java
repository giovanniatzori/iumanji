package com.example.giovy.iumanji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainMenu extends AppCompatActivity {
    Button creaGruppoButton;
    ImageButton vaiGruppo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        creaGruppoButton = (Button) findViewById(R.id.crea_gruppo_button);
        creaGruppoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showCreaGruppo = new Intent(MainMenu.this, CreaGruppoActivity.class);
                startActivity(showCreaGruppo);


            }
        });

        vaiGruppo = (ImageButton) findViewById(R.id.vai_locale_button);
        vaiGruppo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showGruppo = new Intent(MainMenu.this, VisualizzaGruppoActivity.class);
                startActivity(showGruppo);
                                         }
                                     }

        );


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
