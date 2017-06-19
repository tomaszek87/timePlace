package com.tomaszek.timeplace.database.tables;

public class TableGeofence {

    public static final String TABLE_GEOFENCE = "GEOFENCE";
    public static final String GEOFENCE_KEY_ID = "_id";
    public static final String GEOFENCE_KEY_GEOFENCE_ID = "geofenceId";
    public static final String GEOFENCE_KEY_NAME = "name";
    public static final String GEOFENCE_KEY_LAT = "lat";
    public static final String GEOFENCE_KEY_LONG = "long";


    public static final String CREATE_TABLE_GEOFENCE = "CREATE TABLE IF NOT EXISTS " + TABLE_GEOFENCE + "("
            + GEOFENCE_KEY_ID + " INTEGER PRIMARY KEY,"
            + GEOFENCE_KEY_GEOFENCE_ID + " TEXT,"
            + GEOFENCE_KEY_NAME + " TEXT,"
            + GEOFENCE_KEY_LAT + " TEXT,"
            + GEOFENCE_KEY_LONG + " TEXT"
            + ")";

    private TableGeofence(){

    }

}