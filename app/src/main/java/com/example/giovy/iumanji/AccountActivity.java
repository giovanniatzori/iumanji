package com.example.giovy.iumanji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.giovy.iumanji.database.Persona;

import java.io.Serializable;

public class AccountActivity extends AppCompatActivity {

    Persona person;

    TextView nameText, surNameText, emailText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        nameText = (TextView) findViewById(R.id.attrNomeRes);
        surNameText = (TextView) findViewById(R.id.attrCognomeRes);
        emailText = (TextView) findViewById(R.id.attrEmailRes);

        Intent intent = getIntent();
        Serializable obj =  intent.getSerializableExtra(
                RegistrazioneActivity.PERSON_EXTRA);

        if(obj instanceof Persona) {
            person = (Persona) obj;
        }
        else{
            person = new Persona();
        }

        nameText.setText(person.getNome());
        surNameText.setText(person.getCognome());
        emailText.setText(person.getEmail());
    }
}
