package com.proyecto.trellox;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MostrarTareasActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mostrar_tareas);
    }

    public void volver(View view) {
        finish();
    }
}
