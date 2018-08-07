package pl.nataliana.foreignersinbydgoszcz.database;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import pl.nataliana.foreignersinbydgoszcz.R;

public class TaskCursorAdapter extends CursorAdapter {

    public TaskCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.task_status_view, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {

        // Bind Views
        TextView taskCheckBox = view.findViewById(R.id.checkBox);
        ImageView statusImageView = view.findViewById(R.id.imageView2);

        // Find the proper columns
        int nameColumnIndex = cursor.getColumnIndex(TaskContract.TaskEntry.KEY_TASKNAME);
        int statusColumnIndex = cursor.getColumnIndex(TaskContract.TaskEntry.KEY_STATUS);

        // Read the attributes of the product from the Cursor for the current product.
        int id = cursor.getInt(cursor.getColumnIndex(TaskContract.TaskEntry._ID));
        final String taskName = cursor.getString(nameColumnIndex);
        int taskStatus = cursor.getInt(statusColumnIndex);

        final Uri currentTaskUri = ContentUris.withAppendedId(TaskContract.TaskEntry.CONTENT_URI, id);

        // Update views with attributes for the current product
        taskCheckBox.setText(taskName);
        if (taskStatus == 0) {
            Picasso.get().load(R.drawable.not_done).into(statusImageView);
        } else {
            Picasso.get().load(R.drawable.done).into(statusImageView);
        }
    }
}
