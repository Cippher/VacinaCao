package com.example.vacinacao;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadastroPessoaActivity extends Activity implements AdapterView.OnItemSelectedListener {

    /*
     * Declaração das variáveis
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
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private Date dataNascPessoa;
    private static final String[] strSexo = new String[]{"M", "F"};
    private String strNovaSenhaCadastro, strNovaSenhaCadastroConfirma;

    CadastroPessoaDB cpdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoa);
        /*
         * Inicialização das variáveis
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
        /*
         * Spinner
         */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CadastroPessoaActivity.this,
                                           android.R.layout.simple_spinner_item,strSexo);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnSexo.setAdapter(adapter);
        spnSexo.setOnItemSelectedListener(this);

        cpdb = new CadastroPessoaDB(getBaseContext());
    }
    /*
     * Evento de clique de botão "confirmar"
     */
    public void confirmar(View v){
        strNovaSenhaCadastro = txtNovaSenhaCadastro.getText().toString();
        strNovaSenhaCadastroConfirma = txtNovaSenhaCadastroConfirma.getText().toString();
        /*
         * Se as senhas não são iguais
         */
        if (!strNovaSenhaCadastro.equals(strNovaSenhaCadastroConfirma)){
            // Chama mensagem de alerta
            exibeMensagem("Erro!", "As senhas não correspondem! Tente novamente!");
            // Limpa os campos de senha
            txtNovaSenhaCadastro = limpaTextoString(txtNovaSenhaCadastro);
            txtNovaSenhaCadastroConfirma = limpaTextoString(txtNovaSenhaCadastroConfirma);
        }
        CadastroPessoa cadastroPessoa = new CadastroPessoa();
        // Nome da pessoa
        cadastroPessoa.setNome(txtNome.getText().toString());
        // TODO: Vinícius: 11/05/2020: oEmailEValido - Precisa validar o e-mail antes de inserir no banco
        // E-mail da pessoa
        cadastroPessoa.setEmail(txtEmailCadastro.getText().toString());
        // Sexo da pessoa
        cadastroPessoa.setSexo(strSexoPessoa);
        // Data de nascimento da pessoa
        dataNascimentoPessoa = txtDataNascimentoPessoa.getText().toString();
        try {
            dataNascPessoa = dateFormat.parse(dataNascimentoPessoa);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cadastroPessoa.setDataNascimento(dataNascPessoa);
        // Senha válidada
        cadastroPessoa.setSenha(txtNovaSenhaCadastro.getText().toString());
        // Rua
        cadastroPessoa.setRua(txtRua.getText().toString());
        // Número
        cadastroPessoa.setNumero(Integer.parseInt(txtNumero.getText().toString()));
        // Cidade
        cadastroPessoa.setCidade(txtCidade.getText().toString());
        // Se inseriu todos os dados corretamente (Ajustar, não funciona os testes)
        if (cadastroPessoa.getNome() != null &&
            cadastroPessoa.getEmail() != null &&
            cadastroPessoa.getSexo() != null &&
            cadastroPessoa.getDataNascimento() != null &&
            cadastroPessoa.getSenha() != null &&
            cadastroPessoa.getRua() != null &&
            cadastroPessoa.getNumero() != 0 &&
            cadastroPessoa.getCidade() != null){
            // Inclui um novo cadastro no banco
            cpdb.adicionarCadastroPessoa(cadastroPessoa);
            // Chama a tela de cadastro de menu de usuário e pet
            Intent intent = new Intent(this, MenuUsuarioPetActivity.class);
            startActivity(intent);
        }else{
            exibeMensagem("Erro!", "Pada adicionar um novo cadastro, preencha todas as informações!");
        }
        //System.out.println(oEmailEValido(txtEmailCadastro.getText().toString()));
    }
    /*
     * Spinner - Quando um item é selecionado
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // Sexo da pessoa
        strSexoPessoa = adapterView.getItemAtPosition(i).toString();
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
    /*
     * Valida o e-mail
     */
    private boolean oEmailEValido(String email) {
        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(.+)@(.+)$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(email);

        return matcher.matches();
    }
    /*
     * Limpa os campos de senhas
     */
    public EditText limpaTextoString(EditText edTxt){
        edTxt.setText("");
        return edTxt;
    }
}
