package com.example.vacinacao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PerfilPetActivity extends Activity {
    /*
     * Declaração das variáveis
     */
    EditText txtNome;
    EditText txtPeso;
    CadastroCaoDB ccdb;
    CadastroCao cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        ccdb = new CadastroCaoDB(getBaseContext());
        cc = ccdb.carregaCadastroCao();

        txtNome    = (EditText) findViewById(R.id.txtNome);
        txtPeso = (EditText) findViewById(R.id.txtPeso);

    }

    /*
     * Evento de clique de botão "confirmarAlteracaoCao"
     */
    public void confirmarAlteracaoCao(View v) {
        ccdb = new CadastroCaoDB(getBaseContext());
        CadastroCao cadastroCao = new CadastroCao();
        cadastroCao.setNome(txtNome.getText().toString());
        cadastroCao.setPeso(Integer.parseInt(txtPeso.getText().toString()));
        ccdb.alterarCadastroCao(cadastroCao);
        Intent intent = new Intent(this, MenuUsuarioPetActivity.class);
        startActivity(intent);
    }
    /*
     * Evento de clique de botão "excluirCadastro"
     */

    public void excluirCadastroCao(View v){

        ccdb.deletarCadastroCao();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
