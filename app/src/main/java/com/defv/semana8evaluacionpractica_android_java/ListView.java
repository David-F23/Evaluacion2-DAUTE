package com.defv.semana8evaluacionpractica_android_java;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ListView extends AppCompatActivity {

    android.widget.ListView listaNotas;
    ArrayList<String> notas;
    ArrayAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listaNotas = findViewById(R.id.listNotas);

        AdminSQLite admin = new AdminSQLite(this, "EvalNotas.db", null, 1);
        notas = admin.llenar();
        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notas);
        listaNotas.setAdapter(adaptador);
        listaNotas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(ListView.this, VerNotas.class);
                String notes = (String) listaNotas.getItemAtPosition(i);
                intent.putExtra("titulo", notes);
                startActivity(intent);
            }
        });
    }

    public void atras(View view) {
        finish();
    }
}