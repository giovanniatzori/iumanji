package com.example.giovy.iumanji;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.giovy.iumanji.database.DbAdapter;
import com.example.giovy.iumanji.database.Locale;

public class CronologiaSondaggi extends AppCompatActivity {

    private LinearLayout locale1;
    private LinearLayout locale2;
    private LinearLayout locale3;
    private Bundle bundle;
    //private Integer idGruppo;
    private String nomeGruppo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronologia_sondaggi);

        bundle = getIntent().getExtras();
        //idGruppo = bundle.getInt("idGruppo");
        nomeGruppo = bundle.getString("nomeGruppo");

        locale1 = (LinearLayout) findViewById(R.id.cronologia1);

        locale1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showRiepilogoCrono = new Intent(CronologiaSondaggi.this,RipilogoCronologia.class);
                showRiepilogoCrono.putExtra("idLocale", 8);
                showRiepilogoCrono.putExtra("nomeGruppo", nomeGruppo);
                startActivity(showRiepilogoCrono );
            }
        });
        locale2 = (LinearLayout) findViewById(R.id.cronologia2);

        locale2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showRiepilogoCrono = new Intent(CronologiaSondaggi.this,RipilogoCronologia.class);
                showRiepilogoCrono.putExtra("idLocale", 1);
                showRiepilogoCrono.putExtra("nomeGruppo", nomeGruppo);
                startActivity(showRiepilogoCrono );
            }
        });
        locale3 = (LinearLayout) findViewById(R.id.cronologia2);

        locale3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showRiepilogoCrono = new Intent(CronologiaSondaggi.this,RiepilogoActivity.class);
                showRiepilogoCrono.putExtra("idLocale", 2);
                showRiepilogoCrono.putExtra("nomeGruppo", nomeGruppo);
                startActivity(showRiepilogoCrono );
            }
        });
    }
}
