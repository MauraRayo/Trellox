package com.proyecto.trellox;
public class Estructura_BD {
    private Estructura_BD() {}

    // --------- Tabla Usuarios ---------
    public static final String NOMBRE_TABLA_USUARIOS = "Usuarios";
    public static final String COLUMNA_USUARIO_ID = "Id";
    public static final String COLUMNA_USUARIO_NOMBRE = "Usuario";
    public static final String COLUMNA_USUARIO_PASSWORD = "Password";

    public static final String SQL_CREATE_USUARIOS =
            "CREATE TABLE " + NOMBRE_TABLA_USUARIOS + " (" +
                    COLUMNA_USUARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMNA_USUARIO_NOMBRE + " TEXT UNIQUE," +
                    COLUMNA_USUARIO_PASSWORD + " TEXT)";

    public static final String SQL_DELETE_USUARIOS =
            "DROP TABLE IF EXISTS " + NOMBRE_TABLA_USUARIOS;


    // --------- Tabla Tareas  ---------
    public static final String NOMBRE_TABLA_TAREAS = "Tareas";
    public static final String NOMBRE_TAREA_ID = "Id";
    public static final String NOMBRE_TAREA_TITULO = "Titulo";
    public static final String NOMBRE_TAREA_FECHA = "Fecha";

    public static final String SQL_CREATE_TAREAS =
            "CREATE TABLE " + Estructura_BD.NOMBRE_TABLA_TAREAS + " (" +
                    Estructura_BD.NOMBRE_TAREA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Estructura_BD.NOMBRE_TAREA_TITULO + " TEXT," +
                    Estructura_BD.NOMBRE_TAREA_FECHA + " TEXT)";

    public static final String SQL_DELETE_TAREAS =
            "DROP TABLE IF EXISTS " + Estructura_BD.NOMBRE_TABLA_TAREAS;
}