package com.example.vacinacao;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    /*
     * Declarações das variáveis
     */
    EditText txtEmailLogin;
    EditText txtSenhaLogin;
    CadastroPessoaDB cpdb;

    private String strEmailLogin, strSenhaLogin, strEmailBanco, strSenhaBanco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
         * Inicialização das variáveis
         */
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
        //strEmailBanco =
        //strSenhaBanco =
        //TODO-Vinícius: 27/04/2020: Buscar e-mail e senha no banco
        // Se o e-mail está gravado no banco
        if (strEmailLogin.equals(strEmailBanco)){
            // Se a senha está cadastrada no banco
            if (strSenhaLogin.equals(strSenhaBanco)){
                Intent intent = new Intent(this, MenuUsuarioPetActivity.class);
                //Chama a tela de menu de usuário e pet
                startActivity(intent);
            }else{
                exibeMensagem("Erro!", "As senhas não correspondem! Tente novamente.");
                txtSenhaLogin = limpaTextoEditText(txtSenhaLogin);
            }
        }else{
            exibeMensagem("Erro!", "Os e-mails não correspondem! Tente novamente.");
            txtEmailLogin = limpaTextoEditText(txtEmailLogin);
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
}
