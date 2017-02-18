package com.example.giovy.iumanji;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;

import static android.R.attr.data;

public class AggiungiLocale extends AppCompatActivity {

    //private static final int PHOTO_REQUEST_CODE=0 ;
    private static final int SELECT_IMAGE_CODE = 0;
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
       /* aggiungi_foto_button.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View view){
                Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(photoIntent, PHOTO_REQUEST_CODE);
            }
        }

        );*/

        aggiungi_foto_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // check the value state is null or not
                Show_Toast("Scegliere la foto");
                Intent in = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(in, SELECT_IMAGE_CODE);

            }

            private void Show_Toast(String s) {
            }
        });


    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PHOTO_REQUEST_CODE)
        {
            Bitmap bp = (Bitmap) data.getExtras().get("data");
            photo.setImageBitmap(bp);
        }
    }*/

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_IMAGE_CODE && resultCode == Activity.RESULT_OK) {
            Uri contentUri = data.getData();

            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = managedQuery(contentUri,
                    proj,  // Which columns to return
                    null,  // WHERE clause; which rows to return (all rows)
                    null,  // WHERE clause selection arguments (none)
                    null); // Order-by clause (ascending by name)
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String filePath = cursor.getString(column_index);
            File imgFile = new File(filePath);
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                //Drawable d = new BitmapDrawable(getResources(), myBitmap);
                photo.setImageBitmap(myBitmap);
            }
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
