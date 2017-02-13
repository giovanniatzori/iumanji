package com.example.giovy.iumanji;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.giovy.iumanji.database.Gruppo;

import java.io.File;
import java.util.List;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.MyViewHolder> {

    private Context mContext;
    private List<Gruppo> gruppoList;




    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nomeGruppo, numeroPartecipanti;
        ImageView immagineGruppo;
        ImageButton vaiGruppo;

        public MyViewHolder(View view) {
            super(view);
            mContext = view.getContext();
            nomeGruppo = (TextView) view.findViewById(R.id.nome_gruppo2);
            numeroPartecipanti = (TextView) view.findViewById(R.id.numero_partecipanti_gruppo2a);
            immagineGruppo = (ImageView) view.findViewById(R.id.immagine_gruppo2);
            this.vaiGruppo = (ImageButton) view.findViewById(R.id.vai_locale_button2);

        }

    }


    public MainMenuAdapter(Context mContext, List<Gruppo> groupList) {
        this.mContext = mContext;
        this.gruppoList = groupList;
    }

    @Override
    public MainMenuAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_menu_adapter, parent, false);

        return new MainMenuAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MainMenuAdapter.MyViewHolder holder, int position) {
        Gruppo gruppo = gruppoList.get(position);
        File imgFile = new  File("/sdcard/Images/" + gruppo.getImmagine());

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            holder.immagineGruppo.setImageBitmap(myBitmap);

        }
        holder.nomeGruppo.setText(gruppo.getNome());
        holder.numeroPartecipanti.setText("6");
        holder.vaiGruppo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent showGruppo = new Intent(mContext, VisualizzaGruppoActivity.class);

                mContext.startActivity(showGruppo);
            }
        });
    }
    @Override
    public int getItemCount() {
        return gruppoList.size();
    }
}
