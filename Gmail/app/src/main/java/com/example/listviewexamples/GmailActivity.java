package com.example.listviewexamples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.listviewexamples.adapters.GmailAdapter;
import com.example.listviewexamples.models.GmailModel;

import java.util.ArrayList;
import java.util.List;

public class GmailActivity extends AppCompatActivity {

    List<GmailModel> gmails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmail);

        gmails = new ArrayList<>();

        for (int i = 1; i < 21; i++) {
            gmails.add(new GmailModel(
                    false,
                    "Sender" + i,
                    "[GitHub] A third-party OAuth application has been added to your account",
                    "Hey Receiver " + i + "! A third-party OAuth application " +
                            "(freeCodeCamp.org) with user:email scope was recently authorized to access your account.",
                    "Nov " + i,
                    false));
        }

        GmailAdapter adapter = new GmailAdapter(this, gmails);
        ListView listView = findViewById(R.id.mail_list_view);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GmailModel mail = gmails.get(i);

                mail.setReaded(true);
                adapter.notifyDataSetChanged();
            }
        });
    }
}