package com.example.giovy.iumanji;

        import android.app.Dialog;
        import android.content.Context;
        import android.content.Intent;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.BaseAdapter;
        import android.widget.Button;
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
    ImageButton elimina;

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
        elimina = (ImageButton) v.findViewById(R.id.elimina_pietanza_button);



        elimina.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);

                // Evito la presenza della barra del titolo nella mia dialog
                dialog.getWindow();
                //dialog.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

                // Carico il layout della dialog al suo intenro
                dialog.setContentView(R.layout.elimina);

                // Nel caso fosse previsto un titolo questo sarebbe il codice da
                // utilizzare eliminando quello visto poco sopra per evitarlo
                dialog.setTitle("Vuoi davvero eliminare?");

                dialog.setCancelable(true);

                // Qui potrei aggiungere eventuali altre impostazioni per la dialog
                // ...

                //Gestisco il bottone di chiusura della dialog (quello in alto a destra)
                Button imgclose = (Button) dialog.findViewById(R.id.no_elimina);
                imgclose.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                /*//Gestisco il bottone, della dialog, di apertura della schermata per la chiamata
                final ImageButton button_dialog_to_call = (ImageButton) dialog.findViewById(R.id.DialogToCall01);
                button_dialog_to_call.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        // Funzioni varie
                    }
                });*/
                dialog.show();
                //Gestisco il bottone, della dialog, per l'apertura dell'applicazione nativa di messaggistica per rispondere all'SMS selezionato
                final Button button_dialog_reply = (Button) dialog.findViewById(R.id.si_elimina);
                button_dialog_reply.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });

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
