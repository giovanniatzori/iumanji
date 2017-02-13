package com.example.giovy.iumanji;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.giovy.iumanji.database.Locale;
import com.example.giovy.iumanji.database.Persona;

import java.io.File;
import java.util.List;
/**
 * Created by giovy on 13/02/2017.
 */

public class CreaSondaggioAdapter extends RecyclerView.Adapter<CreaSondaggioAdapter.MyViewHolder>{

    private Context mContext;
    private List<Locale> localeList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nomeLocale;
        public ImageView immagineLocale;


        public MyViewHolder(View view) {
            super(view);
            nomeLocale = (TextView) view.findViewById(R.id.nome_locale);
            immagineLocale = (ImageView) view.findViewById(R.id.immagine_locale);


        }
    }


    public CreaSondaggioAdapter(Context mContext, List<Locale> localeList) {
        this.mContext = mContext;
        this.localeList = localeList;
    }

    @Override
    public CreaSondaggioAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crea_sondaggio_cardview, parent, false);

        return new CreaSondaggioAdapter.MyViewHolder(itemView);
    }




    @Override
    public void onBindViewHolder(final CreaSondaggioAdapter.MyViewHolder holder, int position) {
        Locale locale = localeList.get(position);
        holder.nomeLocale.setText(locale.getNome());
        File imgFile = new  File("/sdcard/Images/" + locale.getImmagine());

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            holder.immagineLocale.setImageBitmap(myBitmap);

        }

    }
    @Override
    public int getItemCount() {
        return localeList.size();
    }
}

