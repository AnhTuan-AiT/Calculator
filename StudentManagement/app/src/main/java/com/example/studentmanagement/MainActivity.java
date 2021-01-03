package com.example.studentmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.studentmanagement.adapters.StudentAdapter;
import com.example.studentmanagement.model.Student;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.bloco.faker.Faker;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;

    short numStudents;

    List<Student> students;

    SearchView searchView;

    StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up db.
        File storagePath = getApplication().getFilesDir();
        String dbPath = storagePath + "/students";
        db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);

        db.execSQL("create table if not exists student(" +
                "code integer primary key," +
                "name text," +
                "birthday date," +
                "email text," +
                "address text)");

        // Init seed data.
        /*Faker faker = new Faker();
        numStudents = 100;
        List<Student> students = new ArrayList<>();

        for (short i = 0; i < numStudents; i++) {
            Student student = new Student();

            student.setCode(faker.number.between(20170000, 20177000));
            student.setName(faker.name.name().replaceAll("'", " "));
            student.setBirthday(faker.date.birthday(-22, -21));
            student.setEmail(faker.internet.email());
            student.setAddress(faker.address.city());

            students.add(student);
        }

        insert(students);*/

        // Get data from db.
        this.students = getAll();

        // Init list view.
        ListView listStudents = findViewById(R.id.list_students);
        adapter = new StudentAdapter(this, this.students);
        listStudents.setAdapter(adapter);
        registerForContextMenu(listStudents);

        listStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, StudentDetail.class);
                Bundle bundle = new Bundle();
                Student student = students.get(i);

                bundle.putInt("code", student.getCode());
                bundle.putString("name", student.getName());
                bundle.putLong("birthday", student.getBirthday().getTime());
                bundle.putString("email", student.getEmail());
                bundle.putString("address", student.getAddress());

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        db.close();
    }

    private void insert(List<Student> students) {
        db.beginTransaction();

        try {
            for (Student student : students) {
                db.execSQL("insert into student(code, name, birthday, email, address) values(" +
                        student.getCode() + ", '" +
                        student.getName() + "', " +
                        student.getBirthday().getTime() + ", '" +
                        student.getEmail() + "', '" +
                        student.getAddress() + "');");
            }

            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    private List<Student> getAll() {
        List<Student> students = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from student", null);

        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            Student student = new Student();

            student.setCode(cursor.getInt(0));
            student.setName(cursor.getString(1));
            student.setBirthday(new Date(cursor.getLong(2)));
            student.setEmail(cursor.getString(3));
            student.setAddress(cursor.getString(4));

            students.add(student);
        }

        cursor.close();
        return students;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar, menu);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        // Handle search.
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

        if (id == R.id.add) {
            Intent intent = new Intent(MainActivity.this, AddStudent.class);
            startActivityForResult(intent, 123);
        } else if (id == R.id.delete) {
            adapter.delete();
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if ((requestCode == 123) && (resultCode == Activity.RESULT_OK)) {
                Bundle resultBundle = data.getExtras();
                Student student = new Student();

                // Extract info.
                student.setName(resultBundle.getString("name"));
                student.setCode(resultBundle.getInt("code"));
                student.setBirthday(new Date(resultBundle.getLong("birthday")));
                student.setEmail(resultBundle.getString("email"));
                student.setAddress(resultBundle.getString("address"));

                adapter.add(student);
                Toast.makeText(this, "Added", Toast.LENGTH_LONG).show();
            } else if ((requestCode == 234) && (resultCode == Activity.RESULT_OK)) {
                Bundle resultBundle = data.getExtras();
                Student student = new Student();

                // Extract info.
                student.setName(resultBundle.getString("name"));
                student.setCode(resultBundle.getInt("code"));
                student.setBirthday(new Date(resultBundle.getLong("birthday")));
                student.setEmail(resultBundle.getString("email"));
                student.setAddress(resultBundle.getString("address"));

                adapter.update(student);
                Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong when try to add new student!", Toast.LENGTH_LONG).show();
        }
    }

    // Context menu.
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.list_students) {
            menu.add("Cập nhật");
            menu.add("Xoá");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        if (item.getTitle().equals("Xoá")) {
            showConfirmDialog(MainActivity.this, info.position);
        } else if (item.getTitle().equals("Cập nhật")) {
            Intent intent = new Intent(MainActivity.this, AddStudent.class);
            Bundle bundle = new Bundle();
            Student student = adapter.getFiltered().get(info.position);

            bundle.putString("name", student.getName());
            bundle.putInt("code", student.getCode());
            bundle.putLong("birthday", student.getBirthday().getTime());
            bundle.putString("email", student.getEmail());
            bundle.putString("address", student.getAddress());

            intent.putExtras(bundle);
            startActivityForResult(intent, 234);
        } else {
            return false;
        }

        return true;
    }

    // Confirm dialog for deleting student.
    private void showConfirmDialog(MainActivity mainActivity, int position) {
        AlertDialog dialog = new AlertDialog.Builder(mainActivity)
                .setTitle("Xoá sinh viên")
                .setMessage("Cảnh báo: hành động này không thể hoàn tác sau khi thực hiện")
                .setPositiveButton("Xoá",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                adapter.remove(position);
                                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                            }
                        })
                .setNegativeButton("Huỷ",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                            }
                        })
                .create();

        dialog.show();

        // Align cancel button.
        Button cancelBtn = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) cancelBtn.getLayoutParams();

        layoutParams.weight = 1f;
        layoutParams.gravity = Gravity.RIGHT;
        cancelBtn.setLayoutParams(layoutParams);
    }
}