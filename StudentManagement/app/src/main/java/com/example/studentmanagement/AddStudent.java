package com.example.studentmanagement;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        // Bind views.
        EditText name = findViewById(R.id.add_name);
        EditText code = findViewById(R.id.add_code);
        EditText birthday = findViewById(R.id.add_birthday);
        EditText email = findViewById(R.id.add_email);
        EditText address = findViewById(R.id.add_address);

        Button submitBtn = findViewById(R.id.add_btn);

        // Mode edit
        if (bundle != null) {
            name.setText(bundle.getString("name"));
            code.setText(String.valueOf(bundle.getInt("code")));
            birthday.setText(formatter.format(new Date(bundle.getLong("birthday"))));
            email.setText(bundle.getString("email"));
            address.setText(bundle.getString("address"));

            code.setVisibility(View.INVISIBLE);
            submitBtn.setText("Update");
        }

        // Handle submit.
        submitBtn.setOnClickListener(view -> {
            Bundle resultBundle = new Bundle();

            resultBundle.putString("name", String.valueOf(name.getText()));
            resultBundle.putInt("code", Integer.parseInt(String.valueOf(code.getText())));
            resultBundle.putString("email", String.valueOf(email.getText()));
            resultBundle.putString("address", String.valueOf(address.getText()));

            try {
                resultBundle.putLong("birthday", formatter.parse(String.valueOf(birthday.getText())).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            intent.putExtras(resultBundle);
            setResult(Activity.RESULT_OK, intent);
            finish();
        });

        // Handle date picker.
        final Calendar calendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = (datePicker, i, i1, i2) -> {
            calendar.set(Calendar.YEAR, i);
            calendar.set(Calendar.MONTH, i1);
            calendar.set(Calendar.DAY_OF_MONTH, i2);

            // Update view
            birthday.setText(formatter.format(calendar.getTime()));
        };

        birthday.setOnClickListener(v -> new DatePickerDialog(AddStudent.this, date, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show());
    }
}