package com.example.giovy.iumanji;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class VisualizzaGruppoActivity extends AppCompatActivity {
    ImageButton vaiLocali;
    ImageButton vaiGruppo;
    Button visualizzaSondaggio;
    Button creaSondaggio;
    EditText timer;
    Bundle idGruppo = getIntent().getExtras();
    Integer id = idGruppo.getInt("id gruppo");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_gruppo);

        vaiLocali = (ImageButton) findViewById(R.id.vai_locali_button);
        vaiLocali.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             Intent showGruppo = new Intent(VisualizzaGruppoActivity.this, Locali.class);
                                             startActivity(showGruppo);
                                         }
                                     }

        );
        vaiGruppo = (ImageButton) findViewById(R.id.vai_membri_button);
        vaiGruppo.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             Intent showGruppo = new Intent(VisualizzaGruppoActivity.this, MembriGruppoActivity.class);
                                             startActivity(showGruppo);
                                         }
                                     }

        );

        visualizzaSondaggio = (Button) findViewById(R.id.sondaggio_button);
        visualizzaSondaggio.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             Intent showSondaggio = new Intent(VisualizzaGruppoActivity.this, VisualizzaSondaggioActivity.class);
                                             startActivity(showSondaggio);
                                         }
                                     }

        );

        creaSondaggio = (Button) findViewById(R.id.crea_button);
        creaSondaggio.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View view) {

                                                       Intent showCreaSondaggio = new Intent(VisualizzaGruppoActivity.this,CreaSondaggioActivity.class);
                                                       showCreaSondaggio.putExtra("idGruppo",id);
                                                       startActivity(showCreaSondaggio);
                                                   }
                                               }

        );
        /*MyCountDownTimer myCountDownTimer;
        Bundle datiPassati = getIntent().getExtras();
        final long timerValue = datiPassati.getInt("timerValue");
        timer = (EditText) findViewById(R.id.timer);

        myCountDownTimer = new MyCountDownTimer(timerValue*59000, 1000);
        myCountDownTimer.start();*/
    }
    /*public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisInFuture) {



            long seconds = millisInFuture/1000;
            long remainingSeconds=seconds%(60*60);
            long minutes=remainingSeconds/60;
            remainingSeconds = remainingSeconds%60;
            Toast.makeText(VisualizzaGruppoActivity.this, Thread.currentThread().getName()+"", Toast.LENGTH_LONG).show();


            timer.setText(minutes+" : "+seconds);
        }

        @Override
        public void onFinish() {
            finish();
        }
    }*/
}