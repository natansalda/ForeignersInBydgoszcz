package pl.nataliana.foreignersinbydgoszcz.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import pl.nataliana.foreignersinbydgoszcz.model.Task;

public class WidgetService extends IntentService {

    public static final String TASK_WIDGET_ACTION_UPDATE = "pl.nataliana.foreignersinbydgoszcz.action.update";
    private static final String BUNDLE_TASK_WIDGET_DATA = "pl.nataliana.foreignersinbydgoszcz.task_widget_data";

    public WidgetService() {
        super("WidgetService");
    }

    public static void startActionUpdateTaskWidgets(Context context, Task task) {
        Intent intent = new Intent(context, WidgetService.class);
        intent.setAction(TASK_WIDGET_ACTION_UPDATE);
        intent.putExtra(BUNDLE_TASK_WIDGET_DATA, (Parcelable) task);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (TASK_WIDGET_ACTION_UPDATE.equals(action) &&
                    intent.getParcelableExtra(BUNDLE_TASK_WIDGET_DATA) != null) {
                handleActionUpdateWidgets((Task)intent.getParcelableExtra(BUNDLE_TASK_WIDGET_DATA));
            }
        }
    }

    private void handleActionUpdateWidgets(Task task) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, WidgetProvider.class));
        WidgetProvider.updateTaskWidgets(this, appWidgetManager, task, appWidgetIds);
    }
}
