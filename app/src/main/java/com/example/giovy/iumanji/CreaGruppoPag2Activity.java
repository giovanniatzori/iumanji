package com.example.giovy.iumanji;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.giovy.iumanji.database.DbAdapter;
import com.example.giovy.iumanji.database.Persona;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreaGruppoPag2Activity extends AppCompatActivity {

    private Button crea_gruppo2;
    private ListView listview;
    private Cursor cursor;
    private DbAdapter helper;
    private List<Integer> idSelect = new ArrayList<>();
    private EditText nome_gruppo;
    private ImageView immagineGruppo;
    private static final int PHOTO_REQUEST_CODE=0 ;
    ImageButton aggiungi_foto_button;
    File file=new File(Environment.getExternalStorageDirectory(),"file name");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_gruppo_pag2);

        aggiungi_foto_button = (ImageButton) findViewById(R.id.immagine_gruppo_button);

        immagineGruppo = (ImageView)findViewById(R.id.immagine_gruppo);
        aggiungi_foto_button = (ImageButton) findViewById(R.id.immagine_gruppo_button);
        aggiungi_foto_button.setOnClickListener(new View.OnClickListener(){
                                                    @Override
                                                    public void onClick(View view){
                                                        Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                                        startActivityForResult(photoIntent, PHOTO_REQUEST_CODE);
                                                    }
                                                }

        );
        //immagineGruppo.setImageResource(getResources().getIdentifier("china", "drawable", getPackageName()));

        crea_gruppo2 = (Button) this.findViewById(R.id.crea_gruppo2_button);
        crea_gruppo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nome_gruppo = (EditText) findViewById(R.id.nomeGruppo);
                String nomeGruppo = nome_gruppo.getText().toString();

                Context context = getApplicationContext();

                helper = DbAdapter.getInstance(context);
                helper.open();

                Integer i = (helper.fetchMaxIdGruppo())+1;

                if(!(nomeGruppo).isEmpty()){
                    helper.createGroup(i.toString(), nomeGruppo, "");
                    for(Integer j : idSelect){
                        helper.createGroupMember(i.toString(), j.toString());
                    }
                    Intent showCreaGruppoPag2 = new Intent(CreaGruppoPag2Activity.this, MainMenu.class);
                    startActivity(showCreaGruppoPag2);
                } else {
                    nome_gruppo.setError("Campo obbligatorio");
                }

                helper.close();

                }
            });

        listview = (ListView) findViewById(R.id.lista_persone_scelte);

        String[] listContent =  getPersoneSelect();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listContent);

        listview.setAdapter(adapter);

    }

    public String[] getPersoneSelect (){
        ArrayList<String> nomi = new ArrayList<>();
        HashMap<String, Boolean> personeSelect = (HashMap<String, Boolean>) getIntent().getSerializableExtra("personeSelect");

        helper = DbAdapter.getInstance(this);
        helper.open();

        for(String s : personeSelect.keySet()){
            if(personeSelect.get(s)) {
                nomi.add(s);
                idSelect.add(helper.getIdPersonaNomeCognome(s));
            }
        }

        helper.close();

        return nomi.toArray(new String[0]);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PHOTO_REQUEST_CODE)
        {
            Bitmap bp = (Bitmap) data.getExtras().get("data");
            immagineGruppo.setImageBitmap(bp);
        }
    }

}
