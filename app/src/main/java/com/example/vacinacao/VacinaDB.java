package com.example.vacinacao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VacinaDB {
    /*
     * Definição do banco de dados
     */
    private static final String NOME_BANCO = "vacina.db";
    private static final String TAB_VACINA = "VACINA";
    private static final int VERSAO_BANCO = 10;
    /*
     * Declaração das colunas
     */
    private static final String COL_ID       = "ID";
    private static final String COL_LABORATORIO     = "LABORATORIO";
    private static final String COL_LOTE     = "LOTE";
    private static final String COL_VALIDADE     = "VALIDADE";
    private static final String COL_DATAAPLICACAO     = "DATAAPLICACAO";
    //private static final String COL_SEXO     = "SEXO";
    private static final String SQL_TAB_VACINA = "create table " + TAB_VACINA +
            "(" + COL_ID     + " integer primary key autoincrement," +
            COL_LABORATORIO + " text not null," +
            COL_LOTE + " text not null," +
            COL_VALIDADE + " text not null," +
            //COL_PESO + " text not null," +
            COL_DATAAPLICACAO + " text not null)";
    private com.example.vacinacao.VacinaDB.vacinaDBHelper dbHelper;
    private SQLiteDatabase db;
    private Context ctx;
    /*
     * Construtor do banco de dados de cadastro de pessoa
     */
    public VacinaDB(Context ctx){
        dbHelper = new com.example.vacinacao.VacinaDB.vacinaDBHelper(ctx, NOME_BANCO, null, VERSAO_BANCO);
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
    public void adicionarVacina(Vacina cc){
        deletarVacina();
        ContentValues values = new ContentValues();
        values.put(COL_LABORATORIO, cc.getLaboratorio());
        values.put(COL_LOTE, cc.getLote());
        values.put(COL_VALIDADE, cc.getValidade().toString());
        values.put(COL_DATAAPLICACAO, cc.getDataAplicacao().toString());
        //values.put(COL_SEXO, cc.getSexo());
        //db.insert(TAB_CAES, null, values);

        // Insere o registro no banco de dados
        long resultado = db.insert(TAB_VACINA, null, values);
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
    public void alterarVacina(Vacina cc){
        ContentValues values = new ContentValues();
        values.put(COL_LABORATORIO, cc.getLaboratorio());
        values.put(COL_LOTE, cc.getLote());
        //System.out.println(cc.getPeso());
        db.update(TAB_VACINA,values,null,null);
        fechar();
    }
    /*
     * Carrega cão cadastrado no banco de dados
     */
    public Vacina carregaVacina(){
        Vacina v = new Vacina();
        // Colunas a buscar no banco
        String[] colunas = {COL_LABORATORIO, COL_LOTE, COL_VALIDADE, COL_DATAAPLICACAO};
        Cursor cursor = db.query(TAB_VACINA, colunas, null, null, null, null, null);
        System.out.println(cursor);
        // Se existe registro no banco
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            v = new Vacina();
            v.setLaboratorio(cursor.getString(cursor.getColumnIndex(COL_LABORATORIO)));
            v.setLote(cursor.getString(cursor.getColumnIndex(COL_LOTE)));

            String data = cursor.getString(cursor.getColumnIndex(COL_VALIDADE));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dataV = null;

            try {
                dataV = dateFormat.parse(data);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            v.setValidade(dataV);
            //Data aplicação
            dataV = null;
            try {
                dataV = dateFormat.parse(data);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            v.setDataAplicacao(dataV);
        }
        fechar();
        return v;
    }
    /*
     * Apaga um cadastro de cão do banco de dadoss
     */
    public Integer deletarVacina(int id){
        db = dbHelper.getReadableDatabase();
        return db.delete(TAB_VACINA, COL_ID + "=" + id, null);
    }
    public Integer deletarVacina(){
        db = dbHelper.getReadableDatabase();
        return db.delete(TAB_VACINA, null, null);
    }

    private static class vacinaDBHelper extends SQLiteOpenHelper {

        public vacinaDBHelper(Context ctx, String nome, SQLiteDatabase.CursorFactory factory, int versao){
            super(ctx, nome, factory, versao);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_TAB_VACINA);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TAB_VACINA);
            onCreate(db);
        }
    }
}
