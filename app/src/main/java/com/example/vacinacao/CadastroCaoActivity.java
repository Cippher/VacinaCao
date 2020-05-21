package com.example.vacinacao;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;



public class CadastroCaoActivity  extends Activity implements AdapterView.OnItemSelectedListener{

    EditText txtNome;
    EditText txtRaca;
    EditText txtDataNascimento;
    EditText txtPeso;

    private static final String[] strSexo = new String[]{"M", "F"};

    CadastroCaoDB ccdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pet);

        txtNome =           (EditText) findViewById(R.id.txtNome);
        txtRaca =           (EditText) findViewById(R.id.txtRaca);
        txtDataNascimento = (EditText) findViewById(R.id.txtDataNascimento);
        txtPeso =           (EditText) findViewById(R.id.txtPeso);
        /*
         * Spinner
         */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CadastroCaoActivity.this,
                android.R.layout.simple_spinner_item,strSexo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSexo.setAdapter(adapter);
        spnSexo.setOnItemSelectedListener(this);

        ccdb = new CadastroCaoDB(getBaseContext());
    }

    public void confirmar(View v){
        CadastroCao cc = new CadastroCao();

        cc.setNome(txtNome.getText().toString());
        cc.setRaca(txtRaca.getText().toString());
        cc.setDataNascimento();
        cc.setPeso(Integer.parseInt(txtPeso.getText().toString()));
        cc.setSexo();

        ccdb.adicionarCadastroCao(cc);

        Intent intent = new Intent(this, MenuUsuarioPetActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
