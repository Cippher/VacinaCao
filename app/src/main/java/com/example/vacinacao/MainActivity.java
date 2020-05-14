package com.example.vacinacao;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    /*
     * Declarações das variáveis
     */
    EditText txtEmailLogin;
    EditText txtSenhaLogin;
    CadastroPessoaDB cpdb;

    private String strEmailLogin, strSenhaLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
         * Inicialização das variáveis
         */
        txtEmailLogin = limpaTextoEditText((EditText) findViewById(R.id.txtEmailLogin));
        txtSenhaLogin = limpaTextoEditText((EditText) findViewById(R.id.txtSenhaLogin));
        txtEmailLogin = (EditText) findViewById(R.id.txtEmailLogin);
        txtSenhaLogin = (EditText) findViewById(R.id.txtSenhaLogin);
    }
    /*
     * Evento de clique de botão "login"
     */
    public void entrar(View v){
        strEmailLogin = txtEmailLogin.getText().toString();
        strSenhaLogin = txtSenhaLogin.getText().toString();

        cpdb = new CadastroPessoaDB(getBaseContext());

        CadastroPessoa pessoa = cpdb.carregaCadastroPessoa();
        //System.out.println(pessoa.getRua() + pessoa.getNumero() + pessoa.getCidade() + pessoa.getEmail() + pessoa.getSenha());

        // Se o e-mail digitado é válido
        if (oEmailEValido(strEmailLogin)){
            // Se o e-mail está gravado no banco
            if (strEmailLogin.equals(pessoa.getEmail())){
                // Se a senha está cadastrada no banco
                if (strSenhaLogin.equals(pessoa.getSenha())){
                    Intent intent = new Intent(this, MenuUsuarioPetActivity.class);
                    //Chama a tela de menu de usuário e pet
                    startActivity(intent);
                }else{
                    exibeMensagem("Erro!", "As senhas não correspondem! Tente novamente.");
                    txtSenhaLogin = limpaTextoEditText(txtSenhaLogin);
                }
            }else{
                exibeMensagem("Erro!", "E-mail não cadastrado! Tente novamente.");
                txtEmailLogin = limpaTextoEditText(txtEmailLogin);
                txtSenhaLogin = limpaTextoEditText(txtSenhaLogin);
            }
        }else{
            exibeMensagem("Erro!", "E-mail inválido! Tente novamente.");
            txtEmailLogin = limpaTextoEditText(txtEmailLogin);
            txtSenhaLogin = limpaTextoEditText(txtSenhaLogin);
        }
    }
    /*
     * Evento de clique de botão "cadastrar"
     */
    public void cadastrar(View v){
        Intent intent = new Intent(this, CadastroPessoaActivity.class);
        //Chama a tela de cadastro de pessoa
        startActivity(intent);
    }
    /*
     * Limpa os campos de senhas
     */
    public EditText limpaTextoEditText(EditText edTxt){
        edTxt.setText("");
        return edTxt;
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

        // Falta validar o "ponto"
        final String PASSWORD_PATTERN = "^(.+)@(.+)$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(email);

        return matcher.matches();
    }
}
