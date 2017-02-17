package com.example.giovy.iumanji;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ScegliPietanza extends AppCompatActivity {

    private TextView cronometro;
    private long timer = 1;
    private ListView listview;
    private MyCustomAdapter dataAdapter = null;
    private Integer id = 3;
    private TextView tot;
    private List<Pietanza> pietanzeleList = new ArrayList<Pietanza>();
    ListView list;
    String prezzoTotale = "Prezzo totale € ";
    Button conferma;

    Double prezzoTot = 0.0;
    //private ScegliPietanzaAdapter adapter = new ScegliPietanzaAdapter(this,generaPietanze());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scegli_pietanza);
        list = (ListView) findViewById(R.id.grigliaPietanze);
        cronometro = (TextView) findViewById(R.id.chronometer3);
        MyCountDownTimer myCountDownTimer;
        myCountDownTimer = new MyCountDownTimer(timer * 60000, 1000);
        myCountDownTimer.start();

        //List<Pietanza> tanza = new ArrayList<Pietanza>();
        DbAdapter helper;
        Cursor cursor;
        helper = DbAdapter.getInstance(this);
        helper.open();

        cursor=helper.fetchLocaliPietanzesByFilter(id.toString());

        while (cursor.moveToNext()) {
            Pietanza l = new Pietanza(cursor.getString(0), cursor.getDouble(1));
            pietanzeleList.add(l);
        }
        helper.close();
        cursor.close();
        //pietanzeleList = tanza;
        //ScegliPietanzaAdapter adapter = new ScegliPietanzaAdapter(this,tanza);
        dataAdapter = new MyCustomAdapter(this,R.layout.activity_scegli_pietanza_adapter, pietanzeleList);
        list.setAdapter(dataAdapter);
        tot = (TextView) findViewById(R.id.textView7);
        Double totaleTotale=0.0;

        tot.setText(prezzoTotale + prezzoTot.toString());
        conferma = (Button) findViewById(R.id.conferma_button);
        conferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showSondaggio = new Intent(ScegliPietanza.this, RiepilogoActivity.class);
                showSondaggio.putExtra("listaPietanze", (Serializable) dataAdapter);

                startActivity(showSondaggio);
            }
        });

    }
    private List<Pietanza> generaPietanze(){
        ArrayList<Pietanza> tanza = new ArrayList<Pietanza>();

        return tanza;
    }
    private class MyCustomAdapter extends ArrayAdapter<Pietanza> {

        private ArrayList<Pietanza> productList;

        public MyCustomAdapter(Context context, int textViewResourceId, List<Pietanza> productList) {
            super(context, textViewResourceId, productList);
            this.productList = new ArrayList<Pietanza>();
            this.productList.addAll(productList);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            //DecimalFormat df = new DecimalFormat("0.00##");
            Pietanza product = productList.get(position);
            if (view == null) {
                LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = vi.inflate(R.layout.activity_scegli_pietanza_adapter, null);
                EditText quantity = (EditText) view.findViewById(R.id.quantita);
                //attach the TextWatcher listener to the EditText
                quantity.addTextChangedListener(new MyTextWatcher(view));
                /*if(position % 2 == 0){
                    view.setBackgroundColor(Color.rgb(238, 233, 233));
                }*/
            }

            EditText quantity = (EditText) view.findViewById(R.id.quantita);
            quantity.setTag(product);
            if(product.getQuantita() != 0){
                    quantity.setText(String.valueOf(product.getQuantita()));
            }
            else {
                quantity.setText("");
            }

            TextView prezzo = (TextView) view.findViewById(R.id.prezzo);
            prezzo.setText(product.getPrezzo().toString());

            TextView description = (TextView) view.findViewById(R.id.NomePietanzaScelta);
            description.setText(product.getNome());

            return view;

        }

    }
    private class MyTextWatcher implements TextWatcher{

        private View view;
        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //do nothing
        }
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //do nothing
        }
        public void afterTextChanged(Editable s) {

            String qtyString = s.toString().trim();
            int quantity = qtyString.equals("") ? 0:Integer.valueOf(qtyString);

            EditText qtyView = (EditText) view.findViewById(R.id.quantita);
            Pietanza product = (Pietanza) qtyView.getTag();

            if(product.getQuantita() != quantity){

                Double currPrice = product.getPrezzo();
                Double extPrice = quantity * currPrice;
                Double oldPrice = product.getQuantita() * product.getPrezzo();
                prezzoTot -= oldPrice;
                System.out.println(prezzoTot);
                prezzoTot += extPrice;
                tot.setText(prezzoTotale + prezzoTot.toString());

                product.setQuantita(quantity);
                product.setTotale(extPrice);

                if(product.getQuantita() != 0){
                    qtyView.setText(String.valueOf(product.getQuantita()));
                }
                else {
                    qtyView.setText("");
                }

            }

            return;
        }
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
            Toast.makeText(ScegliPietanza.this, Thread.currentThread().getName()+"", Toast.LENGTH_LONG).show();

            cronometro.setText(minutes + " : " + seconds);
        }

        @Override
        public void onFinish() {

            //Intent showSondaggio = new Intent(ScegliPietanza.this, RiepilogoActivity.class);
            //startActivity(showSondaggio);
        }
    }
}


