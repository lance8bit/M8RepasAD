package com.example.m8repasad;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Incidencia.class}, version = 1)
public abstract class IncidenciaDatabase extends RoomDatabase {

    private static volatile IncidenciaDatabase incidenciaDatabaseInstance;

    public IncidenciaDAO incidenciaDAO;

    public abstract IncidenciaDatabase getIncidenciaDatabaseInstance(Context context){

        if(incidenciaDatabaseInstance == null){
            incidenciaDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    Incidencia.class,"m8repas.db")
                    .build();
        }

        return incidenciaDatabaseInstance;
    }

}
