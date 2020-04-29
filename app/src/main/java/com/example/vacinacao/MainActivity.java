package com.example.vacinacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btEntrar, btCadastrar;
    /*
     * Variable declaration section
     */
    EditText txtEmailLogin;
    EditText txtSenhaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Variable initialize section
         */
        txtEmailLogin = (EditText) findViewById(R.id.txtEmailLogin);
        txtSenhaLogin = (EditText) findViewById(R.id.txtSenhaLogin);


    }

    /*
    * Push button "login"
    */
    public void entrar(View v){
        //TODO-Vinícius: 27/04/2020: Validar txtEmailLogin e txtSenhaLogin
        //TODO-Vinícius: 27/04/2020: Se email e login válidos, chamar a outra tela
    }
    /*
    * Push button "cadastrar"
    */
    public void cadastrar(View v){
        Intent intent = new Intent(this, CadastroPessoaActivity.class);
        //Chama a tela de cadastro
        startActivity(intent);
    }

}
