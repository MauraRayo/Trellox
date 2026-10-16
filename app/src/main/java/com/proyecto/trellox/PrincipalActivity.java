package com.proyecto.trellox;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PrincipalActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.principal);
    }

    public void mostrarTarea(View view ){
        Intent intent = new Intent(this, MostrarTareasActivity.class);
        startActivity(intent);
    }
    public void crearTareaModal(View view) {
        Toast.makeText(PrincipalActivity.this, "¡Boton de crear areas!", Toast.LENGTH_SHORT).show();
    }


}
