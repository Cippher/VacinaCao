package com.example.vacinacao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuUsuarioPetActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario_pet);
    }
    /*
     * Go to PerfilUsuarioActivity
     */
    public void editarPerfil(View v){
        Intent intent = new Intent(this, PerfilUsuarioActivity.class);
        startActivity(intent);
    }
    /*
     * Go to CadastroCaoActivity
     */
    public void cadastrarPet(View v){
        Intent intent = new Intent(this, CadastroCaoActivity.class);
        startActivity(intent);
    }
    /*
     * Go to atualiza cadastro caos
     */
    public void atualizarCadastro(View v){
        Intent intent = new Intent(this, PerfilPetActivity.class);
        startActivity(intent);
    }
    /*
     * Go to Vacina
     */
    public void vacina(View v){
        Intent intent = new Intent(this, RegistroVacinaActivity.class);
        startActivity(intent);
    }
}
