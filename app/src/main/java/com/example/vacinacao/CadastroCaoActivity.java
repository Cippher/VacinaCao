package com.example.vacinacao;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CadastroCaoActivity  extends Activity implements AdapterView.OnItemSelectedListener{

    /*
     * Declaração das variáveis
     */

    EditText txtNome;
    EditText txtRaca;
    EditText txtDataNascimentoCao;
    EditText txtPeso;
    Spinner spnSexo;

    private String dataNascimentoCao;
    private String strSexoCao;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Date dataNascCao;
    private static final String[] strSexo = new String[]{"M", "F"};




    CadastroCaoDB ccdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pet);

        txtNome =           (EditText) findViewById(R.id.txtNome);
        txtRaca =           (EditText) findViewById(R.id.txtRaca);
        txtDataNascimentoCao = (EditText) findViewById(R.id.txtDataNascimentoCao);
        txtPeso =           (EditText) findViewById(R.id.txtPeso);
        spnSexo =                      (Spinner) findViewById(R.id.spnSexo);
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

    public void confirmar(View v) {
        CadastroCao cc = new CadastroCao();
        // Nome do Cao
        cc.setNome(txtNome.getText().toString());
        // Raca do cao
        cc.setRaca(txtRaca.getText().toString());
        // Peso do cao
        cc.setPeso(Integer.parseInt(txtPeso.getText().toString()));
        // Sexo do cao
        cc.setSexo(strSexoCao);
        // Nascimento do cao
        dataNascimentoCao = txtDataNascimentoCao.getText().toString();
        try {
            dataNascCao = dateFormat.parse(dataNascimentoCao);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cc.setDataNascimento(dataNascCao);

        // Se inseriu todos os dados corretamente
        if (cc.getNome() != null &&
                cc.getDataNascimento() != null &&
                cc.getSexo() != null &&
                cc.getPeso() != 0 &&
                cc.getRaca() != null) {
            // Inclui um novo cadastro no banco
            ccdb.adicionarCadastroCao(cc);
            Intent intent = new Intent(this, MenuUsuarioPetActivity.class);
            startActivity(intent);
        } else {
            exibeMensagem("Erro!", "Para adicionar um novo cadastro, preencha todas as informações!");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // Sexo do cao
        strSexoCao = adapterView.getItemAtPosition(i).toString();
    }
    /*
     * Spinner - Quando um item não é selecionado
     */

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
    /*
     * Exibe uma mensagem com título e texto
     */
    public void exibeMensagem(String title, String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
