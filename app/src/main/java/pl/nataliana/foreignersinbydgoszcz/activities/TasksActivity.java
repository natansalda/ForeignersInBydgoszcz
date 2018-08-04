package pl.nataliana.foreignersinbydgoszcz.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pl.nataliana.foreignersinbydgoszcz.R;
import pl.nataliana.foreignersinbydgoszcz.adapters.TaskAdapter;
import pl.nataliana.foreignersinbydgoszcz.database.TaskDbHelper;
import pl.nataliana.foreignersinbydgoszcz.model.Task;

public class TasksActivity extends AppCompatActivity {

    protected TaskDbHelper db;
    List<Task> list;
    TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        db = new TaskDbHelper(this);
        list = db.getAllTasks();
        adapter = new TaskAdapter(this, R.layout.task_status_view, list);
        ListView listTask = (ListView) findViewById(R.id.listView);
        listTask.setAdapter(adapter);
    }

    public void addTaskNow(View v) {
        EditText taskEditText = (EditText) findViewById(R.id.editText);
        String s = taskEditText.getText().toString();
        if (s.equalsIgnoreCase("")) {
            Toast.makeText(this, "enter the task decription!",
                    Toast.LENGTH_LONG).show();
        } else {
            Task task = new Task(s, 0);
            db.addTask(task);
            Log.d("task", "data added");
            task.setTaskName("");
            adapter.add(task);
            adapter.notifyDataSetChanged();
        }
    }

    private class MyAdapter extends ArrayAdapter<Task> {
        Context context;
        List<Task> taskList = new ArrayList<Task>();
        int layoutResourceId;

        public MyAdapter(Context context, int layoutResourceId,
                         List<Task> objects) {
            super(context, layoutResourceId, objects);
            this.layoutResourceId = layoutResourceId;
            this.taskList = objects;
            this.context = context;
        }

        /**
         * This method will DEFINe what the view inside the list view will
         * finally look like Here we are going to code that the checkbox state
         * is the status of task and check box text is the task name
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            CheckBox chk = null;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.task_status_view,
                        parent, false);
                chk = (CheckBox) convertView.findViewById(R.id.checkBox);
                convertView.setTag(chk);
                chk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckBox cb = (CheckBox) v;
                        Task changeTask = (Task) cb.getTag();
                        changeTask.setStatus(cb.isChecked() == true ? 1 : 0);
                        db.updateTask(changeTask);
                        Toast.makeText(
                                getApplicationContext(),
                                "Clicked on Checkbox: "+cb.getText() + "is "
                        +cb.isChecked(), Toast.LENGTH_LONG)
                                .show();
                    }
                });
            } else {
                chk = (CheckBox) convertView.getTag();
            }
            Task current = taskList.get(position);
            chk.setText(current.getTaskName());
            chk.setChecked(current.getStatus() == 1);
            chk.setTag(current);
            Log.d("listener", String.valueOf(current.getId()));
            return convertView;
        }
    }
}
