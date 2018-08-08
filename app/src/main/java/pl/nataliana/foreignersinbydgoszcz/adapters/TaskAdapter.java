package pl.nataliana.foreignersinbydgoszcz.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import java.util.List;

import pl.nataliana.foreignersinbydgoszcz.R;
import pl.nataliana.foreignersinbydgoszcz.model.Task;

public class TaskAdapter extends ArrayAdapter<Task>
{
    private Context context;
    private List<Task> taskList;
    int layoutResourceId;
    public TaskAdapter(Context context, int layoutResourceId,
                     List<Task> objects) {
        super(context, layoutResourceId, objects);
        this.layoutResourceId = layoutResourceId;
        this.taskList=objects;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.task_status_view, parent, false);
        CheckBox chk = rowView.findViewById(R.id.checkBox);
        Task current = taskList.get(position);
        chk.setText(current.getTaskName());
        chk.setChecked(current.getStatus()==1);

        return rowView;
    }
}
