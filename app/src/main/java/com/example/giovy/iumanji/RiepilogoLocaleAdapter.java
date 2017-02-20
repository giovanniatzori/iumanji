package com.example.giovy.iumanji;

        import android.content.Context;
        import android.content.Intent;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.BaseAdapter;
        import android.widget.EditText;
        import android.widget.ImageButton;
        import android.widget.TextView;

        import com.example.giovy.iumanji.database.Locale;
        import com.example.giovy.iumanji.database.Pietanza;

        import java.util.ArrayList;
        import java.util.List;
/**
 * Created by giovy on 20/02/2017.
 */

public class RiepilogoLocaleAdapter extends  BaseAdapter{


    private List<Pietanza> pietanza=null;
    private Context context=null;

    public RiepilogoLocaleAdapter(Context context,List<Pietanza> pietanza)
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
            v=LayoutInflater.from(context).inflate(R.layout.pietanza_locali_listview, null);
        }
        final Pietanza ai=(Pietanza) getItem(position);
        TextView txt=(TextView) v.findViewById(R.id.prezzo_pietanza_locali);
        txt.setText(ai.getPrezzo().toString()+"0 €");
        txt=(TextView) v.findViewById(R.id.nome_pietanza_locali);
        txt.setText(ai.getNome());
        ImageButton img = (ImageButton) v.findViewById(R.id.elimina_pietanza_button);

        /*txt.setOnClickListener(new View.OnClickListener() {
            String nomeLocale ;
            String idLocale;
            //String immagineLocale;

            @Override
            public void onClick(View v) {
                Intent showCreaGruppo = new Intent(context, RiepilogoLocale.class);
                nomeLocale = ai.getNome();
                idLocale = ai.getId().toString();
                //immagineLocale=ai.getImmagine();
               // showCreaGruppo.putExtra("idLocale", idLocale);
               // showCreaGruppo.putExtra("nomeLocale", nomeLocale);
                //showCreaGruppo.putExtra("immagineLocale",immagineLocale);
                context.startActivity(showCreaGruppo);
            }
        });*/

        return v;
    }
}
