package com.example.crud_book;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AddActivity extends AppCompatActivity {
    EditText name_input, destination_input, date_input, description_input;
    RadioGroup isRisk_radioGroup;
    RadioButton radioButton;
    Button add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input);
        destination_input = findViewById(R.id.destination_input);
        date_input = findViewById(R.id.date_input);
        description_input = findViewById(R.id.description_input);

        isRisk_radioGroup = findViewById(R.id.isRisk_radioGroup);

        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper dbInstance = new MyDatabaseHelper(AddActivity.this);

                // Get value from radio group
                int selectedId = isRisk_radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedId);

                if(name_input.getText().toString().trim().equals("")){
                    name_input.setError("Please enter trip name");
                    name_input.requestFocus();
                    return;
                }
                if(destination_input.getText().toString().trim().equals("")){
                    destination_input.setError("Please enter destination");
                    destination_input.requestFocus();
                    return;
                }
                if(date_input.getText().toString().trim().equals("")){
                    date_input.setError("Please enter date");
                    date_input.requestFocus();
                    return;
                }
                if(description_input.getText().toString().trim().equals("")){
                    description_input.setError("Please enter description");
                    description_input.requestFocus();
                    return;
                }


                dbInstance.createTrip(
                        name_input.getText().toString().trim(),
                        destination_input.getText().toString().trim(),
                        date_input.getText().toString().trim(),
                        radioButton.getText().toString().trim(),
                        description_input.getText().toString().trim()
                );


            }
        });
    }
}