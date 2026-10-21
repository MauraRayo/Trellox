package com.proyecto.trellox;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText inputUsuario;
    private EditText inputPassword;
    private  BD_Helper helperbd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        inputUsuario =  findViewById(R.id.inputUsuario);
        inputPassword = findViewById(R.id.inputContra);

       helperbd = new BD_Helper(this);
    }
    public void login(View view) {
        String usuario  = inputUsuario.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();

        if(usuario.isEmpty()){
            Toast.makeText(this, "Introduce tu usuario", Toast.LENGTH_SHORT).show();
            return;
        }else if(password.isEmpty()){
            Toast.makeText(this, "Introduce tu contraseña", Toast.LENGTH_SHORT).show();
            return;
        }

        if(validarUsuario(usuario, password)){
            Intent intent = new Intent(this, PrincipalActivity.class);
            intent.putExtra("NOMBRE_USUARIO", usuario);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean validarUsuario(String usuario, String password){
        SQLiteDatabase db = helperbd.getReadableDatabase();

        String[] projection = {Estructura_BD.USUARIO_ID};

        String selection = Estructura_BD.USUARIO_NOMBRE   + " = ? AND " +
                           Estructura_BD.USUARIO_PASSWORD + " = ? ";

        String[] selectionArgs = {usuario, password};

        boolean existe = false;

        try {
            Cursor cursor = db.query(
              Estructura_BD.TABLA_USUARIOS,
              projection,
              selection,
              selectionArgs,
              null, null, null
            );

            if (cursor.getCount() > 0) {
                existe = true;
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
         return existe;
    }
}
