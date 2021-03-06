package com.example.vacinacao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CadastroPessoaDB{
    /*
     * Definição do banco de dados
     */
    private static final String NOME_BANCO = "pessoas.db";
    private static final String TAB_PESSOAS = "PESSOAS";
    private static final int VERSAO_BANCO = 10;
    /*
     * Declaração das colunas
     */
    private static final String COL_ID     = "ID";
    private static final String COL_NOME   = "NOME";
    private static final String COL_EMAIL  = "EMAIL";
    private static final String COL_SEXO   = "SEXO";
    private static final String COL_DATA   = "DATA";
    private static final String COL_SENHA  = "SENHA";
    private static final String COL_RUA    = "RUA";
    private static final String COL_NUMERO = "NUMERO";
    private static final String COL_CIDADE = "CIDADE";
    private static final String SQL_TAB_PESSOAS = "create table " + TAB_PESSOAS +
            "(" + COL_ID     + " integer primary key autoincrement," +
                  COL_NOME   + " text not null," +
                  COL_EMAIL  + " text not null," +
                  COL_SEXO   + " text not null," +
                  COL_DATA   + " text not null," +
                  COL_SENHA  + " text not null," +
                  COL_RUA    + " text not null," +
                  COL_NUMERO + " text not null," +
                  COL_CIDADE + " text not null)";
    private cadastroDBHelper dbHelper;
    private SQLiteDatabase db;
    private Context ctx;
    /*
     * Construtor do banco de dados de cadastro de pessoa
     */
    public CadastroPessoaDB(Context ctx){
        dbHelper = new cadastroDBHelper(ctx, NOME_BANCO, null, VERSAO_BANCO);
        this.ctx = ctx;
        abrir();
    }
    /*
     * Abre o banco de dados para inclusão/alteração
     */
    public void abrir(){
        db = dbHelper.getWritableDatabase();
    }
    /*
     * Fecha o banco de dados
     */
    public void fechar(){
        db.close();
    }
    /*
     * Adiciona uma nova pessoa no banco de dados
     */
    public void adicionarCadastroPessoa(CadastroPessoa cp){
        ContentValues values = new ContentValues();
        values.put(COL_NOME, cp.getNome());
        values.put(COL_EMAIL, cp.getEmail());
        values.put(COL_SEXO, cp.getSexo());
        values.put(COL_DATA, cp.getDataNascimento().toString());
        values.put(COL_SENHA, cp.getSenha());
        values.put(COL_RUA, cp.getRua());
        values.put(COL_NUMERO, Integer.toString(cp.getNumero()));
        values.put(COL_CIDADE, cp.getCidade());
        //db.insert(TAB_PESSOAS, null, values);

        // Insere o registro no banco de dados
        long resultado = db.insert(TAB_PESSOAS, null, values);
        // Fecha o banco de dados
        fechar();
        // Se houve erro na inclusão do cadastro no banco de dados
        if (resultado == -1){
            System.out.println("Erro ao incluir registro!");
        }else {
            System.out.println("Registro inserido com sucesso!");
        }
    }
    /*
     * Altera o cadastro de pessoa
     */
    public void alterarCadastroPessoa(CadastroPessoa c){
        ContentValues values = new ContentValues();
        values.put(COL_RUA, c.getRua());
        values.put(COL_NUMERO, Integer.toString(c.getNumero()));
        values.put(COL_CIDADE, c.getCidade());
        System.out.println(c.getRua() + c.getNumero() + c.getCidade());
        db.update(TAB_PESSOAS,values,null,null);
        fechar();
    }
    /*
     * Carrega pessoa cadastrada no banco de dados
     */
    public CadastroPessoa carregaCadastroPessoa(){
        CadastroPessoa pessoa = new CadastroPessoa();
        // Colunas a buscar no banco
        String[] colunas = {COL_RUA, COL_NUMERO, COL_CIDADE, COL_EMAIL, COL_SENHA};
        Cursor cursor = db.query(TAB_PESSOAS, colunas, null, null, null, null, null);
        System.out.println(cursor);
        // Se existe registro no banco
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            pessoa = new CadastroPessoa();
            pessoa.setRua(cursor.getString(cursor.getColumnIndex(COL_RUA)));
            pessoa.setNumero(cursor.getInt(cursor.getColumnIndex(COL_NUMERO)));
            pessoa.setCidade(cursor.getString(cursor.getColumnIndex(COL_CIDADE)));
            pessoa.setEmail(cursor.getString(cursor.getColumnIndex(COL_EMAIL)));
            pessoa.setSenha(cursor.getString(cursor.getColumnIndex(COL_SENHA)));
        }
        fechar();
        return pessoa;
    }
    /*
     * Apaga um cadastro de pessoa do banco de dados
     */
    public Integer deletarCadastroPessoa(int id){
        db = dbHelper.getReadableDatabase();
        return db.delete(TAB_PESSOAS, COL_ID + "=" + id, null);
    }
    public Integer deletarCadastroPessoa(){
        db = dbHelper.getReadableDatabase();
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