package com.example.vacinacao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;

public class PerfilUsuarioActivity extends Activity {
    /*
     * Declaração das variáveis
     */
    EditText txtRua;
    EditText txtNumero;
    EditText txtCidade;
    CadastroPessoaDB cpdb;
    CadastroPessoa cp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        cpdb = new CadastroPessoaDB(getBaseContext());
        cp = cpdb.carregaCadastroPessoa();

        txtRua    = (EditText) findViewById(R.id.txtRua);
        txtNumero = (EditText) findViewById(R.id.txtNumero);
        txtCidade = (EditText) findViewById(R.id.txtCidade);

        txtRua.setText(cp.getRua());
        txtNumero.setText(Integer.toString(cp.getNumero()));
        txtCidade.setText(cp.getCidade());

        txtRua    = (EditText) findViewById(R.id.txtRua);
        txtNumero = (EditText) findViewById(R.id.txtNumero);
        txtCidade = (EditText) findViewById(R.id.txtCidade);
    }
    /*
     * Evento de clique de botão "confirmarAlteracao"
     */
    public void confirmarAlteracao(View v){
        cpdb = new CadastroPessoaDB(getBaseContext());
        CadastroPessoa cadastroPessoa = new CadastroPessoa();
        cadastroPessoa.setRua(txtRua.getText().toString());
        cadastroPessoa.setNumero(Integer.parseInt(txtNumero.getText().toString()));
        cadastroPessoa.setCidade(txtCidade.getText().toString());
        cpdb.alterarCadastroPessoa(cadastroPessoa);
        Intent intent = new Intent(this, MenuUsuarioPetActivity.class);
        startActivity(intent);
    }
    /*
     * Evento de clique de botão "excluirCadastro"
     */
    public void excluirCadastro(View v){

        cpdb.deletarCadastroPessoa();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
