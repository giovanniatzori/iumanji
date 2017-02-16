package com.example.giovy.iumanji;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.giovy.iumanji.database.DbAdapter;
import com.example.giovy.iumanji.database.Persona;

import java.util.ArrayList;

public class MembriGruppoActivity extends AppCompatActivity {

    private DbAdapter helper;
    private Cursor cursor;
    private List<Persona> personaList = new ArrayList<>();
    private ListView listview;
    private TextView nomeGruppo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membri_gruppo);

        Bundle sgnaffoli = getIntent().getExtras();
        Integer id = sgnaffoli.getInt("idGruppo");
        String nome = sgnaffoli.getString("nomeGruppo");

        nomeGruppo = (TextView) findViewById(R.id.nome_gruppo_membri);
        nomeGruppo.setText(nome);

        listview = (ListView) findViewById(R.id.visualizza_membri_view);
        helper = DbAdapter.getInstance(this);
        helper.open();

        cursor=helper.fetchGroupPersonsByFilter(id.toString());

        final List<String> listaNomi = new ArrayList<>();
        while (cursor.moveToNext()) {
            Persona a = new Persona(cursor.getString(0), cursor.getString(1),"","");
            personaList.add(a);
            listaNomi.add(a.getNome() + " " + a.getCognome());
        }

        helper.close();

        String[] listContent = listaNomi.toArray(new String[0]);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listContent);


        listview.setAdapter(adapter);

    }
}
