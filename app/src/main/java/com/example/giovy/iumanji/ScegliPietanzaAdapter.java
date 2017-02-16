package com.example.giovy.iumanji;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.giovy.iumanji.database.Pietanza;

import java.util.List;

/**
 * Created by alessandrotola on 16/02/17.
 */

public class ScegliPietanzaAdapter extends BaseAdapter {

    private List<Pietanza> pietanze=null;
    private Context context=null;

    public ScegliPietanzaAdapter(Context context,List<Pietanza> pietanze)
    {
        this.pietanze=pietanze;
        this.context=context;
    }

    @Override
    public int getCount()
    {
        return pietanze.size();
    }

    @Override
    public Object getItem(int position)
    {
        return pietanze.get(position);
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
            v= LayoutInflater.from(context).inflate(R.layout.activity_scegli_pietanza_adapter, null);
        }
        final Pietanza ai=(Pietanza) getItem(position);
        TextView txt=(TextView) v.findViewById(R.id.prezzo);
        txt.setText(ai.getPrezzo().toString());
        txt=(TextView) v.findViewById(R.id.NomePietanzaScelta);
        txt.setText(ai.getNome());
        final EditText edt = (EditText) v.findViewById(R.id.quantita);

        edt.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {
                Integer qt = Integer.parseInt(edt.getText().toString());
                ai.setQuantita(qt);
                System.out.println(ai.getQuantita());
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        return v;
    }

}
