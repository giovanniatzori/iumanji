package com.example.giovy.iumanji;

import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistrazioneActivity extends AppCompatActivity{

    Person person;

    EditText nameText, surnameText, emailText;
    Button registerButton;

    boolean isResumed;


    public static final  String PERSON_EXTRA = "com.example.giovy.iumanji.Person";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);

        person= new Person();

        isResumed = false;

        nameText = (EditText) this.findViewById(R.id.editNome);
        surnameText = (EditText) this.findViewById(R.id.editCognome);
        emailText = (EditText) this.findViewById(R.id.editEmailRegistrazione);

        //Configurazione Pulsante di Registrazione
        registerButton = (Button) this.findViewById(R.id.registra_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if(checkInput()){
                    UpdatePerson();
                Intent salvaAccount = new Intent(RegistrazioneActivity.this, AccountActivity.class);
                salvaAccount.putExtra(PERSON_EXTRA, person);
                startActivity(salvaAccount);

                //Intent showMainMenu = new Intent(RegistrazioneActivity.this, MainMenu.class);
                   // startActivity(showMainMenu);

                }

        });
    }
     /* FARE TUTTI I CONTROLLI SU EMAIL E PASSWORD */

    @Override
    public void onResume(){
        super.onResume();
        isResumed = true;
    }

    private void UpdatePerson(){
        this.person.setName(this.nameText.getText().toString());
        this.person.setSurname(this.surnameText.getText().toString());
        this.person.setEmail(this.emailText.getText().toString());
    }

}
