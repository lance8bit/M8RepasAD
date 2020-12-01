package com.example.m8repasad;

import java.io.Serializable;

public class Incidencia implements Serializable {
    private String numIncidencia;
    private String titleIncidencia;
    private String priorityIncidencia;
    private String descIncidencia;
    private int staIncidencia;
    private String dateIncidencia;

    public Incidencia(){

    }

    public Incidencia(String numIncidencia, String titleIncidencia, String priorityIncidencia, String descIncidencia, int staIncidencia, String dateIncidencia) {
        this.numIncidencia = numIncidencia;
        this.titleIncidencia = titleIncidencia;
        this.priorityIncidencia = priorityIncidencia;
        this.descIncidencia = descIncidencia;
        this.staIncidencia = staIncidencia;
        this.dateIncidencia = dateIncidencia;
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

    public String getDescIncidencia() {
        return descIncidencia;
    }

    public void setDescIncidencia(String descIncidencia) {
        this.descIncidencia = descIncidencia;
    }

    public int getStaIncidencia() {
        return staIncidencia;
    }

    public void setStaIncidencia(int staIncidencia) {
        this.staIncidencia = staIncidencia;
    }

    public String getDateIncidencia() {
        return dateIncidencia;
    }

    public void setDateIncidencia(String dateIncidencia) {
        this.dateIncidencia = dateIncidencia;
    }
}
