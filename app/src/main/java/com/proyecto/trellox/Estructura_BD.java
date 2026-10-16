package com.proyecto.trellox;
public class Estructura_BD {
    private Estructura_BD() {}

    // --------- Tabla Usuarios ---------
    public static final String TABLA_USUARIOS = "Usuarios";
    public static final String USUARIO_ID = "Id";
    public static final String USUARIO_NOMBRE = "Usuario";
    public static final String USUARIO_PASSWORD = "Password";

    public static final String SQL_CREATE_USUARIOS =
            "CREATE TABLE " + TABLA_USUARIOS + " (" +
                    USUARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    USUARIO_NOMBRE + " TEXT UNIQUE," +
                    USUARIO_PASSWORD + " TEXT)";

    public static final String SQL_DELETE_USUARIOS =
            "DROP TABLE IF EXISTS " + TABLA_USUARIOS;


    // --------- Tabla Tareas  ---------
    public static final String TABLA_TAREAS = "Tareas";
    public static final String TAREA_ID = "Id";
    public static final String TAREA_TITULO = "Titulo";
    public static final String TAREA_FECHA = "Fecha";

    public static final String SQL_CREATE_TAREAS =
            "CREATE TABLE " + Estructura_BD.TABLA_TAREAS + " (" +
                    Estructura_BD.TAREA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Estructura_BD.TAREA_TITULO + " TEXT," +
                    Estructura_BD.TAREA_FECHA + " TEXT)";

    public static final String SQL_DELETE_TAREAS =
            "DROP TABLE IF EXISTS " + Estructura_BD.TABLA_TAREAS;
}