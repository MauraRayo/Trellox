package com.proyecto.trellox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);
    }

    public void login(View view) {
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }
}
