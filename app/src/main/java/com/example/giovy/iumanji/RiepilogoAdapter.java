package com.example.giovy.iumanji;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.giovy.iumanji.database.Pietanza;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by giovy on 17/02/2017.
 */

public class RiepilogoAdapter extends BaseAdapter {

    private List<Pietanza> pietanza=null;
    private Context context=null;

    public RiepilogoAdapter(Context context,List<Pietanza> pietanza)
    {
        this.pietanza=pietanza;
        this.context=context;
    }

    @Override
    public int getCount()
    {
        return pietanza.size();
    }

    @Override
    public Object getItem(int position)
    {
        return pietanza.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return getItem(position).hashCode();
    }

    @Override
    public View getView(int position, View v, ViewGroup vg)
    {
        if (v==null)
        {
            v=LayoutInflater.from(context).inflate(R.layout.riepilogo_ordine_nome_listview, null);
        }
        Pietanza ai=(Pietanza) getItem(position);
        TextView txt=(TextView) v.findViewById(R.id.riepilogo_nome_textview);
        txt.setText(ai.getNome());
        txt=(TextView) v.findViewById(R.id.riepilogo_prezzo_textview);
        txt.setText(ai.getPrezzo().toString()+"0 €");
        txt=(TextView) v.findViewById(R.id.riepilogo_quantita_textview);
        txt.setText(ai.getQuantita().toString());
        return v;
    }
}
