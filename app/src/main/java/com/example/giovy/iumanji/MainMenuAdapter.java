package com.example.giovy.iumanji;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
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

import java.util.List;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.MyViewHolder> {

    private Context mContext;
    private List<Gruppo> gruppoList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nomeGruppo, id_gruppo;

        ImageButton vaiGruppo;
        ImageView immagineGruppo;

        public MyViewHolder(View view) {
            super(view);
            mContext = view.getContext();
            nomeGruppo = (TextView) view.findViewById(R.id.nome_gruppo2);
            immagineGruppo = (ImageView) view.findViewById(R.id.immagine_gruppo2);
            this.vaiGruppo = (ImageButton) view.findViewById(R.id.vai_locale_button2);
            id_gruppo =(TextView) view.findViewById(R.id.id_gruppo);
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
        final Gruppo gruppo = gruppoList.get(position);
        holder.nomeGruppo.setText(gruppo.getNome());
        holder.id_gruppo.setText(gruppo.getId().toString());
        //Bitmap bitmap = BitmapFactory.decodeFile("/storage/BF1A-1C16/Images/"+gruppo.getImmagine());
        //holder.immagineGruppo.setImageBitmap(BitmapFactory.decodeFile("/storage/BF1A-1C16/Images/"+gruppo.getImmagine()+".jpg"));
        holder.immagineGruppo.setImageResource(mContext.getResources().getIdentifier(gruppo.getImmagine(), "drawable", mContext.getPackageName()));
        holder.vaiGruppo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent showGruppo = new Intent(mContext, VisualizzaGruppoActivity.class);
                showGruppo.putExtra("idGruppo", gruppo.getId());
                showGruppo.putExtra("nomeGruppo", gruppo.getNome());
                mContext.startActivity(showGruppo);
            }
        });
    }
    @Override
    public int getItemCount() {
        return gruppoList.size();
    }
}
