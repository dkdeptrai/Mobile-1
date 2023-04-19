package com.example.todolist;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.LineNumberReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ToDoTask> tasks;
    private Button addButton;
    private TaskAdapter taskAdapter;

    static final int RESULT_CODE_NEW = 1;
    static final int RESULT_CODE_EDIT = 2;

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent intent = result.getData();

                    if (result.getResultCode() == RESULT_CODE_NEW) {
                        String title = intent.getStringExtra("title");
                        String description = intent.getStringExtra("description");
                        String date = intent.getStringExtra("date");
                        ToDoTask task = new ToDoTask(title, description, date);
                        tasks.add(task);
                    }
                    if (result.getResultCode() == RESULT_CODE_EDIT) {
                        int position = getIntent().getIntExtra("position", 0);
                        ToDoTask temp = new ToDoTask(intent.getStringExtra("title"), intent.getStringExtra("description"),intent.getStringExtra("date"));
                        tasks.set(position, temp);

                    }
                    taskAdapter.notifyDataSetChanged();

                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ListView tasksLists = findViewById(R.id.tasks_list);
        tasks = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, R.layout.item_layout, tasks);
        registerForContextMenu(tasksLists);

        tasksLists.setAdapter(taskAdapter);
        tasksLists.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                remove(position);
                return true;
            }
        });
        tasksLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.showContextMenu();
            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        switch (item.getItemId()) {
            case R.id.delete_task:
                remove(position);
                return true;
            case R.id.edit_task:
                ToDoTask task = tasks.get(position);
                Intent intent = new Intent(this, NewTaskActivity.class);
                intent.putExtra("title", task.getTitle());
                intent.putExtra("description", task.getDescription());
                intent.putExtra("date", task.getDate());
                navigateToNew("edit", task);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_task:
                navigateToNew("new");
        }
        return super.onOptionsItemSelected(item);
    }

    private void navigateToNew(String operation) {
        navigateToNew(operation, null);
    }

    private void navigateToNew(String operation, ToDoTask task) {
        Intent intent = new Intent(this, NewTaskActivity.class);
        intent.putExtra("operation", operation);
        if (task != null) {

            intent.putExtra("position", tasks.indexOf(task));

        }
        switch (operation) {
            case "new":
                break;
            case "edit":
                intent.putExtra("title", task.getTitle());
                intent.putExtra("description", task.getDescription());
                intent.putExtra("date", task.getDate());
        }

        launcher.launch(intent);
    }

    private boolean remove(int position) {
        Context context = getApplicationContext();
        Toast.makeText(context, "Task Removed", Toast.LENGTH_LONG).show();
        tasks.remove(position);
        taskAdapter.notifyDataSetChanged();
        return true;
    }
}