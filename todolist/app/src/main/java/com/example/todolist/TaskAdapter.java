package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class TaskAdapter extends ArrayAdapter<ToDoTask> {
    private final Context context;

    public TaskAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ToDoTask> objects) {
        super(context, resource, objects);
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_layout, parent, false);
        }
        // Get the data item for this position
        ToDoTask task = getItem(position);

        // Populate the data into the template view using the data object
        TextView title = convertView.findViewById(R.id.display_title);
        title.setText(task.getTitle());

        TextView order = convertView.findViewById(R.id.task_order);
        order.setText(Integer.toString(position + 1));

        // Return the completed view to render on screen
        return convertView;
    }
}