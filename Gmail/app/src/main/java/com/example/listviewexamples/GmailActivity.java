package com.example.listviewexamples;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.listviewexamples.adapters.GmailAdapter;
import com.example.listviewexamples.models.GmailModel;

import java.util.ArrayList;
import java.util.List;

public class GmailActivity extends AppCompatActivity {

    List<GmailModel> gmails;

    GmailAdapter adapter;

    ListView listView;

    SearchView searchView;

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

        adapter = new GmailAdapter(this, gmails);
        listView = findViewById(R.id.mail_list_view);

        listView.setAdapter(adapter);
        listView.setLongClickable(true);
        registerForContextMenu(listView);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            GmailModel mail = gmails.get(i);

            mail.setReaded(true);
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar, menu);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.filter) {
            List<GmailModel> filtered = new ArrayList<>();

            for (GmailModel gmail : gmails) {
                if (gmail.isStarred())
                    filtered.add(gmail);
            }

            adapter = new GmailAdapter(this, filtered);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.mail_list_view) {
            menu.add("Xoá");
            menu.add("Trả lời");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "Xoá") {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            adapter.remove(info.position);

            Toast.makeText(getApplicationContext(), "Đã xoá",
                    Toast.LENGTH_LONG).show();
        } else if (item.getTitle() == "Trả lời") {
            Toast.makeText(getApplicationContext(), "Đang trả lời",
                    Toast.LENGTH_LONG).show();
        } else {
            return false;
        }

        return true;
    }
}