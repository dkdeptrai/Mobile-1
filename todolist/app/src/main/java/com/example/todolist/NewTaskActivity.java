package com.example.todolist;

import static com.example.todolist.MainActivity.RESULT_CODE_EDIT;
import static com.example.todolist.MainActivity.RESULT_CODE_NEW;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.ArrayList;

public class NewTaskActivity extends AppCompatActivity {
    private EditText title;
    private EditText description;
    private EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        title = findViewById(R.id.task_title);
        description = findViewById(R.id.task_description);
        date = findViewById(R.id.task_date);
        Button saveButton = findViewById(R.id.save_button);
        Intent client = getIntent();

        Intent intent = new Intent(NewTaskActivity.this, MainActivity.class);
        title.setText(client.getStringExtra("title"));
        description.setText(client.getStringExtra("description"));
        date.setText(client.getStringExtra("date"));
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (client.getStringExtra("operation")) {
                    case "new":
                        intent.putExtra("title", title.getText().toString());
                        intent.putExtra("description", description.getText().toString());
                        intent.putExtra("date", date.getText().toString());
                        setResult(RESULT_CODE_NEW, intent);
                        finish();
                    case "edit":

                        intent.putExtra("position", client.getIntExtra("position", 0));
                        intent.putExtra("title", title.getText().toString());
                        intent.putExtra("description", description.getText().toString());
                        intent.putExtra("date", date.getText().toString());
                        setResult(RESULT_CODE_EDIT, intent);
                        finish();
                }

            }
        });
    }
}