package com.example.m8repasad;

import java.util.ArrayList;

public class singletonIncidencias {

    private static singletonIncidencias newInstance;
    private ArrayList<Incidencia> listIncidencias = null;

    public static singletonIncidencias getNewInstance(){
        if (newInstance == null)
            newInstance = new singletonIncidencias();

        return newInstance;
    }

    private singletonIncidencias(){
        listIncidencias = new ArrayList<Incidencia>();
        //listIncidencias.add(new Incidencia("INCDEFAULT", "ERROR 303", "High"));
    }

    public ArrayList<Incidencia> getListIncidencias(){
        return this.listIncidencias;
    }

    public void addToListIncidencias(Incidencia nIncidencia){
        listIncidencias.add(nIncidencia);
    }

    public static void removeEntries() {
        newInstance = new singletonIncidencias();
    }

    public void addArrayList(ArrayList<Incidencia> incidencias){
        listIncidencias = incidencias;
    }
}
