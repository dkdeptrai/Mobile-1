package com.example.listvewdemo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listview;
    private List<Student> students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = findViewById(R.id.list_view);
        students = new ArrayList<Student>();
        students.add(new Student("khoadz", "2002"));
        students.add(new Student("khoadz1", "2003"));
        students.add(new Student("khoadz2", "2004"));
        Log.d("khoadz", students.toString());
        StudentAdapter adapter = new StudentAdapter(this, R.layout.list_view_item, students);
        listview.setAdapter(adapter);
    }
}

