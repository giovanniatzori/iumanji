package com.example.giovy.iumanji;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import static android.R.attr.data;

public class AggiungiLocale extends AppCompatActivity {

    private static final int PHOTO_REQUEST_CODE=0 ;
    Button aggiungi_locale_button;
    ImageButton aggiungi_foto_button;
    ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_locale);

        photo = (ImageView) findViewById(R.id.imagine_locale_fotocamera);

        aggiungi_locale_button = (Button) findViewById(R.id.aggiungi_locale_button);
        aggiungi_locale_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showCreaGruppo = new Intent(AggiungiLocale.this, Locali.class);
                startActivity(showCreaGruppo);
            }
        });

        aggiungi_foto_button = (ImageButton) findViewById(R.id.immagine_locale_button);
        aggiungi_foto_button.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View view){
                Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoIntent, PHOTO_REQUEST_CODE);
            }
        }

        );


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PHOTO_REQUEST_CODE)
        {
            Bitmap bp = (Bitmap) data.getExtras().get("data");
            photo.setImageBitmap(bp);
        }
    }
}
