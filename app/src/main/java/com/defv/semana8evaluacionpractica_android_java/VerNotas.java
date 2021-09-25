package com.defv.semana8evaluacionpractica_android_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class VerNotas extends AppCompatActivity {

    private EditText editTitulo, editDesc, editAutor;
    private String titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_notas);

        editTitulo = findViewById(R.id.editTitulo);
        editDesc = findViewById(R.id.editDesc);
        editAutor = findViewById(R.id.editAutor);

        Bundle objeto = getIntent().getExtras();

        String notas = getIntent().getStringExtra("titulo");

        editTitulo.setText(notas);
        titulo = editTitulo.getText().toString();

        AdminSQLite admin = new AdminSQLite(this, "EvalNotas.db", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor fila = bd.rawQuery("select descripcion, autor from tb_notas where titulo = '" + titulo + "'"  , null);
        if(fila.moveToFirst()) {
            editDesc.setText(fila.getString(0));
            editAutor.setText(fila.getString(1));
        }
    }

    public void editar(View view) {
        AdminSQLite admin = new AdminSQLite(this, "EvalNotas.db", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String tirulo = editTitulo.getText().toString();
        String desc = editDesc.getText().toString();
        String autor = editAutor.getText().toString();


        ContentValues registro = new ContentValues();
        registro.put("titulo", tirulo);
        registro.put("descripcion", desc);
        registro.put("autor", autor);

        int actualiza = bd.update("tb_notas", registro, "titulo = '" + titulo + "'", null);
        bd.close();
        if (actualiza == 1) {

            Toast.makeText(this, "Registro actualizado", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void borrar(View view) {
        AdminSQLite admin = new AdminSQLite(this, "EvalNotas.db", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String tirulo = editTitulo.getText().toString();

        int borra = bd.delete("tb_notas", "titulo = '" + titulo + "'", null);
        bd.close();

        Toast.makeText(this, "Nota eliminada", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(VerNotas.this, MainActivity.class);
        startActivity(intent);
    }

    public void atras(View view) {
        finish();
    }
}