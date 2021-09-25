package com.defv.semana8evaluacionpractica_android_java;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class AdminSQLite extends SQLiteOpenHelper {
    public AdminSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("create table tb_notas(id INTEGER PRIMARY KEY AutoIncrement, titulo text, descripcion text, autor text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int i1) {
        bd.execSQL("drop table if exists tb_notas");
    }

    public ArrayList llenar(){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase bd = this.getWritableDatabase();

        String SQL = "select * from tb_notas";
        Cursor registros = bd.rawQuery(SQL, null);

        if(registros.moveToFirst()){
            do {
                lista.add(registros.getString(1));
            }while(registros.moveToNext());
        }

        return lista;
    }

}
