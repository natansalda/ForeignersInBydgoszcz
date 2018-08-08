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

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                Task task, int appWidgetId) {

        Intent intent = TasksActivity.myIntent(context, task);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        views.removeAllViews(R.id.ll_recipe_widget_ingredient_list);
        views.setTextViewText(R.id.recipe_widget_title, recipe.getName());
        views.setOnClickPendingIntent(R.id.recipe_widget_holder, pendingIntent);

        for (Ingredient ingredient : recipe.getIngredients()) {
            RemoteViews rvIngredient = new RemoteViews(context.getPackageName(),
                    R.layout.widget_list_item);
            rvIngredient.setTextViewText(R.id.tv_recipe_widget_ingredient_item,
                    String.valueOf(ingredient.getQuantity()) +
                            String.valueOf(ingredient.getMeasure()) + " " + ingredient.getIngredient());
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
