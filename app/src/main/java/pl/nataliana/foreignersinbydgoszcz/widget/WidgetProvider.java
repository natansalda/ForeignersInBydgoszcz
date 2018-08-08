package pl.nataliana.foreignersinbydgoszcz.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import pl.nataliana.foreignersinbydgoszcz.R;
import pl.nataliana.foreignersinbydgoszcz.activities.TasksActivity;
import pl.nataliana.foreignersinbydgoszcz.model.Task;

public class WidgetProvider extends AppWidgetProvider {

//    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
//        final int N = appWidgetIds.length;
//
//        // Perform this loop procedure for each App Widget that belongs to this provider
//        for (int i=0; i<N; i++) {
//            int appWidgetId = appWidgetIds[i];
//
//            // Create an Intent to launch ExampleActivity
//            Intent intent = new Intent(context, TasksActivity.class);
//            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
//
//            // Get the layout for the App Widget and attach an on-click listener
//            // to the button
//            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
//            views.setOnClickPendingIntent(R.id.widget_list_view, pendingIntent);
//
//            // Tell the AppWidgetManager to perform an update on the current app widget
//            appWidgetManager.updateAppWidget(appWidgetId, views);
//        }
//    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                Task task, int appWidgetId) {

        Intent intent = TasksActivity.myIntent(context, task);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        views.removeAllViews(R.id.ll_recipe_widget_ingredient_list);
        views.setTextViewText(R.id.recipe_widget_title, task.getTaskName());
        views.setOnClickPendingIntent(R.id.recipe_widget_holder, pendingIntent);

        for (Task tasks : task.getIngredients()) {
            RemoteViews rvIngredient = new RemoteViews(context.getPackageName(),
                    R.layout.widget_list_item);
            rvIngredient.setTextViewText(R.id.tv_recipe_widget_ingredient_item,
                    String.valueOf(tasks.getQuantity()) +
                            String.valueOf(tasks.getMeasure()) + " " + ingredient.getIngredient());
            views.addView(R.id.ll_recipe_widget_ingredient_list, rvIngredient);
        }

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void updateRecipeWidgets(Context context, AppWidgetManager appWidgetManager,
                                           Task recipe, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, recipe, appWidgetId);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
    }
}
