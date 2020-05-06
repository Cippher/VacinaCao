package com.example.vacinacao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class CadastroPessoaDB {
    //TODO-Vinícius: 27/04/2020: Banco de dados do cadastro de pessoa
    //TODO-Vinícius: 27/04/2020: Criar classe adapter (precisa ?!?!?! ver melhor)
    private static final String NOME_BANCO = "pessoas.db";
    private static final String TAB_PESSOAS = "PESSOAS";
    private static final int VERSAO_BANCO = 10;

    private static final String COL_ID = "ID";
    private static final String COL_NOME = "NOME";
    private static final String COL_EMAIL = "EMAIL";
    private static final String COL_SEXO = "SEXO";
    private static final String COL_DATA = "DATA";
    private static final String COL_SENHA = "SENHA";
    private static final String COL_RUA = "RUA";
    private static final String COL_NUMERO = "NUMERO";
    private static final String COL_CIDADE = "CIDADE";

    private static final String SQL_TAB_PESSOAS = "create table " + TAB_PESSOAS +
            "(" + COL_ID + " integer primary key autoincrement," +
            COL_NOME + " text not null," +
            COL_EMAIL + " text not null," +
            COL_SEXO + " text not null," +
            COL_DATA + " text not null," +
            COL_SENHA + " text not null," +
            COL_RUA + " text not null," +
            COL_NUMERO + " text not null," +
            COL_CIDADE + " text not null)";

    private cadastroDBHelper dbHelper;
    private SQLiteDatabase db;
    private Context ctx;

    public CadastroPessoaDB(Context ctx){
        dbHelper = new cadastroDBHelper(ctx, NOME_BANCO, null, VERSAO_BANCO);
        this.ctx = ctx;
        abrir();
    }


    public void abrir(){
        db = dbHelper.getWritableDatabase();
    }

    public void fechar(){
        db.close();
    }

    // Criar classe cadastro pessoa
    public void adicionarCadastroPessoa(CadastroPessoa c){
        ContentValues values = new ContentValues();
        values.put(COL_NOME, c.getNome());
        values.put(COL_EMAIL, c.getEmail());
        values.put(COL_SEXO, c.getSexo());
        values.put(COL_DATA, c.getDataNascimento().toString());
        values.put(COL_SENHA, c.getSenha());
        values.put(COL_RUA, c.getRua());
        values.put(COL_NUMERO, c.getNumero());
        values.put(COL_CIDADE, c.getCidade());
        db.insert(TAB_PESSOAS, null, values);
    }

    // Não precisa ?!?!?!?! (Ver melhor)
    public Integer deletarCadastroPessoa(int id){
        return db.delete(TAB_PESSOAS, COL_ID + "=" + id, null);
    }
    public Integer deletarCadastroPessoa(){
        return db.delete(TAB_PESSOAS, null, null);
    }

    private static class cadastroDBHelper extends SQLiteOpenHelper {

        public cadastroDBHelper(Context ctx, String nome, SQLiteDatabase.CursorFactory factory, int versao){
            super(ctx, nome, factory, versao);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_TAB_PESSOAS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TAB_PESSOAS);
            onCreate(db);
        }
    }

}
