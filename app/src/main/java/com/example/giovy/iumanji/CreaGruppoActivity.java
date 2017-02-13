package com.example.giovy.iumanji;

import android.content.Intent;
import android.database.Cursor;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import com.example.giovy.iumanji.database.DatabaseHelper;
import com.example.giovy.iumanji.database.DbAdapter;

import java.util.ArrayList;
import java.util.List;

public class CreaGruppoActivity extends AppCompatActivity {
    private DbAdapter helper;
    private TableRow row;
    private TextView text;
    private Cursor cursor;
    Button avanti_crea_gruppo;
    ListView listaContatti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_gruppo);

        helper = DbAdapter.getInstance(this);
        helper.open();

        this.listaContatti= (ListView) findViewById(R.id.listaContatti);
        //text = (TextView) findViewById(R.id.contatto);
        List<String> records = new ArrayList<>();
        cursor = helper.fetchAllPersons();
        while ( cursor.moveToNext() ) {
            records.add(cursor.getString(1) + " " + cursor.getString(2));

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, records);
        this.listaContatti.setAdapter(adapter);

        helper.close();
        cursor.close();
/* nuovo */
        listaContatti.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listaContatti.setAdapter(adapter);

        avanti_crea_gruppo = (Button) this.findViewById(R.id.crea_gruppo_avanti_button);
        avanti_crea_gruppo.setOnClickListener(new Button.OnClickListener(){

            @Override

            public void onClick(View v) {

                // TODO Auto-generated method stub

                String selected = "";

                int cntChoice = listaContatti.getCount();

                SparseBooleanArray sparseBooleanArray = listaContatti.getCheckedItemPositions();
                listaContatti.setBackgroundResource(R.color.colorButton);
                for(int i = 0; i < cntChoice; i++){

                    if(sparseBooleanArray.get(i)) {
                        selected += listaContatti.getItemAtPosition(i).toString() + "\n";
                    }
                }
                Toast.makeText(CreaGruppoActivity.this,
                        selected,
                        Toast.LENGTH_LONG).show();

            }});

        /*avanti_crea_gruppo = (Button) this.findViewById(R.id.crea_gruppo_avanti_button);

        avanti_crea_gruppo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showCreaGruppoPag2 = new Intent(CreaGruppoActivity.this, CreaGruppoPag2Activity.class);
                startActivity(showCreaGruppoPag2);

            }
        });*/

    }
}
