package com.example.m8repasad.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.m8repasad.Incidencia;
import com.example.m8repasad.db.IncidenciaContract.IncidenciaEntry;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class IncidenciaDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "incidencies.db";

    private static final String SQL_GET_ALL_ENTRIES = "SELECT * FROM " + IncidenciaEntry.TABLE_NAME;
    private static final String SQL_CREATE_ENTRIES ="CREATE TABLE " + IncidenciaEntry.TABLE_NAME + "( " + IncidenciaEntry.ID + " TEXT PRIMARY KEY, " + IncidenciaEntry.COLUMN_NAME_TITLE + " TEXT, " + IncidenciaEntry.COLUMN_NAME_PRIORITY + " TEXT, " + IncidenciaEntry.COLUMN_NAME_DESC + " TEXT, "+IncidenciaEntry.COLUMN_NAME_STATUS + " TEXT, " + IncidenciaEntry.COLUMN_NAME_DATE + " TEXT )";

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
            contentValues.put(IncidenciaEntry.COLUMN_NAME_DESC, incidencia.getDescIncidencia());
            contentValues.put(IncidenciaEntry.COLUMN_NAME_STATUS, incidencia.getStaIncidencia());
            contentValues.put(IncidenciaEntry.COLUMN_NAME_DATE, incidencia.getDateIncidencia());

            db.insert(IncidenciaEntry.TABLE_NAME, null, contentValues);
        }else{
            Log.d("SQL", "Database is closed");
        }
    }

    public void dropAllIncidencies(SQLiteDatabase db){
        db.execSQL("DELETE FROM " + IncidenciaEntry.TABLE_NAME);
    }

    public ArrayList<Incidencia> getAllIncidencies(){
        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SQL_GET_ALL_ENTRIES, null);
        try{
            if(cursor.moveToFirst()){
                do {
                    Incidencia incidencia = new Incidencia(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), Integer.valueOf(cursor.getString(4)), cursor.getString(5));
                    incidencias.add(incidencia);
                } while (cursor.moveToNext());
            }
        } finally {
            try{cursor.close(); } catch (Exception ignore){}
        }

        return incidencias;
    }

    public Incidencia getIncidencia(String id){
        Incidencia incidencia = new Incidencia();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM incidencia WHERE numIncidencia = ?", new String[] {id});

        try{
            if(cursor.moveToFirst()){
                incidencia = new Incidencia(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), Integer.valueOf(cursor.getString(4)), cursor.getString(5));
            }
        } finally {
            try {
                cursor.close();
            }catch (Exception ignore){}
        }
        return incidencia;
    }

    public void deleteIncidencia(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(IncidenciaEntry.TABLE_NAME, "numIncidencia = ?", new String[] {id});
    }

    public void changeStatus(SQLiteDatabase db, String numInc, int status){
        ContentValues contentValues = new ContentValues();

        //Content value con el valor a actualizar
        contentValues.put(IncidenciaEntry.COLUMN_NAME_STATUS, Integer.toString(status));

        //Where clause
        String[] args = new String [] { numInc };
        db.update(IncidenciaEntry.TABLE_NAME, contentValues, "numIncidencia=?", args);
    }

    public ArrayList<Incidencia> getFilteredIncidencias(String status){
        ArrayList<Incidencia> incidencias_filtered = new ArrayList<Incidencia>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM incidencia WHERE priorityIncidencia = ?", new String[] {status});

        try{
            if(cursor.moveToFirst()){
                do {
                    Incidencia incidencia = new Incidencia(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), Integer.valueOf(cursor.getString(4)), cursor.getString(5));
                    incidencias_filtered.add(incidencia);
                } while (cursor.moveToNext());
            }
        } finally {
            try{cursor.close(); } catch (Exception ignore){}
        }

        return incidencias_filtered;
    }

}
