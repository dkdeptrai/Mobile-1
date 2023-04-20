package com.example.listvewdemo2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintSet;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    private Context context;
    private int resource;
    private List<Student> students;

    public StudentAdapter(@NonNull Context context, int resource, @NonNull List<Student> objects) {

        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.students = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater viewInflater;
            viewInflater = LayoutInflater.from(this.getContext());
            v = viewInflater.inflate(this.resource, null);
        }
        Student s = getItem(position);
        Log.d("khoadz2", s.getName());
        if (s != null) {
            TextView name = v.findViewById(R.id.name);
            name.setText(s.getName());
            TextView dob = v.findViewById(R.id.dob);
            dob.setText(s.getDob());
        }
        return v;
    }
}
