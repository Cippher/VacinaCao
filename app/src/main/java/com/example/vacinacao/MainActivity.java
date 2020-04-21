package com.example.vacinacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail = (EditText) findViewById(R.id.txtEmail);

    }

    /*
    * Evento do botão login
    */
    public void entrar(View v){
        //Chamar a outra tela
    }
    /*
    * Evento do botão cadastrar
    */
    public void cadastrar(View v){
        Intent intent = new Intent(this, CadastroPessoaActivity.class);
        //Chama a tela de cadastro
        startActivity(intent);
    }
}
