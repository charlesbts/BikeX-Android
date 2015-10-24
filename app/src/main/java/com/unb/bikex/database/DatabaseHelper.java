package com.unb.bikex.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    private static final String TABLE_STATISTICS = "statistics";

    /* Track table columns*/
    private static final String TRACK_COLUMN_COD = "cod";
    private static final String TRACK_COLUMN_NAME = "name";

    /* Location table columns*/
    private static final String LOCATION_COLUMN_COD = "cod";
    private static final String LOCATION_COLUMN_COD_TRACK_FK = "cod_track";
    private static final String LOCATION_COLUMN_LATITUDE = "latitude";
    private static final String LOCATION_COLUMN_LONGITUDE = "longitude";

    /* Statistics table columns*/
    private static final String STATISTICS_COLUMN_COD = "cod";
    private static final String STATISTICS_COLUMN_DATE = "date";
    private static final String STATISTICS_COLUMN_AVERAGE_SPEED = "average_speed";
    private static final String STATISTICS_COLUMN_AVERAGE_CADENCE = "average_cadence";
    private static final String STATISTICS_COLUMN_DISTANCE = "distance";
    private static final String STATISTICS_COLUMN_TIME = "time";
    private static final String STATISTICS_COLUMN_COD_TRACK_FK = "cod_track";

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

        db.execSQL(CREATE_TRACK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRACK);
            onCreate(db);
        }
    }

    public void insertTrack(String name){
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        ContentValues values = new ContentValues();
        values.put(TRACK_COLUMN_NAME, name);
        db.insert(TABLE_TRACK, null, values);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public List<String> selectAllTracks(){
        List<String> track = new ArrayList<>();
        String TRACK_SELECT_QUERY =
                String.format("SELECT name FROM %s",
                        TABLE_TRACK);

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(TRACK_SELECT_QUERY, null);
        cursor.moveToFirst();
        do {
            String trackName = cursor.getString(cursor.getColumnIndex(TRACK_COLUMN_NAME));
            track.add(trackName);
        }while(cursor.moveToNext());

        return track;
    }

}
