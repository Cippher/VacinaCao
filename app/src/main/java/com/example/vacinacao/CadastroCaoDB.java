package com.example.vacinacao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CadastroCaoDB {
        /*
         * Definição do banco de dados
         */
        private static final String NOME_BANCO = "caes.db";
        private static final String TAB_CAES = "CAES";
        private static final int VERSAO_BANCO = 10;
        /*
         * Declaração das colunas
         */
        private static final String COL_ID       = "ID";
        private static final String COL_NOME     = "NOME";
        private static final String COL_RACA     = "RACA";
        private static final String COL_DATA     = "DATA";
        private static final String COL_PESO     = "PESO";
        private static final String COL_SEXO     = "SEXO";
        private static final String SQL_TAB_CAES = "create table " + TAB_CAES +
                "(" + COL_ID     + " integer primary key autoincrement," +
                      COL_NOME + " text not null," +
                      COL_RACA + " text not null," +
                      COL_DATA + " text not null," +
                      COL_PESO + " text not null," +
                      COL_SEXO + " text not null)";
        private com.example.vacinacao.CadastroCaoDB.cadastroDBHelper dbHelper;
        private SQLiteDatabase db;
        private Context ctx;
        /*
         * Construtor do banco de dados de cadastro de pessoa
         */
        public CadastroCaoDB(Context ctx){
            dbHelper = new com.example.vacinacao.CadastroCaoDB.cadastroDBHelper(ctx, NOME_BANCO, null, VERSAO_BANCO);
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
         * Adiciona um novo cão no banco de dados
         */
        public void adicionarCadastroCao(CadastroCao cc){
            ContentValues values = new ContentValues();
            values.put(COL_NOME, cc.getNome());
            values.put(COL_RACA, cc.getRaca());
            values.put(COL_DATA, cc.getDataNascimento().toString());
            values.put(COL_PESO, cc.getPeso());
            values.put(COL_SEXO, cc.getSexo());
            //db.insert(TAB_CAES, null, values);

            // Insere o registro no banco de dados
            long resultado = db.insert(TAB_CAES, null, values);
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
         * Altera o cadastro de cao
         */
        public void alterarCadastroCao(CadastroCao cc){
            ContentValues values = new ContentValues();
            values.put(COL_PESO, cc.getPeso());
            values.put(COL_NOME, cc.getNome());
            System.out.println(cc.getPeso());
            db.update(TAB_CAES,values,null,null);
            fechar();
        }
        /*
<<<<<<< HEAD
         * Carrega cão cadastrado no banco de dados
=======
         * Carrega cadastro cao
>>>>>>> 442ef6f63fe0d72c868540f56740ec39e9fe6821
         */
        public CadastroCao carregaCadastroCao(){
            CadastroCao cao = new CadastroCao();
            // Colunas a buscar no banco
            String[] colunas = {COL_PESO, COL_NOME};
            Cursor cursor = db.query(TAB_CAES, colunas, null, null, null, null, null);
            System.out.println(cursor);
            // Se existe registro no banco
            if (cursor.getCount() != 0){
                cursor.moveToFirst();
                cao = new CadastroCao();
                cao.setPeso(cursor.getFloat(cursor.getColumnIndex(COL_PESO)));
                cao.setNome(cursor.getString(cursor.getColumnIndex(COL_NOME)));
            }
            fechar();
            return cao;
        }
        /*
         * Apaga um cadastro de cão do banco de dadoss
         */
        public Integer deletarCadastroCao(int id){
            db = dbHelper.getReadableDatabase();
            return db.delete(TAB_CAES, COL_ID + "=" + id, null);
        }
        public Integer deletarCadastroCao(){
            db = dbHelper.getReadableDatabase();
            return db.delete(TAB_CAES, null, null);
        }

        private static class cadastroDBHelper extends SQLiteOpenHelper {

            public cadastroDBHelper(Context ctx, String nome, SQLiteDatabase.CursorFactory factory, int versao){
                super(ctx, nome, factory, versao);
            }
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(SQL_TAB_CAES);
            }
            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + TAB_CAES);
                onCreate(db);
            }
        }
}
