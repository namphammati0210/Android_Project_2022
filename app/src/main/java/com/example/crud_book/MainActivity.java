package com.example.crud_book;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    MyDatabaseHelper myDB;
//    ArrayList<String> trip_id, trip_name, trip_destination, trip_date, trip_risk, trip_description;
    ArrayList<Trip> tripArrayList;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find recycleView and floatingBtn by Id
        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);

        // Listen click event on Add button
        add_button.setOnClickListener(new View.OnClickListener() { // Click add button will lead to Add activity
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);

                startActivity(intent);
            }
        });

        // Setup trip data
        myDB = new MyDatabaseHelper(MainActivity.this);
//        trip_id = new ArrayList<>();
//        trip_name = new ArrayList<>();
//        trip_destination = new ArrayList<>();
//        trip_date = new ArrayList<>();
//        trip_risk = new ArrayList<>();
//        trip_description = new ArrayList<>();

        // Render data to recycleView with CustomAdapter
//        storeDataInArrays();
        tripArrayList = myDB.readAllData();

        customAdapter = new CustomAdapter(MainActivity.this, this, tripArrayList);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

//    @Override
//    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        MenuItem item = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) item.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                customAdapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 1){
//            recreate();
//        }
//    }

//    void storeDataInArrays() {
//        Cursor cursor = myDB.readAllData();
//
//        if(cursor.getCount() == 0) {
//            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        while(cursor.moveToNext()) {
//            trip_id.add(cursor.getString(0));
//            trip_name.add(cursor.getString(1));
//            trip_destination.add(cursor.getString(2));
//            trip_date.add(cursor.getString(3));
//            trip_risk.add(cursor.getString(4));
//            trip_description.add(cursor.getString(5));
//        }
//
//
//    }
}