package com.proyecto.trellox;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MostrarTareasActivity extends AppCompatActivity {

    private ListView listViewTareas;
    private BD_Helper helperbd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_tareas);

        listViewTareas = findViewById(R.id.listViewTareas);
        helperbd = new BD_Helper(this);

        cargarTareas();
    }

    private void cargarTareas() {
        ArrayList<String> listaTareas = new ArrayList<>();
        SQLiteDatabase db = helperbd.getReadableDatabase();

        try (Cursor cursor = db.query(
                Estructura_BD.TABLA_TAREAS,
                new String[]{Estructura_BD.TAREA_TITULO, Estructura_BD.TAREA_FECHA},
                null, null, null, null, null
        )) {
            while (cursor.moveToNext()) {
                String titulo = cursor.getString(cursor.getColumnIndexOrThrow(Estructura_BD.TAREA_TITULO));
                String fecha = cursor.getString(cursor.getColumnIndexOrThrow(Estructura_BD.TAREA_FECHA));
                listaTareas.add(titulo + " (" + fecha + ")");
            }
        } catch (Exception e) {
            // Ignored or logged
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listaTareas
        );
        listViewTareas.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarTareas();
    }

    public void volver(View view) {
        finish();
    }
}
