package com.example.a1514290069.contatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 1514290069 on 02/03/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String BANCO_DADOS = "BancoDados.db";
    private static int VERSAO = 1;


        public DatabaseHelper(Context context) {

            super(context, BANCO_DADOS, null,VERSAO);

        }

        @Override

        public void onCreate(SQLiteDatabase db){

            db.execSQL("CREATE TABLE usuario (_id INTEGER PRIMARY KEY, nome TEXT, endereco TEXT, telefone TEXT, email TEXT, senha TEXT )");
        }
        @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        }
}
