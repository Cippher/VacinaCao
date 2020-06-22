package com.example.vacinacao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegistroVacinaActivity extends Activity {

    /*
     * Declaração das variáveis
     */
    EditText txtLaboratorio;
    EditText txtLote;
    EditText txtValidade;
    EditText dtDataAplicacao;
    EditText txtNome;

    private String data, data1;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Date dataV, dataV1;

    VacinaDB ccdb;
    CadastroCaoDB cpdb;
    CadastroCao cp;
    Vacina v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_vacina);

        ccdb = new VacinaDB(getBaseContext());
        v = ccdb.carregaVacina();
        cpdb = new CadastroCaoDB(getBaseContext());
        cp = cpdb.carregaCadastroCao();

        txtLaboratorio =       (EditText) findViewById(R.id.txtLaboratorio);
        txtLote =              (EditText) findViewById(R.id.txtLote);
        txtValidade =          (EditText) findViewById(R.id.txtValidade);
        dtDataAplicacao =      (EditText) findViewById(R.id.dtDataAplicacao);
        txtNome =              (EditText) findViewById(R.id.txtNome);

        if (v.getValidade() != null){
            String validade = v.getValidade().toString();
            txtValidade.setText(validade);
        }
        txtLote.setText(v.getLote());
        txtLaboratorio.setText(v.getLaboratorio());
        if (v.getDataAplicacao() != null){
            String dataAplicacao = v.getDataAplicacao().toString();
            dtDataAplicacao.setText(dataAplicacao);
        }
        txtNome.setText(cp.getNome());

        txtLaboratorio =       (EditText) findViewById(R.id.txtLaboratorio);
        txtLote =              (EditText) findViewById(R.id.txtLote);
        txtValidade =          (EditText) findViewById(R.id.txtValidade);
        dtDataAplicacao =      (EditText) findViewById(R.id.dtDataAplicacao);
        //txtNome =              (EditText) findViewById(R.id.txtNome);

        //txtIdade.setText(Integer.toString(cp.getIdade()));
    }

    public void registrar(View v){

        Vacina cc = new Vacina();
        //
        cc.setLaboratorio(txtLaboratorio.getText().toString());
        //
        cc.setLote(txtLote.getText().toString());
        //
        data = dtDataAplicacao.getText().toString();
        try {
            dataV = dateFormat.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cc.setDataAplicacao(dataV);

        data1 = txtValidade.getText().toString();
        try {
            dataV1 = dateFormat.parse(data1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cc.setValidade(dataV1);

        // Inclui um novo cadastro no banco
        ccdb.adicionarVacina(cc);
        Intent intent = new Intent(this, MenuUsuarioPetActivity.class);
        startActivity(intent);
    }

}
