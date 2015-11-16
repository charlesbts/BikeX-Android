package com.unb.bikex.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.unb.bikex.entity.Location;
import com.unb.bikex.entity.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charles on 10/23/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    /* Database infos */
    private static final String DATABASE_NAME = "bikedb";
    private static final int DATABASE_VERSION = 1;

    /* Table names */
    private static final String TABLE_TRACK = "track";
    private static final String TABLE_LOCATION = "location";
    private static final String TABLE_STATISTIC = "statistics";

    /* Track table columns*/
    private static final String TRACK_COLUMN_COD = "cod";
    private static final String TRACK_COLUMN_NAME = "name";

    /* Location table columns*/
    private static final String LOCATION_COLUMN_COD = "cod";
    private static final String LOCATION_COLUMN_COD_TRACK_FK = "cod_track";
    private static final String LOCATION_COLUMN_SEQUENCE = "sequence";
    private static final String LOCATION_COLUMN_LATITUDE = "latitude";
    private static final String LOCATION_COLUMN_LONGITUDE = "longitude";

    /* Statistics table columns*/
    private static final String STATISTIC_COLUMN_COD = "cod";
    private static final String STATISTIC_COLUMN_TIME_STAMP = "time_stamp";
    private static final String STATISTIC_COLUMN_AVERAGE_SPEED = "average_speed";
    private static final String STATISTIC_COLUMN_AVERAGE_CADENCE = "average_cadence";
    private static final String STATISTIC_COLUMN_DISTANCE = "distance";
    private static final String STATISTIC_COLUMN_ELAPSED_TIME = "elapsed_time";
    private static final String STATISTIC_COLUMN_COD_TRACK_FK = "cod_track";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TRACK_TABLE = "CREATE TABLE " + TABLE_TRACK +
                "(" +
                    TRACK_COLUMN_COD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TRACK_COLUMN_NAME + " TEXT" +
                ")";
        String CREATE_LOCATION_TABLE = "CREATE TABLE " + TABLE_LOCATION +
                "(" +
                    LOCATION_COLUMN_COD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    LOCATION_COLUMN_COD_TRACK_FK + " INTEGER REFERENCES " + TABLE_TRACK + " ON DELETE CASCADE, " +
                    LOCATION_COLUMN_SEQUENCE + " INTEGER, " +
                    LOCATION_COLUMN_LATITUDE + " DOUBLE, " +
                    LOCATION_COLUMN_LONGITUDE + " DOUBLE" +
                ")";

        String CREATE_STATISTIC_TABLE = "CREATE TABLE " + TABLE_STATISTIC +
                "(" +
                    STATISTIC_COLUMN_COD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    STATISTIC_COLUMN_COD_TRACK_FK + " INTEGER REFERENCES " + TABLE_TRACK + " ON DELETE CASCADE, " +
                    STATISTIC_COLUMN_TIME_STAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    STATISTIC_COLUMN_AVERAGE_CADENCE + " FLOAT, " +
                    STATISTIC_COLUMN_AVERAGE_SPEED + " FLOAT, " +
                    STATISTIC_COLUMN_ELAPSED_TIME + " INTEGER, " +
                    STATISTIC_COLUMN_DISTANCE + " FLOAT" +
                ")";

        db.execSQL(CREATE_TRACK_TABLE);
        db.execSQL(CREATE_LOCATION_TABLE);
        db.execSQL(CREATE_STATISTIC_TABLE);
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        if(!db.isReadOnly()){
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRACK);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATISTIC);
            onCreate(db);
        }
    }

    public long insertTrack(Track track){
        long codTrack;
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(TRACK_COLUMN_NAME, track.getName());
        codTrack = db.insert(TABLE_TRACK, null, values);
        db.setTransactionSuccessful();
        db.endTransaction();

        return codTrack;
    }

    public boolean trackNameExists(String trackName){
        String TRACK_SELECT_QUERY =
                String.format("SELECT %s FROM %s WHERE %s = ?",
                        TRACK_COLUMN_COD, TABLE_TRACK, TRACK_COLUMN_NAME);

        SQLiteDatabase db = getReadableDatabase();
        SQLiteStatement statement = db.compileStatement(TRACK_SELECT_QUERY);
        statement.bindString(1, trackName);
        try {
            statement.simpleQueryForLong();
            return true;
        }
        catch (SQLiteDoneException trackNameNotExists){
            return false;
        }
    }

    public void insertLocation(long trackCod, int sequence, double latitude, double longitude){
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(LOCATION_COLUMN_COD_TRACK_FK, trackCod);
        values.put(LOCATION_COLUMN_SEQUENCE, sequence);
        values.put(LOCATION_COLUMN_LATITUDE, latitude);
        values.put(LOCATION_COLUMN_LONGITUDE, longitude);
        db.insert(TABLE_LOCATION, null, values);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public int deleteTrack(long cod){
        int numRowsDeleted;
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        numRowsDeleted = db.delete(TABLE_TRACK, TRACK_COLUMN_COD + " =?", new String[] {Long.toString(cod)});
        db.setTransactionSuccessful();
        db.endTransaction();
        return numRowsDeleted;
    }

    public List<Track> selectAllTracks(){
        List<Track> trackList = new ArrayList<>();
        String TRACK_SELECT_QUERY =
                String.format("SELECT cod, name FROM %s",
                        TABLE_TRACK);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(TRACK_SELECT_QUERY, null);
        if(cursor.moveToFirst()) {
            do {
                long trackCod = cursor.getLong(cursor.getColumnIndex(TRACK_COLUMN_COD));
                String trackName = cursor.getString(cursor.getColumnIndex(TRACK_COLUMN_NAME));
                trackList.add(new Track(trackCod, trackName));
            }while(cursor.moveToNext());
        }

        cursor.close();
        return trackList;
    }

    public List<Location> selectAllDataLocationsFromTrack(long trackCod){
        List<Location> locationList = new ArrayList<>();
        String DATA_LOCATION_SELECT_QUERY =
                String.format("SELECT latitude, longitude FROM %s WHERE %s = %s ORDER BY sequence",
                        TABLE_LOCATION, LOCATION_COLUMN_COD_TRACK_FK, Long.toString(trackCod));

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(DATA_LOCATION_SELECT_QUERY, null);
        if(cursor.moveToFirst()){
            do{
                double latitude = cursor.getDouble(cursor.getColumnIndex(LOCATION_COLUMN_LATITUDE));
                double longitude = cursor.getDouble(cursor.getColumnIndex(LOCATION_COLUMN_LONGITUDE));
                locationList.add(new Location(latitude, longitude));
            }while(cursor.moveToNext());
        }

        cursor.close();
        return locationList;
    }

}
