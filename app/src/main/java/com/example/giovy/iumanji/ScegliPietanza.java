package com.example.giovy.iumanji;

import android.content.Intent;
import android.database.Cursor;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giovy.iumanji.database.DbAdapter;
import com.example.giovy.iumanji.database.Locale;
import com.example.giovy.iumanji.database.Pietanza;

import java.util.ArrayList;
import java.util.List;

public class ScegliPietanza extends AppCompatActivity {

    TextView cronometro;
    long timer = 1;
    ListView listview;
    DbAdapter helper;
    Cursor cursor;
    Integer id = 3;
    private final List<Pietanza> pietanzeleList = new ArrayList<>();
    ImageButton aggiungi1a;
    ImageButton sottrai1a;
    ImageButton aggiungi2a;
    ImageButton sottrai2a;
    ImageButton aggiungi3a;
    ImageButton sottrai3a;
    ImageButton aggiungi4a;
    ImageButton sottrai4a;
    ImageButton aggiungi5a;
    ImageButton sottrai5a;
    ImageButton aggiungi6a;
    ImageButton sottrai6a;
    ImageButton aggiungi7a;
    ImageButton sottrai7a;
    TextView prezzo1a;
    TextView prezzo2a;
    TextView prezzo3a;
    TextView prezzo4a;
    TextView prezzo5a;
    TextView prezzo6a;
    TextView prezzo7a;
    TextView tot;
    Double pz1;
    Double pz2;
    Double pz3;
    Double pz4;
    Double pz5;
    Double pz6;
    Double pz7;
    String prezzoTotale = "Prezzo totale € ";

    Double prezzoTot = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scegli_pietanza);
        cronometro = (TextView) findViewById(R.id.chronometer3);
        MyCountDownTimer myCountDownTimer;

        myCountDownTimer = new MyCountDownTimer(timer * 59000, 1000);
        myCountDownTimer.start();
        tot = (TextView) findViewById(R.id.textView7);
        prezzo1a = (TextView) findViewById(R.id.prezzo1);
        prezzo2a = (TextView) findViewById(R.id.prezzo2);
        prezzo3a = (TextView) findViewById(R.id.prezzo3);
        prezzo4a = (TextView) findViewById(R.id.prezzo4);
        prezzo5a = (TextView) findViewById(R.id.prezzo5);
        prezzo6a = (TextView) findViewById(R.id.prezzo6);
        prezzo7a = (TextView) findViewById(R.id.prezzo7);
        System.out.println(prezzo1a.getText().toString());
        pz1=Double.parseDouble(prezzo1a.getText().toString());
        pz2=Double.parseDouble(prezzo2a.getText().toString());
        pz3=Double.parseDouble(prezzo3a.getText().toString());
        pz4=Double.parseDouble(prezzo4a.getText().toString());
        pz5=Double.parseDouble(prezzo5a.getText().toString());
        pz6=Double.parseDouble(prezzo6a.getText().toString());
        pz7=Double.parseDouble(prezzo7a.getText().toString());

        aggiungi1a = (ImageButton) findViewById(R.id.aggiungi1);
        sottrai1a = (ImageButton) findViewById(R.id.sottrai1);
        aggiungi2a = (ImageButton) findViewById(R.id.aggiungi2);
        sottrai2a = (ImageButton) findViewById(R.id.sottrai2);
        aggiungi3a = (ImageButton) findViewById(R.id.aggiungi3);
        sottrai3a = (ImageButton) findViewById(R.id.sottrai3);
        aggiungi4a = (ImageButton) findViewById(R.id.aggiungi4);
        sottrai4a = (ImageButton) findViewById(R.id.sottrai4);
        aggiungi5a = (ImageButton) findViewById(R.id.aggiungi5);
        sottrai5a = (ImageButton) findViewById(R.id.sottrai5);
        aggiungi6a = (ImageButton) findViewById(R.id.aggiungi6);
        sottrai6a = (ImageButton) findViewById(R.id.sottrai6);
        aggiungi7a = (ImageButton) findViewById(R.id.aggiungi7);
        sottrai7a = (ImageButton) findViewById(R.id.sottrai7);
        aggiungi1a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prezzoTot += Double.parseDouble(prezzo1a.getText().toString());
                tot.setText(prezzoTotale + prezzoTot.toString());
            }
        });
        sottrai1a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prezzoTot > 0) {prezzoTot -= Double.parseDouble(prezzo1a.getText().toString());}
                tot.setText(prezzoTotale + prezzoTot.toString());
            }
        });
        aggiungi2a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prezzoTot += Double.parseDouble(prezzo2a.getText().toString());
                tot.setText(prezzoTotale + prezzoTot.toString());
            }
        });
        sottrai2a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prezzoTot > 0) {prezzoTot -= Double.parseDouble(prezzo2a.getText().toString());}
                tot.setText(prezzoTotale + prezzoTot.toString());
            }
        });
        aggiungi3a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prezzoTot += Double.parseDouble(prezzo3a.getText().toString());
                tot.setText(prezzoTotale + prezzoTot.toString());
            }
        });
        sottrai3a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prezzoTot > 0) {prezzoTot -= Double.parseDouble(prezzo3a.getText().toString());}
                tot.setText(prezzoTotale + prezzoTot.toString());
            }
        });
        aggiungi4a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prezzoTot += Double.parseDouble(prezzo4a.getText().toString());
                tot.setText(prezzoTotale + prezzoTot.toString());
            }
        });
        sottrai4a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prezzoTot > 0) {prezzoTot -= Double.parseDouble(prezzo4a.getText().toString());}
                tot.setText(prezzoTotale + prezzoTot.toString());
            }
        });
        aggiungi5a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prezzoTot += Double.parseDouble(prezzo5a.getText().toString());
                tot.setText(prezzoTotale + prezzoTot.toString());
            }
        });
        sottrai5a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prezzoTot > 0) {prezzoTot -= Double.parseDouble(prezzo5a.getText().toString());}
                tot.setText(prezzoTotale + prezzoTot.toString());
            }
        });
        aggiungi6a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prezzoTot += Double.parseDouble(prezzo6a.getText().toString());
                tot.setText(prezzoTotale + prezzoTot.toString());
            }
        });
        sottrai6a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prezzoTot > 0) {prezzoTot -= Double.parseDouble(prezzo6a.getText().toString());}
                tot.setText(prezzoTotale + prezzoTot.toString());
            }
        });
        aggiungi7a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prezzoTot += Double.parseDouble(prezzo7a.getText().toString());
                tot.setText(prezzoTotale + prezzoTot.toString());
            }
        });
        sottrai7a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prezzoTot > 0) {prezzoTot -= Double.parseDouble(prezzo7a.getText().toString());}
                tot.setText(prezzoTotale + prezzoTot.toString());
            }
        });




        //listview = (ListView) findViewById(R.id.lista_pietanze);

        //helper = DbAdapter.getInstance(this);
        //helper.open();

        //cursor=helper.fetchLocaliPietanzesByFilter(id.toString());

        /*final List<String> listaNomi = new ArrayList<>();
        while (cursor.moveToNext()) {
            Pietanza l = new Pietanza(cursor.getString(0), cursor.getDouble(1));
            System.out.println(cursor.getString(0));
            pietanzeleList.add(l);
            listaNomi.add(l.getPrezzo().toString() +" €     " + " " + l.getNome() );
        }*/


        //String[] listContent = listaNomi.toArray(new String[0]);
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                //android.R.layout.simple_list_item_1,
                //listContent);

        //listview.setAdapter(adapter);


    }


    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisInFuture) {
            long seconds = millisInFuture / 1000;
            long remainingSeconds = seconds % (60);
            long minutes = remainingSeconds / 60;
            remainingSeconds = remainingSeconds % 60;
            Toast.makeText(ScegliPietanza.this, Thread.currentThread().getName() + "", Toast.LENGTH_LONG).show();

            cronometro.setText(minutes + " : " + seconds);
        }

        @Override
        public void onFinish() {

            Intent showSondaggio = new Intent(ScegliPietanza.this, RiepilogoActivity.class);
            startActivity(showSondaggio);
        }
    }
}


