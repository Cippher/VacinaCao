package com.example.vacinacao;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    private String dataNascimentoPessoa;
    private String strSexoPessoa;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private Date dataNascPessoa;
    private static final String[] strSexo = new String[]{"M", "F"};

    //CadastroPessoaAdapter adapter;
    CadastroPessoaDB      cpdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);

        //TODO-Vinícius: 27/04/2020: Remover todos os componentes da tela e colocar sobre o scrollView

        /*
         * Variable initialize section
         */
        /*txtNome =                      (EditText) findViewById(R.id.txtNome);
        txtEmailCadastro =             (EditText) findViewById(R.id.txtEmailCadastro);
        spnSexo =                      (Spinner) findViewById(R.id.spnSexo);
        txtDataNascimentoPessoa =      (EditText) findViewById(R.id.txtDataNascimentoPessoa);
        txtNovaSenhaCadastro =         (EditText) findViewById(R.id.txtNovaSenhaCadastro);
        txtNovaSenhaCadastroConfirma = (EditText) findViewById(R.id.txtNovaSenhaCadastroConfirma);
        txtRua =                       (EditText) findViewById(R.id.txtRua);
        txtNumero =                    (EditText) findViewById(R.id.txtNumero);
        txtCidade =                    (EditText) findViewById(R.id.txtCidade);*/
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CadastroPessoaActivity.this,
                android.R.layout.simple_spinner_item,strSexo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSexo.setAdapter(adapter);
        spnSexo.setOnItemSelectedListener(this);
        // TODO-Vinícius: 04/05/2020: Precisa validar a senha antes de testar?
        // Se as senhas informadas são iguais
        if (txtNovaSenhaCadastro.getText().toString() !=
            txtNovaSenhaCadastroConfirma.getText().toString()){
            // Message alert
            showMessage("Erro!", "As senhas não correspondem! Tente novamente!");
            // Clear text on activity
            clearText();
        }
    }
    /*
     * Message alert
     */
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    /*
     * Password validation
     */
    private boolean isPasswordValid (String password) {
        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }
    /*
     * Clear text on activity
     */
    public void clearText(){
        txtNovaSenhaCadastro.setText("");
        txtNovaSenhaCadastroConfirma.setText("");
    }
    /*
     * Push button "confirmar"
     */
    public void confirmar(View v){
        //TODO-Vinícius: 04/05/2020: Talvez de problema ao adicionar no banco as colunas do
        //TODO-Vinícius: 04/05/2020: ... mesmo estão definidas como string, ver se da erro e tratar
        CadastroPessoa cadastroPessoa = new CadastroPessoa();
        // Nome da pessoa
        cadastroPessoa.setNome(txtNome.getText().toString());
        // E-mail da pessoa
        cadastroPessoa.setEmail(txtEmailCadastro.getText().toString());
        // Sexo da pessoa
        cadastroPessoa.setSexo(strSexoPessoa);
        // Data de nascimento da pessoa
        dataNascimentoPessoa = txtDataNascimentoPessoa.getText().toString();
        try {
            dataNascPessoa = format.parse(dataNascimentoPessoa);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cadastroPessoa.setDataNascimento(dataNascPessoa);
        // Senha validada
        cadastroPessoa.setSenha(txtNovaSenhaCadastro.getText().toString());
        // Rua
        cadastroPessoa.setRua(txtRua.getText().toString());
        // Número
        cadastroPessoa.setNumero(Integer.parseInt(txtNumero.getText().toString()));
        // Cidade
        cadastroPessoa.setCidade(txtCidade.getText().toString());
        // Inclui um novo cadastro no banco
        cpdb.adicionarCadastroPessoa(cadastroPessoa);
        // Chama a tela de cadastro de cachorro
        Intent intent = new Intent(this, CadastroCaoActivity.class);
        startActivity(intent);
        //adapter.notifyDataSetChanged();
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //TODO-Vinícius: 27/04/2020: Whatever you want to happen when the item gets selected
        // Sexo da pessoa
        strSexoPessoa = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //TODO-Vinícius: 27/04/2020: Whatever you want to happen when nothing gets selected
    }
}
