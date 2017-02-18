package com.example.giovy.iumanji;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;


import com.example.giovy.iumanji.database.Pietanza;

import java.util.List;

/**
 * Created by alessandrotola on 16/02/17.
 */

public class ScegliPietanzaAdapter extends BaseAdapter {

    private List<Pietanza> pietanze;
    private LayoutInflater inflater;
    private Context context;
    private int listPosititon = 1;

    public ScegliPietanzaAdapter(Context context,List<Pietanza> pietanze)
    {
        this.pietanze=pietanze;
        this.context=context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView prezzo;
        TextView nome;
        EditText quantita;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final ViewHolder holder;
        listPosititon = position;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = this.inflater.inflate(
                    R.layout.activity_scegli_pietanza_adapter, parent, false);

            holder.prezzo = (TextView) convertView.findViewById(R.id.prezzo);
            holder.nome = (TextView) convertView.findViewById(R.id.NomePietanzaScelta);
            holder.quantita = (EditText) convertView.findViewById(R.id.quantita);


            convertView.setTag(holder);
            convertView.setTag(R.id.prezzo, holder.prezzo);
            convertView.setTag(R.id.NomePietanzaScelta, holder.nome);
            convertView.setTag(R.id.quantita, holder.quantita);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.quantita.setTag(position);

        holder.quantita.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                int pos = (Integer) holder.quantita.getTag();

                pietanze.get(pos).setQuantita(Integer.parseInt(s.toString()));

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.prezzo.setText(pietanze.get(position).getPrezzo().toString());
        holder.nome.setText(pietanze.get(position).getNome());
        holder.quantita.setText(pietanze.get(position)
                .getQuantita());

        return convertView;
    }
}


