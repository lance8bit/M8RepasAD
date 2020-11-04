package com.example.m8repasad;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface IncidenciaDAO {

    @Query("SELECT * from incidencies")
    List<Incidencia> getAll();

    @Insert
     void insert(Incidencia incidencia);

    @Delete
    void delete(Incidencia incidencia);
}
