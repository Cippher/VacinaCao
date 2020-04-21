package com.example.vacinacao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /*
     * Evento do botão login
     */
    public void login(View v){
        //Chamaar a outra tela
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
