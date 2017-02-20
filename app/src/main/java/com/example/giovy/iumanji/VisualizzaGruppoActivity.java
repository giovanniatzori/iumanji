package com.example.giovy.iumanji;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giovy.iumanji.database.DbAdapter;

public class VisualizzaGruppoActivity extends AppCompatActivity {

    private ImageButton vaiLocali;
    private ImageButton vaiGruppo;
    private Button visualizzaSondaggio;
    private Button creaSondaggio;
    private TextView timer;
    private TextView nome;
    //private TextView abbandona;
    private Bundle bundle;
    private DbAdapter helper;
    private Cursor cursor;
    private Integer idGruppo;
    private Bundle timerPassato;
    private long timerValue = 0 ;
    private String nomeGruppo;
    private boolean creaTasto = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bundle = getIntent().getExtras();
        idGruppo = bundle.getInt("idGruppo");
        nomeGruppo = bundle.getString("nomeGruppo");
        timerValue = bundle.getLong("timerValue");

        if (bundle.containsKey("ciao")){
            creaTasto = bundle.getBoolean("ciao");
        }

        MyCountDownTimer myCountDownTimer;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizza_gruppo);

        nome = (TextView) findViewById(R.id.nome_gruppo);
        nome.setText(nomeGruppo);

       // abbandona = (TextView) findViewById(R.id.abbandona_gruppo);

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
        visualizzaSondaggio.setBackgroundResource(R.drawable.griggio_button);
        visualizzaSondaggio.setEnabled(false);

        creaSondaggio = (Button) findViewById(R.id.crea_button);

        if (creaTasto){creaSondaggio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showCreaSondaggio = new Intent(VisualizzaGruppoActivity.this,CreaSondaggioActivity.class);
                showCreaSondaggio.putExtra("idGruppo",idGruppo);
                showCreaSondaggio.putExtra("nomeGruppo", nomeGruppo);
                startActivity(showCreaSondaggio);
            }
        });
        } else {creaSondaggio.setEnabled(false);}

       /* abbandona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abbandona.setPaintFlags(abbandona.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                helper = DbAdapter.getInstance(getApplicationContext());
                helper.open();
                helper.deleteGroup(idGruppo.toString());
                helper.close();

                Intent showCreaSondaggio = new Intent(VisualizzaGruppoActivity.this,MainMenu.class);
                showCreaSondaggio.putExtra("idGruppo",idGruppo);
                showCreaSondaggio.putExtra("nomeGruppo", nomeGruppo);
                startActivity(showCreaSondaggio);
            }
        });*/

        if( timerValue != 0) {
            creaSondaggio.setBackgroundResource(R.drawable.griggio_button);
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
                if(minutes < 1 && seconds <20) {
                    timer.setTextColor(Color.parseColor("#FF0000"));
                }
                Toast.makeText(VisualizzaGruppoActivity.this, Thread.currentThread().getName()+"", Toast.LENGTH_SHORT);

                if(minutes<10) {
                    if(seconds < 10 ){
                        timer.setText("0" + minutes + " : " + "0"+remainingSeconds);
                    } else {
                        timer.setText("0" + minutes + " : " + remainingSeconds);
                    }
                } else {
                    if(seconds < 10 ){
                        timer.setText(minutes + " : " + "0"+remainingSeconds);
                    } else {
                        timer.setText(minutes + " : " + remainingSeconds);
                    }
                }

            }

            @Override
            public void onFinish() {
                timer.setText("00:00");
                visualizzaSondaggio.setEnabled(true);
                visualizzaSondaggio.setBackgroundResource(R.drawable.altro_rutton);
                //creaSondaggio.setEnabled(true);
                //creaSondaggio.setBackgroundResource(R.drawable.altro_rutton);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_gruppo,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch(id)
        {
            case R.id.menu_gruppo_archivio:
                Intent cronologia = new Intent(VisualizzaGruppoActivity.this, CronologiaSondaggi.class);
                startActivity(cronologia);
                break;
            case R.id.menu_gruppo_abbandona:
                final Dialog dialog = new Dialog(VisualizzaGruppoActivity.this);

                /*// Evito la presenza della barra del titolo nella mia dialog
                dialog.getWindow();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);*/

                // Carico il layout della dialog al suo intenro
                dialog.setContentView(R.layout.conferma);

                // Nel caso fosse previsto un titolo questo sarebbe il codice da
                // utilizzare eliminando quello visto poco sopra per evitarlo
                dialog.setTitle("Vuoi davvero abbandonare il gruppo?");

                dialog.setCancelable(true);

                // Qui potrei aggiungere eventuali altre impostazioni per la dialog
                // ...

                //Gestisco il bottone di chiusura della dialog (quello in alto a destra)
                Button imgclose = (Button) dialog.findViewById(R.id.no_conferma);
                imgclose.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                /*//Gestisco il bottone, della dialog, di apertura della schermata per la chiamata
                final ImageButton button_dialog_to_call = (ImageButton) dialog.findViewById(R.id.DialogToCall01);
                button_dialog_to_call.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        // Funzioni varie
                    }
                });*/
                dialog.show();
                //Gestisco il bottone, della dialog, per l'apertura dell'applicazione nativa di messaggistica per rispondere all'SMS selezionato
                final Button button_dialog_reply = (Button) dialog.findViewById(R.id.si_conferma);
                button_dialog_reply.setOnClickListener(new View.OnClickListener() {
                                                           public void onClick(View v) {
                                                               helper = DbAdapter.getInstance(getApplicationContext());
                                                               helper.open();
                                                               helper.deleteGroup(idGruppo.toString());
                                                               helper.close();

                                                               Intent showCreaSondaggio = new Intent(VisualizzaGruppoActivity.this, MainMenu.class);
                                                               showCreaSondaggio.putExtra("idGruppo", idGruppo);
                                                               showCreaSondaggio.putExtra("nomeGruppo", nomeGruppo);
                                                               startActivity(showCreaSondaggio);
                                                           }
                                                           });

                                                           // Faccio comparire la dialog
                break;
                                                       }
        return false;

                    }




    }

