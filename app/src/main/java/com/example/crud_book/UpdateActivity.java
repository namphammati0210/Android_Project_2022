package com.example.crud_book;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText name_input, destination_input, date_input, description_input;
    RadioGroup isRisk_radioGroup;
    RadioButton riskBtn, notRiskBtn, radioButton;
    Button update_button, delete_button, view_expenses_button;
    String id, name, destination, date, risk, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_input = findViewById(R.id.name_input);
        destination_input = findViewById(R.id.destination_input);
        date_input = findViewById(R.id.date_input);
        description_input = findViewById(R.id.description_input);

        isRisk_radioGroup = findViewById(R.id.isRisk_radioGroup);
        riskBtn = findViewById(R.id.risk_radio);
        notRiskBtn = findViewById(R.id.notRisk_radio);

        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        view_expenses_button = findViewById(R.id.expense_button);

        getAndSetIntentData();

        // Set action bar title after getAndSetIntentData
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(name);

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper dbInstance = new MyDatabaseHelper(UpdateActivity.this);

                // Get value from radio group
                int selectedId = isRisk_radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);

                name = name_input.getText().toString().trim();
                destination = destination_input.getText().toString().trim();
                date = date_input.getText().toString().trim();
                risk = radioButton.getText().toString().trim();
                description = description_input.getText().toString().trim();

                if(name.equals("")){
                    name_input.setError("Please enter trip name");
                    name_input.requestFocus();
                    return;
                }
                if(destination.equals("")){
                    destination_input.setError("Please enter destination");
                    destination_input.requestFocus();
                    return;
                }
                if(date.equals("")){
                    date_input.setError("Please enter date");
                    date_input.requestFocus();
                    return;
                }
                if(description.equals("")){
                    description_input.setError("Please enter description");
                    description_input.requestFocus();
                    return;
                }

                dbInstance.updateData(id, name, destination, date, risk, description);

            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

        view_expenses_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateActivity.this, ExpenseActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });


    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name")) {
            // Get data from intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            destination = getIntent().getStringExtra("destination");
            date = getIntent().getStringExtra("date");
            risk = getIntent().getStringExtra("risk");
            description = getIntent().getStringExtra("description");

            // Set data to input fields
            name_input.setText(name);
            destination_input.setText(destination);
            date_input.setText(date);
            description_input.setText(description);

            if(risk.equals("Yes")) {
                riskBtn.setChecked(true);
            }else {
                notRiskBtn.setChecked(true);
            }


        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Delete this " + name + " ?" );
        builder.setMessage("Are you sure ???");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteData(id);
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create().show();
    }
}