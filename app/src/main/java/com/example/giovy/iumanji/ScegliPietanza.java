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
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.giovy.iumanji.database.DbAdapter;
import com.example.giovy.iumanji.database.Locale;
import com.example.giovy.iumanji.database.Pietanza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ScegliPietanza extends AppCompatActivity {

    private TextView cronometro;
    private long timer = 1;
    private ListView listview;
    Button bottone_conferma;
    private Integer id = 3;
    private TextView tot;
    private final List<Pietanza> pietanzeleList = new ArrayList<>();
    ListView griglia;
    String prezzoTotale = "Prezzo totale € ";

    Double prezzoTot = 0.0;
    //private ScegliPietanzaAdapter adapter = new ScegliPietanzaAdapter(this,generaPietanze());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scegli_pietanza);
        griglia = (ListView) findViewById(R.id.grigliaPietanze);
        cronometro = (TextView) findViewById(R.id.chronometer3);
        MyCountDownTimer myCountDownTimer;
        myCountDownTimer = new MyCountDownTimer(timer * 59000, 1000);
        myCountDownTimer.start();

        bottone_conferma = (Button) findViewById(R.id.conferma_button) ;

        ArrayList<Pietanza> tanza = new ArrayList<Pietanza>();
        DbAdapter helper;
        Cursor cursor;
        helper = DbAdapter.getInstance(this);
        helper.open();

        cursor=helper.fetchLocaliPietanzesByFilter(id.toString());

        while (cursor.moveToNext()) {
            Pietanza l = new Pietanza(cursor.getString(0), cursor.getDouble(1));
            tanza.add(l);
        }
        helper.close();
        cursor.close();
        ScegliPietanzaAdapter adapter = new ScegliPietanzaAdapter(this,tanza);
        griglia.setAdapter(adapter);
        tot = (TextView) findViewById(R.id.textView7);

        bottone_conferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showRiepilogo = new Intent(ScegliPietanza.this, RiepilogoActivity.class);
                //showCreaGruppoPag2.putExtra("personeSelect", (Serializable) personeSelect);
                startActivity(showRiepilogo);

            }
        });

    }
    private List<Pietanza> generaPietanze(){
        ArrayList<Pietanza> tanza = new ArrayList<Pietanza>();

        return tanza;
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

            //Intent showSondaggio = new Intent(ScegliPietanza.this, RiepilogoActivity.class);
            //startActivity(showSondaggio);
        }
    }
}


