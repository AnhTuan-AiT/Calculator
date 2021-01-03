package com.example.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        TextView code = findViewById(R.id.detail_txt_code);
        TextView name = findViewById(R.id.detail_txt_name);
        TextView birthday = findViewById(R.id.detail_txt_birthday);
        TextView email = findViewById(R.id.detail_txt_email);
        TextView address = findViewById(R.id.detail_txt_address);

        code.setText("Code: " + bundle.getInt("code"));
        name.setText("Name: " + bundle.getString("name", ""));
        birthday.setText("Birthday: " + formatter.format(new Date(bundle.getLong("birthday"))));
        email.setText("Email: " + bundle.getString("email"));
        address.setText("Address: " + bundle.getString("address"));
    }
}