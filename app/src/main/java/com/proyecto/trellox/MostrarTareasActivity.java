package com.proyecto.trellox;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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

    public void cargarTareas() {
        ArrayList<TareaItem> listaDeTareas = new ArrayList<>();
        SQLiteDatabase db = helperbd.getReadableDatabase();

        try (Cursor cursor = db.query(
                Estructura_BD.TABLA_TAREAS,
                new String[]{Estructura_BD.TAREA_ID, Estructura_BD.TAREA_TITULO, Estructura_BD.TAREA_FECHA},
                null, null, null, null, null
        )) {
            while (cursor.moveToNext()) {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(Estructura_BD.TAREA_ID));
                String titulo = cursor.getString(cursor.getColumnIndexOrThrow(Estructura_BD.TAREA_TITULO));
                String fecha = cursor.getString(cursor.getColumnIndexOrThrow(Estructura_BD.TAREA_FECHA));
                listaDeTareas.add(new TareaItem(id, titulo, fecha));
            }
        } catch (Exception e) {
            Log.e("ERROR", "Error al obtener tareas", e);
        }

        TareaAdapter adapter = new TareaAdapter(this, listaDeTareas, helperbd);
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

    public static class TareaItem {
        long id;
        String titulo;
        String fecha;

        public TareaItem(long id, String titulo, String fecha) {
            this.id = id;
            this.titulo = titulo;
            this.fecha = fecha;
        }
    }

    public static class TareaAdapter extends ArrayAdapter<TareaItem> {
        private final Context context;
        private final ArrayList<TareaItem> tareas;
        private final BD_Helper helperbd;

        public TareaAdapter(Context context, ArrayList<TareaItem> tareas, BD_Helper helperbd) {
            super(context, R.layout.item_tarea, tareas);
            this.context = context;
            this.tareas = tareas;
            this.helperbd = helperbd;
        }

        @SuppressLint("SetTextI18n")
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.item_tarea, parent, false);
            }

            TareaItem tarea = tareas.get(position);

            TextView txtTextoTarea = view.findViewById(R.id.txtTextoTarea);
            Button btnActualizar = view.findViewById(R.id.btnActualizar);
            Button btnBorrar = view.findViewById(R.id.btnBorrar);

            txtTextoTarea.setText(tarea.fecha + " -> " + tarea.titulo);

            btnBorrar.setOnClickListener(v -> {
                SQLiteDatabase db = helperbd.getWritableDatabase();
                db.delete(Estructura_BD.TABLA_TAREAS, Estructura_BD.TAREA_ID + " = ?", new String[]{String.valueOf(tarea.id)});
                Toast.makeText(context, "Tarea borrada", Toast.LENGTH_SHORT).show();
                ((MostrarTareasActivity) context).cargarTareas();
            });

            btnActualizar.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Actualizar Tarea");

                final EditText input = new EditText(context);
                input.setText(tarea.titulo);
                builder.setView(input);

                builder.setPositiveButton("Actualizar", (dialog, which) -> {
                    String nuevoTitulo = input.getText().toString().trim();
                    if (!nuevoTitulo.isEmpty()) {
                        SQLiteDatabase db = helperbd.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put(Estructura_BD.TAREA_TITULO, nuevoTitulo);
                        db.update(Estructura_BD.TABLA_TAREAS, values, Estructura_BD.TAREA_ID + " = ?", new String[]{String.valueOf(tarea.id)});
                        Toast.makeText(context, "Tarea actualizada", Toast.LENGTH_SHORT).show();
                        ((MostrarTareasActivity) context).cargarTareas();
                    } else {
                        Toast.makeText(context, "El título no puede estar vacío", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());
                builder.show();
            });

            return view;
        }
    }
}
