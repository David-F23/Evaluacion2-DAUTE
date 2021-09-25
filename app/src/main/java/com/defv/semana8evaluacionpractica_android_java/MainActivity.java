package com.defv.semana8evaluacionpractica_android_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtTitulo, txtDesc, txtAutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTitulo = findViewById(R.id.txtTitulo);
        txtDesc = findViewById(R.id.txtDesc);
        txtAutor = findViewById(R.id.txtAutor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mi_menu,menu);
        return true;
    }

    public void guardar(View view) {

        AdminSQLite admin = new AdminSQLite(this, "EvalNotas.db", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String titulo = txtTitulo.getText().toString();
        String desc = txtDesc.getText().toString();
        String autor = txtAutor.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("titulo", titulo);
        registro.put("descripcion", desc);
        registro.put("autor", autor);

        bd.insert("tb_notas", null, registro);
        bd.close();

        txtTitulo.setText(null);
        txtDesc.setText(null);
        txtAutor.setText(null);

        Toast.makeText(this, "Guardado con exito!", Toast.LENGTH_SHORT).show();
    }

    public void verNotas(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, ListView.class);
        startActivity(intent);
    }
}