package com.example.giovy.iumanji;

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


import com.example.giovy.iumanji.database.Persona;

import java.util.List;
/**
 * Created by giovy on 13/02/2017.
 */

public class CreaGruppoAdapter extends RecyclerView.Adapter<CreaGruppoAdapter.MyViewHolder>{

    private Context mContext;
    private List<Persona> personaList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nome, cognome, id;


        public MyViewHolder(View view) {
            super(view);

            id = (TextView) view.findViewById(R.id.id_contatto);
            nome = (TextView) view.findViewById(R.id.nome_contatto);
            cognome = (TextView) view.findViewById(R.id.cognome_contatto);

        }
    }


    public CreaGruppoAdapter(Context mContext, List<Persona> personaList) {
        this.mContext = mContext;
        this.personaList = personaList;
    }

    @Override
    public CreaGruppoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.aggiungi_utente_gruppo_cardview, parent, false);

        return new CreaGruppoAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CreaGruppoAdapter.MyViewHolder holder, int position) {
        Persona persona = personaList.get(position);
        holder.nome.setText(persona.getNome());
        holder.cognome.setText(persona.getCognome());
        holder.id.setText(persona.getId());
    }
    @Override
    public int getItemCount() {
        return personaList.size();
    }
}
