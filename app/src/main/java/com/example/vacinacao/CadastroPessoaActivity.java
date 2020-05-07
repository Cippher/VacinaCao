package com.example.vacinacao;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


import androidx.appcompat.app.AppCompatActivity;

public class CadastroPessoaActivity extends Activity implements AdapterView.OnItemSelectedListener {

    /*
     * Variable declaration section
     */
    EditText txtNome;
    EditText txtEmailCadastro;
    Spinner  spnSexo;
    EditText txtDataNascimentoPessoa;
    EditText txtNovaSenhaCadastro;
    EditText txtNovaSenhaCadastroConfirma;
    EditText txtRua;
    EditText txtNumero;
    EditText txtCidade;

    //CadastroPessoaAdapter adapter;
    CadastroPessoaDB      cpdb;

    //private static final String[] strSexo = new String[]{"M", "F"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);

        //TODO-Vinícius: 27/04/2020: Remover todos os componentes da tela e colocar sobre o scrollView

        /*
         * Variable initialize section
         */
        txtNome =                      (EditText) findViewById(R.id.txtNome);
        txtEmailCadastro =             (EditText) findViewById(R.id.txtEmailCadastro);
        spnSexo =                      (Spinner) findViewById(R.id.spnSexo);
        txtDataNascimentoPessoa =      (EditText) findViewById(R.id.txtDataNascimentoPessoa);
        txtNovaSenhaCadastro =         (EditText) findViewById(R.id.txtNovaSenhaCadastro);
        txtNovaSenhaCadastroConfirma = (EditText) findViewById(R.id.txtNovaSenhaCadastroConfirma);
        txtRua =                       (EditText) findViewById(R.id.txtRua);
        txtNumero =                    (EditText) findViewById(R.id.txtNumero);
        txtCidade =                    (EditText) findViewById(R.id.txtCidade);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(CadastroPessoaActivity.this,
               // android.R.layout.simple_spinner_item,stnSexo);

        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spnSexo.setAdapter(adapter);
        //spnSexo.setOnItemSelectedListener(this);

        //TODO-Vinícius: 27/04/2020: Precisa validar a senha com a confirmação da senha
        //Se txtNovaSenhaCadastro == txtNovaSenhaCadastroConfirma
        //Segue
        //Se diferente, erro

    }
    /*
     * Push button "confirmar"
     */
    public void confirmar(View v){
        //TODO-Vinícius: 27/04/2020: Se todos os dados são válidos
        //TODO-Vinícius: 27/04/2020: Criar classe CadastroPessoa
        // Adiciona um novo cadastros de pessoa ao banco de dados
        //CadastroPessoa cadastroPessoa = new CadastroPessoa();
       // cadastroPessoa.setNome(txtNome.getText().toString());
      //  cadastroPessoa.setEmail(txtEmailCadastro.getText().toString());
        //cadastroPessoa.setSexo(...);
        //cadastroPessoa.setDataNascimento(txtDataNascimentoPessoa.getText().toString());
        //cadastroPessoa.setSenha(...);
        //cadastroPessoa.setRua(txtRua.getText().toString());
        //cadastroPessoa.setNumero(...);
        //cadastroPessoa.setCidade(txtRua.getText().toString());
       // cpdb.adicionarCadastroPessoa(cadastroPessoa);

        Intent intent = new Intent(this, CadastroCaoActivity.class);
        //Chama a tela de cadastro pet
        startActivity(intent);
        //adapter.notifyDataSetChanged();
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //TODO-Vinícius: 27/04/2020: Whatever you want to happen when the item gets selected
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //TODO-Vinícius: 27/04/2020: Whatever you want to happen when nothing gets selected
    }
}
