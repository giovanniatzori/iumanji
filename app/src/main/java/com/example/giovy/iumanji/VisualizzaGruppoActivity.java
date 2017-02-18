package com.example.giovy.iumanji;

import android.content.Intent;
import android.database.Cursor;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giovy.iumanji.database.DbAdapter;

public class VisualizzaGruppoActivity extends AppCompatActivity {
    ImageButton vaiLocali;
    ImageButton vaiGruppo;
    Button visualizzaSondaggio;
    Button creaSondaggio;
    TextView timer;
    TextView nome;
    Bundle bundle;
    private DbAdapter helper;
    private Cursor cursor;
    Integer idGruppo;
    Bundle timerPassato;
    long timerValue = 0 ;
    String nomeGruppo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bundle = getIntent().getExtras();
        idGruppo = bundle.getInt("idGruppo");
        nomeGruppo = bundle.getString("nomeGruppo");
        timerValue = bundle.getLong("timerValue");

        MyCountDownTimer myCountDownTimer;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_gruppo);


        nome = (TextView) findViewById(R.id.nome_gruppo);
        nome.setText(nomeGruppo);

        vaiLocali = (ImageButton) findViewById(R.id.localiGruppoButton);
        vaiLocali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showGruppo = new Intent(VisualizzaGruppoActivity.this, Locali.class);
                showGruppo.putExtra("idGruppo", idGruppo);
                showGruppo.putExtra("nomeGruppo", nomeGruppo);
                startActivity(showGruppo);
            }
        });

        vaiGruppo = (ImageButton) findViewById(R.id.vai_membri_button);
        vaiGruppo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showGruppo = new Intent(VisualizzaGruppoActivity.this, MembriGruppoActivity.class);
                showGruppo.putExtra("idGruppo", idGruppo);
                showGruppo.putExtra("nomeGruppo", nomeGruppo);
                startActivity(showGruppo);
            }
        });

        visualizzaSondaggio = (Button) findViewById(R.id.sondaggio_button);
        visualizzaSondaggio.setBackgroundColor(0xFFE3E3E3);
        visualizzaSondaggio.setEnabled(false);

        creaSondaggio = (Button) findViewById(R.id.crea_button);
        creaSondaggio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showCreaSondaggio = new Intent(VisualizzaGruppoActivity.this,CreaSondaggioActivity.class);
                showCreaSondaggio.putExtra("idGruppo",idGruppo);
                showCreaSondaggio.putExtra("nomeGruppo", nomeGruppo);
                startActivity(showCreaSondaggio);
            }
        });

        if( timerValue != 0) {
            creaSondaggio.setBackgroundColor(0xFFE3E3E3);
            creaSondaggio.setEnabled(false);
            timer = (TextView) findViewById(R.id.timer);
            myCountDownTimer = new MyCountDownTimer(timerValue * 60000, 1000);
            myCountDownTimer.start();
        } else {timerValue = 0;}
    }


    public class MyCountDownTimer extends CountDownTimer {

            public MyCountDownTimer(long millisInFuture, long countDownInterval) {
                super(millisInFuture, countDownInterval);
            }

            @Override
            public void onTick(long millisInFuture) {

                long seconds = millisInFuture/1000;
                long remainingSeconds=seconds%(60);
                long minutes=seconds/60;
                Toast.makeText(VisualizzaGruppoActivity.this, Thread.currentThread().getName()+"", Toast.LENGTH_LONG).show();

                timer.setText(minutes+" : "+remainingSeconds);
            }

            @Override
            public void onFinish() {
                timer.setText("00:00");
                visualizzaSondaggio.setEnabled(true);
                visualizzaSondaggio.setBackgroundColor(0xFF009966);
                creaSondaggio.setEnabled(true);
                creaSondaggio.setBackgroundColor(0xFF009966);
                visualizzaSondaggio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent showSondaggio = new Intent(VisualizzaGruppoActivity.this, RisultatoSondaggioActivity.class);
                        showSondaggio.putExtra("idGruppo",idGruppo);
                        showSondaggio.putExtra("nomeGruppo", nomeGruppo);
                        startActivity(showSondaggio);
                    }
                });
                //Intent showSondaggio = new Intent(VisualizzaGruppoActivity.this, RisultatoSondaggioActivity.class);
                //startActivity(showSondaggio);
            }
        }
    }
