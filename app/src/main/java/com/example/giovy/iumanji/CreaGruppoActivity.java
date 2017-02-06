package com.example.giovy.iumanji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreaGruppoActivity extends AppCompatActivity {

    Button avanti_crea_gruppo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_gruppo);

        avanti_crea_gruppo = (Button) this.findViewById(R.id.crea_gruppo_avanti_button);

        avanti_crea_gruppo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showCreaGruppoPag2 = new Intent(CreaGruppoActivity.this, CreaGruppoPag2Activity.class);
                startActivity(showCreaGruppoPag2);

            }
        });
    }
}
