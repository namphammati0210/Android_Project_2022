package com.example.crud_book;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ExpenseActivity extends AppCompatActivity {
    private String trip_id;
    FloatingActionButton add_button;
    MyDatabaseHelper myDB;
    ArrayList<Expense> expenseArrayList;
    ExpenseAdapter expenseAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        trip_id = getIntent().getStringExtra("id");
        recyclerView = findViewById(R.id.expenseRecyclerView);
        Toast.makeText(this, "Trip ID: " + trip_id, Toast.LENGTH_SHORT).show();

        add_button = findViewById(R.id.add_expense_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExpenseActivity.this, AddExpenseActivity.class);
                intent.putExtra("id", trip_id);
                startActivity(intent);
            }
        });

        // Setup expense data
        myDB = new MyDatabaseHelper(ExpenseActivity.this);
        expenseArrayList = myDB.readAllExpensesByTripId(trip_id);

        expenseAdapter = new ExpenseAdapter(ExpenseActivity.this, this, expenseArrayList);
        recyclerView.setAdapter(expenseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ExpenseActivity.this));
    }
}