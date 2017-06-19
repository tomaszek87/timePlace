package com.tomaszek.timeplace.database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tomaszek.timeplace.common.model.GeofenceModel;
import com.tomaszek.timeplace.database.tables.TableGeofence;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "QUALITY";

    private static DatabaseHandler instance = null;

    private DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        onStart();
    }

    public static DatabaseHandler getInstance(Context ctx) {
        if (instance == null) {
            instance = new DatabaseHandler(ctx.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TableGeofence.CREATE_TABLE_GEOFENCE);
    }

    private boolean isTableExists(final SQLiteDatabase db, final String tableName) {
        if (tableName == null || db == null || !db.isOpen()) {
            return false;
        }
        final Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[]{"table", tableName});
        if (!cursor.moveToFirst()) {
            return false;
        }
        final int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void onStart() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TableGeofence.TABLE_GEOFENCE, null, null);
        onCreate(db);
    }

    public boolean insertGeofence(GeofenceModel model) {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.i("DATABASE", "inserting article");
        db.insert(TableGeofence.TABLE_GEOFENCE, null, model.getContentValues());
        return true;
    }

    public List<GeofenceModel> getGeofences() {
        ArrayList<GeofenceModel> result = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TableGeofence.TABLE_GEOFENCE, null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            result.add(GeofenceModel.fromCursor(res));
            res.moveToNext();
        }
        return result;
    }



}