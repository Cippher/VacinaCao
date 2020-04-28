package com.example.vacinacao;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroPessoaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);
        //coment

    }

    /*
     * Evento do botão cadastro de pet
     */
    public void confirmar(View v){
        Intent intent = new Intent(this, CadastroCaoActivity.class);
        //Chama a tela de cadastro pet
        startActivity(intent);
    }


}
