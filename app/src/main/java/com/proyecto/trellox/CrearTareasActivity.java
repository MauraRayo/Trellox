package com.proyecto.trellox;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CrearTareasActivity extends AppCompatActivity {

    private EditText inputTarea;
    private BD_Helper helperbd;
    private String fechaSeleccionada;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_tareas);

        inputTarea = findViewById(R.id.inputTarea);
        CalendarView calendarioCT = findViewById(R.id.calendarioCT);
        helperbd = new BD_Helper(this);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        fechaSeleccionada = sdf.format(calendar.getTime());

        calendarioCT.setOnDateChangeListener((view, dayOfMonth, month, year) -> {
            fechaSeleccionada = (month + 1) + "/" + dayOfMonth + "/" + year;
        });
    }

    public void guardarTarea(View view) {
        String titulo = inputTarea.getText().toString().trim();
        if (titulo.isEmpty()) {
            Toast.makeText(this, "Introduce un título para la tarea", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = helperbd.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Estructura_BD.TAREA_TITULO, titulo);
        values.put(Estructura_BD.TAREA_FECHA, fechaSeleccionada);

        long newRowId = db.insert(Estructura_BD.TABLA_TAREAS, null, values);
        if (newRowId != -1) {
            Toast.makeText(this, "Tarea guardada correctamente", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error al guardar la tarea", Toast.LENGTH_SHORT).show();
        }
    }
}
