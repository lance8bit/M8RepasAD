package com.example.m8repasad.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.m8repasad.db.IncidenciaContract.*;

import androidx.annotation.Nullable;

import com.example.m8repasad.Incidencia;

import java.util.ArrayList;

public class IncidenciaDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "incidencies.db";

    private static final String SQL_CREATE_ENTRIES ="CREATE TABLE " + IncidenciaEntry.TABLE_NAME + "( " + IncidenciaEntry.ID + " TEXT PRIMARY KEY, " + IncidenciaEntry.COLUMN_NAME_TITLE + " TEXT, " + IncidenciaEntry.COLUMN_NAME_PRIORITY + " TEXT)";
    private static final String SQL_GET_ALL_ENTRIES = "SELECT * FROM " + IncidenciaEntry.TABLE_NAME;

    public IncidenciaDBHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertIncidencia(SQLiteDatabase db, Incidencia incidencia){
        if(db.isOpen()){
            ContentValues contentValues = new ContentValues();
            contentValues.put(IncidenciaEntry.ID, incidencia.getNumIncidencia());
            contentValues.put(IncidenciaEntry.COLUMN_NAME_TITLE, incidencia.getTitleIncidencia());
            contentValues.put(IncidenciaEntry.COLUMN_NAME_PRIORITY, incidencia.getPriorityIncidencia());

            db.insert(IncidenciaEntry.TABLE_NAME, null, contentValues);
        }else{
            Log.d("SQL", "Database is closed");
        }
    }

    public ArrayList<Incidencia> getAllIncidencies(){
        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL_GET_ALL_ENTRIES, null);
        try{
            if(cursor.moveToFirst()){
                do {
                    Incidencia incidencia = new Incidencia(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                    incidencias.add(incidencia);
                } while (cursor.moveToNext());
            }
        } finally {
            try{cursor.close(); } catch (Exception ignore){}
        }

        return incidencias;
    }

}
