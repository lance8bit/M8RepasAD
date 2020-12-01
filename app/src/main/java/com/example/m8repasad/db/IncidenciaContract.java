package com.example.m8repasad.db;

import android.provider.BaseColumns;

public class IncidenciaContract {

    public static abstract class IncidenciaEntry implements BaseColumns {
        public static final String TABLE_NAME = "incidencia";
        public static final String ID = "numIncidencia";
        public static final String COLUMN_NAME_TITLE = "titleIncidencia";
        public static final String COLUMN_NAME_PRIORITY = "priorityIncidencia";
        public static final String COLUMN_NAME_DESC = "descIncidencia";
        public static final String COLUMN_NAME_STATUS = "staIncidencia";
        public static final String COLUMN_NAME_DATE = "dateIncidencia";

    }
}
