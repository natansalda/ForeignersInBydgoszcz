package pl.nataliana.foreignersinbydgoszcz.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;

import pl.nataliana.foreignersinbydgoszcz.R;
import pl.nataliana.foreignersinbydgoszcz.database.TaskContract;

public class AddTaskActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int EXISTING_TASK_LOADER = 0;
    private Uri currentTaskUri;
    private EditText taskEditText;
    private ImageView taskStatusImageView;
    private int rowsDeleted = 0;
    private boolean taskChange = false;
    private boolean isDone = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // Binding the views.
        taskEditText = findViewById(R.id.editText);
        taskStatusImageView = findViewById(R.id.imageView_status);

        Intent intent = getIntent();
        currentTaskUri = intent.getData();
        if (currentTaskUri == null) {
            setTitle(getString(R.string.title_activity_new_task));
            invalidateOptionsMenu();
        } else {
            setTitle(getString(R.string.title_activity_modify_task));
            taskEditText.setHintTextColor(getResources().getColor(R.color.colorPrimaryDark));
            getLoaderManager().initLoader(EXISTING_TASK_LOADER, null, this);
        }

        taskStatusImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (taskStatusImageView.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.done).getConstantState()) {
                    taskStatusImageView.setImageResource(R.drawable.not_done);
                    isDone = false;
                } else {
                    taskStatusImageView.setImageResource(R.drawable.done);
                    isDone = true;
                }
            }
        });
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_single_task);
        builder.setPositiveButton(R.string.action_delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteTask();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //Perform the removal of the task in the database.
    private void deleteTask() {
        // Only perform the delete if the product exists.
        if (currentTaskUri != null) {
            int rowsDeleted = getContentResolver().delete(currentTaskUri, null, null);
            if (rowsDeleted == 0) {
                Toast.makeText(this, R.string.task_not_deleted, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddTaskActivity.this, TasksActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.task_deleted, Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }

    private void cancelChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.discard_changes_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.continue_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        if (!taskChange) {
            super.onBackPressed();
            return;
        }

        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                };
        cancelChangesDialog(discardButtonClickListener);
    }

    private void AddNewProduct() {
        String name = taskEditText.getText().toString();

        if (name.isEmpty()) {
            Toast.makeText(this, R.string.enter_task, Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskEntry.KEY_TASKNAME, name);
        if (!isDone) {
            values.put(TaskContract.TaskEntry.KEY_STATUS, 0);
        } else {
            values.put(TaskContract.TaskEntry.KEY_STATUS, 1);
        }

        if (currentTaskUri == null) {
            Uri insertedRow = getContentResolver().insert(TaskContract.TaskEntry.CONTENT_URI, values);
            if (insertedRow == null) {
                Toast.makeText(this, R.string.save_failed, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, R.string.task_added, Toast.LENGTH_LONG).show();
            }
        } else {
            int rowUpdated = getContentResolver().update(currentTaskUri, values, null, null);
            if (rowUpdated == 0) {
                Toast.makeText(this, R.string.save_failed, Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, R.string.task_modified, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, TasksActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_task, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (currentTaskUri == null) {
            MenuItem menuItem = menu.findItem(R.id.delete_product);
            menuItem.setVisible(false);
        }

        if (currentTaskUri != null) {
            MenuItem menuItem = menu.findItem(R.id.saved_product);
            menuItem.setIcon(R.drawable.ic_save_black_24dp);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saved_product:
                AddNewProduct();
                finish();
                return true;
            case R.id.delete_product:
                showDeleteConfirmationDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                TaskContract.TaskEntry._ID,
                TaskContract.TaskEntry.KEY_TASKNAME,
                TaskContract.TaskEntry.KEY_STATUS};
        return new CursorLoader(this,
                currentTaskUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {
            int nameColumnIndex = cursor.getColumnIndex(TaskContract.TaskEntry.KEY_TASKNAME);
            int statusColumnIndex = cursor.getColumnIndex(TaskContract.TaskEntry.KEY_STATUS);

            String name = cursor.getString(nameColumnIndex);
            int status = cursor.getInt(statusColumnIndex);
            taskEditText.setText(name);

            if (status == 1) {
                Picasso.get().load(currentTaskUri)
                        .placeholder(R.drawable.done)
                        .fit()
                        .into(taskStatusImageView);
            } else {
                Picasso.get().load(currentTaskUri)
                        .placeholder(R.drawable.not_done)
                        .fit()
                        .into(taskStatusImageView);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        taskEditText.setText("");
    }
}