package com.example.crud_book;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME="BookLibrary.db";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="trip";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_DESTINATION="destination";
    private static final String COLUMN_DATE="date";
    private static final String COLUMN_RISK="risk";
    private static final String COLUMN_DESCRIPTION="description";

     MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESTINATION + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_RISK + " TEXT, " +
                COLUMN_DESCRIPTION + " INTEGER); ";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void createTrip (String name, String destination, String date, String isRisk, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        Log.d("name", "Value: " + name);
        Log.d("destination", "Value: " + destination);
        Log.d("date", "Value: " + date);
        Log.d("isRisk", "Value: " + isRisk);
        Log.d("description", "Value: " + description);

        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_DESTINATION, destination);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_RISK, isRisk);
        contentValues.put(COLUMN_DESCRIPTION, description);

        long result = db.insert(TABLE_NAME, null, contentValues);
        
        if(result == -1) {
            Toast.makeText(context, "Insert data into database failed !!!", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(context, "Insert data into database successfully", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<Trip> readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        ArrayList<Trip> trips = new ArrayList<>();

        if(db != null) {
            cursor = db.rawQuery(query, null);

            if(cursor.getCount() == 0) {
                Log.d("Data", "No data");
            }

            while(cursor.moveToNext()) {
                String id = cursor.getString(0);
                String name = cursor.getString(1);
                String destination = cursor.getString(2);
                String date = cursor.getString(3);
                String risk = cursor.getString(4);
                String description = cursor.getString(5);

                trips.add(new Trip(id, name, destination, date, risk, description));
            }

//            if (cursor.moveToFirst()) {
//                do {
                    // on below line we are adding the data from cursor to our array list.
//                    courseModalArrayList.add(new CourseModal(cursorCourses.getString(1),
//                            cursorCourses.getString(4),
//                            cursorCourses.getString(2),
//                            cursorCourses.getString(3)));
//                    String id = cursor.getString(0);
//                    String name = cursor.getString(1);
//                    String destination = cursor.getString(2);
//                    String date = cursor.getString(3);
//                    String risk = cursor.getString(4);
//                    String description = cursor.getString(5);
//
//                    trips.add(new Trip(id, name, destination, date, risk, description));
//                } while ( cursor.moveToNext());
                // moving our cursor to next.
//            }
            // at last closing our cursor
            // and returning our array list.
//            cursor.close();
        }

        return trips;
    }

    void updateData(String row_id, String name, String destination, String date, String risk, String description) {
         SQLiteDatabase db = this.getWritableDatabase();
         ContentValues contentValues = new ContentValues();

//        Log.d("row_id", "Value: " + row_id);
//        Log.d("title", "Value: " + title);
//        Log.d("author", "Value: " + author);
//        Log.d("pages", "Value: " + pages);

        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_DESTINATION, destination);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_RISK, risk);
        contentValues.put(COLUMN_DESCRIPTION, description);

        long result =  db.update(TABLE_NAME, contentValues, "_id=?", new String[]{row_id});

        if(result == -1) {
            Toast.makeText(context, "Update data into database failed !!!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Update data into database successfully", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteData(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(TABLE_NAME,"_id=?", new String[]{row_id});

        if(result == -1) {
            Toast.makeText(context, "Delete data into database failed !!!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Delete data into database successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
