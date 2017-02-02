package com.example.giovy.iumanji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.Serializable;

public class AccountActivity extends AppCompatActivity {

    Person person;

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

        if(obj instanceof Person) {
            person = (Person) obj;
        }
        else{
            person = new Person();
        }

        nameText.setText(person.getName());
        surNameText.setText(person.getSurname());
        emailText.setText(person.getEmail());
    }
}
