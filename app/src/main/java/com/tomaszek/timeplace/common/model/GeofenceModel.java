package com.tomaszek.timeplace.common.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.tomaszek.timeplace.database.tables.TableGeofence;

public class GeofenceModel {

    private String geofenceId;
    private Double latitude;
    private Double longitude;

    public GeofenceModel(String geofenceId, Double latitude, Double longitude) {
        this.geofenceId = geofenceId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(TableGeofence.GEOFENCE_KEY_GEOFENCE_ID, getGeofenceId());
        values.put(TableGeofence.GEOFENCE_KEY_LAT, getLatitude());
        values.put(TableGeofence.GEOFENCE_KEY_LONG, getLongitude());
        return values;
    }

    public static GeofenceModel fromCursor(Cursor res) {
        return new GeofenceModel(res.getString(res.getColumnIndex(TableGeofence.GEOFENCE_KEY_GEOFENCE_ID)),
                Double.valueOf(res.getString(res.getColumnIndex(TableGeofence.GEOFENCE_KEY_LAT))),
                Double.valueOf(res.getString(res.getColumnIndex(TableGeofence.GEOFENCE_KEY_LONG))));
    }

    public String getGeofenceId() {
        return geofenceId;
    }

    public void setGeofenceId(String geofenceId) {
        this.geofenceId = geofenceId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
