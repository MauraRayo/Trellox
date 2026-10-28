package com.proyecto.trellox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class PrincipalActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.principal);

        TextView bienvenidaPrincipal = findViewById(R.id.bienvenidaPrincipal);
        String usuario = getIntent().getStringExtra("NOMBRE_USUARIO");
        if (usuario != null && !usuario.isEmpty()) {
            bienvenidaPrincipal.setText(getString(R.string.bienvenido_usuario, usuario));
        }
    }

    public void mostrarTarea(View view ){
        Intent intent = new Intent(this, MostrarTareasActivity.class);
        startActivity(intent);
    }
    public void crearTareaModal(View view) {
        Intent intent = new Intent(this, CrearTareasActivity.class);
        startActivity(intent);
    }


}
