package com.example.dam2.ejercicio5a;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 2dam on 09/12/2015.
 */
public class miDBAdaptador {
    // Definiciones y constantes
    private static final String DATABASE_NAME = "Colegio.db";
    private static final String DATABASE_TABLE1 = "alumnos";
    private static final String DATABASE_TABLE2 = "profesores";
    private static final int DATABASE_VERSION = 1;

    private static final String NOMBRE = "nombre";
    private static final String EDAD = "edad";
    private static final String CICLO = "ciclo";
    private static final String CURSO = "curso";
    private static final String NOTA = "nota";

    private static final String NOMBRE_P = "nombre";
    private static final String EDAD_P = "edad";
    private static final String CICLO_p = "ciclo";
    private static final String CURSO_P = "curso";
    private static final String DESPACHO = "despacho";



    private static final String TITLE = "title";
    private static final String YEAR = "year";

    private static final String DATABASE_CREATE1="CREATE TABLE"+DATABASE_TABLE1+" (_id integer primary key autoincrement, nombre text, edad integer, ciclo text, curso integer, nota integer); ";
    private static final String DATABASE_CREATE2="CREATE TABLE"+DATABASE_TABLE2+" (_id integer primary key autoincrement, nombre text, edad integer, ciclo text, curso integer, despacho text); ";
    private static final String DATABASE_CREATE = "CREATE TABLE "+DATABASE_TABLE1+" (_id integer primary key autoincrement, title text, year integer);";
    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS "+DATABASE_TABLE1+";";
    private static final String DATABASE_DROP1="DROP TABLE IF EXISTS "+DATABASE_TABLE1+";";
    private static final String DATABASE_DROP2="DROP TABLE IF EXISTS "+DATABASE_TABLE2+";";
    // Contexto de la aplicación que usa la base de datos
    private final Context context;
    // Clase SQLiteOpenHelper para crear/actualizar la base de datos
    private MyDbHelper dbHelper;
    // Instancia de la base de datos
    private SQLiteDatabase db;

    public miDBAdaptador (Context c){
        context = c;
        dbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        //OJO open();
    }

    public void open(){

        try{
            db = dbHelper.getWritableDatabase();
        }catch(SQLiteException e){
            db = dbHelper.getReadableDatabase();
        }

    }

    public void insertarDisco(String t, int y){
        //Creamos un nuevo registro de valores a insertar
        ContentValues newValues = new ContentValues();
        //Asignamos los valores de cada campo
        newValues.put(TITLE,t);
        newValues.put(YEAR,y);
        db.insert(DATABASE_TABLE1, null, newValues);
    }

    public void insertarAlumno(String n, int e, String ci, int cu, int not){
        ContentValues nuevosValores=new ContentValues();
        nuevosValores.put(NOMBRE,n);
        nuevosValores.put(EDAD,e);
        nuevosValores.put(CICLO,ci);
        nuevosValores.put(CURSO,cu);
        nuevosValores.put(NOTA,not);
        db.insert(DATABASE_TABLE1,null,nuevosValores);
    }

    public void insertarProfesor(String n, int e, String ci, int cu, String d ){
        ContentValues nuevosValores=new ContentValues();
        nuevosValores.put(NOMBRE_P,n);
        nuevosValores.put(EDAD_P,e);
        nuevosValores.put(CICLO_p,ci);
        nuevosValores.put(CURSO_P,cu);
        nuevosValores.put(DESPACHO, d);
        db.insert(DATABASE_TABLE2, null, nuevosValores);
    }
    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context,name,factory,version);
        }

        @Override
       // public void onCreate(SQLiteDatabase db) {
        //    db.execSQL(DATABASE_CREATE);
        //}
        public void onCreate(SQLiteDatabase db){
            db.execSQL(DATABASE_CREATE1);
            db.execSQL(DATABASE_CREATE2);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DATABASE_DROP);
            onCreate(db);
        }

    }
}
