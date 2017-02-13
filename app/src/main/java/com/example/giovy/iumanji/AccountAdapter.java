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
 * Created by giovy on 12/02/2017.
 */

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.MyViewHolder>{

    private Context mContext;
    private List<Persona> personaList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nome, cognome, email;


        public MyViewHolder(View view) {
            super(view);
            nome = (TextView) view.findViewById(R.id.attrNomeRes);
            cognome = (TextView) view.findViewById(R.id.attrCognomeRes);
            email = (TextView) view.findViewById(R.id.attrEmailRes);

        }
    }


    public AccountAdapter(Context mContext, List<Persona> personaList) {
        this.mContext = mContext;
        this.personaList = personaList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.account_cardview, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Persona persona = personaList.get(position);
        holder.nome.setText(persona.getNome());
        holder.cognome.setText(persona.getCognome());
        holder.email.setText(persona.getEmail());
    }
    @Override
    public int getItemCount() {
        return personaList.size();
    }
}
