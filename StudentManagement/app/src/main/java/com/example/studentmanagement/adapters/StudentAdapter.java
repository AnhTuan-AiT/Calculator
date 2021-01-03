package com.example.studentmanagement.adapters;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.studentmanagement.R;
import com.example.studentmanagement.model.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentAdapter extends BaseAdapter implements Filterable {

    Context context;

    List<Student> students;

    List<Student> filtered;

    Set<Integer> selected;

    public StudentAdapter(Context context, List<Student> students) {
        this.context = context;
        this.students = students;
        this.filtered = students;
        selected = new HashSet<>();
    }

    @Override
    public int getCount() {
        return filtered.size();
    }

    @Override
    public Object getItem(int i) {
        return filtered.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        Student student = filtered.get(i);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.student_list_item, viewGroup, false);
            viewHolder = new ViewHolder();

            viewHolder.name = view.findViewById(R.id.txt_name);
            viewHolder.code = view.findViewById(R.id.txt_code);
            viewHolder.email = view.findViewById(R.id.txt_email);
            viewHolder.selected = view.findViewById(R.id.selected);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.name.setText(student.getName());
        viewHolder.code.setText(String.valueOf(student.getCode()));
        viewHolder.email.setText(student.getEmail());

        CheckBox chk = viewHolder.selected;
        chk.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                selected.add(student.getCode());
            } else {
                selected.remove(student.getCode());
            }
        });

        if (selected.contains(student.getCode())) {
            chk.setChecked(true);
        } else {
            chk.setChecked(false);
        }

        return view;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();

                if (charSequence == null || charSequence.length() == 0) {
                    //no constraint given, just return all the data. (no search)
                    results.count = students.size();
                    results.values = students;
                } else {//do the search
                    List<Student> resultsData = new ArrayList<>();
                    CharSequence txtSearch = charSequence.toString().toUpperCase();

                    for (Student student : students) {
                        if (student.getName().toUpperCase().contains(txtSearch) ||
                                String.valueOf(student.getCode()).contains(txtSearch)) {
                            resultsData.add(student);
                        }
                    }

                    results.count = resultsData.size();
                    results.values = resultsData;
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filtered = (List<Student>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public List<Student> getFiltered() {
        return filtered;
    }

    public void add(Student student) {
        students.add(0, student);
        notifyDataSetChanged();
    }

    public void update(Student updatedStudent) {
        for (Student student : students) {
            if (student.getCode() == updatedStudent.getCode()) {
                student.setName(updatedStudent.getName());
                student.setBirthday(updatedStudent.getBirthday());
                student.setEmail(updatedStudent.getEmail());
                student.setAddress(updatedStudent.getAddress());

                break;
            }
        }

        notifyDataSetChanged();
    }

    public void remove(int position) {
        Student removedItem = filtered.remove(position);

        students.remove(removedItem);
        notifyDataSetChanged();
    }

    public void delete() {
        for (int i = 0; i < students.size(); i++) {
            if (selected.contains(students.get(i).getCode())) {
                students.remove(i);
                i--;
            }
        }

        selected = new HashSet<>();
        notifyDataSetChanged();
    }

    private class ViewHolder {
        TextView name;
        TextView code;
        TextView email;
        CheckBox selected;
    }
}
