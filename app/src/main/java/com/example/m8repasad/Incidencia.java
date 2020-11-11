package com.example.m8repasad;

public class Incidencia {
    private String numIncidencia;
    private String titleIncidencia;
    private String priorityIncidencia;

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
