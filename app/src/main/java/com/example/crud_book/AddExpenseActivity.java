package com.example.crud_book;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddExpenseActivity extends AppCompatActivity {
    EditText type_input, amount_input, date_input;
    Button add_button;
    String trip_id, expense_type, expense_amount, expense_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        type_input = findViewById(R.id.type_input);
        amount_input = findViewById(R.id.amount_input);
        date_input = findViewById(R.id.date_expense_input);
        add_button = findViewById(R.id.add_expense_button);



        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper dbInstance = new MyDatabaseHelper(AddExpenseActivity.this);
                trip_id = getIntent().getStringExtra("id");
                expense_type = type_input.getText().toString().trim();
                expense_amount = amount_input.getText().toString().trim();
                expense_date = date_input.getText().toString().trim();

                dbInstance.createExpense(expense_type, expense_amount, expense_date, trip_id);
            }
        });

    }

//    void getAndSetIntentData() {
//        if(getIntent().hasExtra("id") && getIntent().hasExtra("name")) {
//            // Get data from intent
//            id = getIntent().getStringExtra("id");
//
//
//            // Set data to input fields
//            name_input.setText(name);
//            destination_input.setText(destination);
//            date_input.setText(date);
//            description_input.setText(description);
//
//            if(risk.equals("Yes")) {
//                riskBtn.setChecked(true);
//            }else {
//                notRiskBtn.setChecked(true);
//            }
//
//
//        }else{
//            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
//        }
//    }
}