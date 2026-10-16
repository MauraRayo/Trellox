package com.proyecto.trellox;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BD_Helper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TrelloxBD";

    public BD_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Estructura_BD.SQL_CREATE_USUARIOS);
        db.execSQL(Estructura_BD.SQL_CREATE_TAREAS);

        ContentValues values = new ContentValues();
        values.put(Estructura_BD.COLUMNA_USUARIO_NOMBRE, "Mau");
        values.put(Estructura_BD.COLUMNA_USUARIO_PASSWORD, "1234");
        db.insert(Estructura_BD.NOMBRE_TABLA_USUARIOS, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Estructura_BD.SQL_DELETE_USUARIOS);
        db.execSQL(Estructura_BD.SQL_DELETE_TAREAS);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
