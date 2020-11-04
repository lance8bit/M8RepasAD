package com.example.m8repasad;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "incidencies")
public class Incidencia {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String numIncidencia;

    @NonNull
    @ColumnInfo(name = "title")
    private String titleIncidencia;

    @NonNull
    @ColumnInfo(name = "priority")
    private String priorityIncidencia;

    @Ignore
    public Incidencia(String numIncidencia, String titleIncidencia, String priorityIncidencia) {
        this.numIncidencia = numIncidencia;
        this.titleIncidencia = titleIncidencia;
        this.priorityIncidencia = priorityIncidencia;
    }

    public String getNumIncidencia() {
        return numIncidencia;
    }

    public void setNumIncidencia(String numIncidencia) {
        this.numIncidencia = numIncidencia;
    }

    public String getTitleIncidencia() {
        return titleIncidencia;
    }

    public void setTitleIncidencia(String titleIncidencia) {
        this.titleIncidencia = titleIncidencia;
    }

    public String getPriorityIncidencia() {
        return priorityIncidencia;
    }

    public void setPriorityIncidencia(String priorityIncidencia) {
        this.priorityIncidencia = priorityIncidencia;
    }
}
