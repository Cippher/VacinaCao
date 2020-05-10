package com.example.vacinacao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.EditText;

public class PerfilUsuarioActivity extends Activity {
    /*
     * Declaração das variáveis
     */
    EditText txtRua;
    EditText txtNumero;
    EditText txtCidade;
    CadastroPessoaDB cpdb;
    CadastroPessoa cp;
    BaseAdapter bAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        //TODO-Vinícius: 09/05/2020: Precisa carregar as informações do banco para a tela
        //cpdb = new CadastroPessoaDB(getBaseContext());
        //cp = cpdb.carregaCadastroPessoa();
        //cp.getRua()
        //cp.getNumero()
        //cp.getCidade()
        //bAdapter.notifyDataSetChanged();

        txtRua    = (EditText) findViewById(R.id.txtRua);
        txtNumero = (EditText) findViewById(R.id.txtNumero);
        txtCidade = (EditText) findViewById(R.id.txtCidade);

        //cpdb.
    }
    /*
     * Evento de clique de botão "confirmarAlteracao"
     */
    public void confirmarAlteracao(View v){
        CadastroPessoa cadastroPessoa = new CadastroPessoa();
        cadastroPessoa.setRua(txtRua.getText().toString());
        cadastroPessoa.setNumero(Integer.parseInt(txtNumero.getText().toString()));
        cadastroPessoa.setCidade(txtCidade.getText().toString());
        cpdb.alterarCadastroPessoa(cadastroPessoa);
    }
    /*
     * Evento de clique de botão "excluirCadastro"
     */
    public void excluirCadastro(View v){
        cpdb.deletarCadastroPessoa();
        cpdb.fechar();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
