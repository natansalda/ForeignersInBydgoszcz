package pl.nataliana.foreignersinbydgoszcz.widget;

import android.appwidget.AppWidgetProvider;

public class WidgetProvider extends AppWidgetProvider {

//    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
//                                Recipe recipe, int appWidgetId) {
//
//        Intent intent = DetailActivity.myIntent(context, recipe);
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
//
//                PendingIntent.FLAG_UPDATE_CURRENT);
//
//
//
//        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
//
//        views.removeAllViews(R.id.ll_recipe_widget_ingredient_list);
//
//        views.setTextViewText(R.id.recipe_widget_title, recipe.getName());
//
//        views.setOnClickPendingIntent(R.id.recipe_widget_holder, pendingIntent);
//
//
//
//        for (Ingredient ingredient : recipe.getIngredients()) {
//
//            RemoteViews rvIngredient = new RemoteViews(context.getPackageName(),
//
//                    R.layout.widget_list_item);
//
//            rvIngredient.setTextViewText(R.id.tv_recipe_widget_ingredient_item,
//
//                    String.valueOf(ingredient.getQuantity()) +
//
//                            String.valueOf(ingredient.getMeasure()) + " " + ingredient.getIngredient());
//
//            views.addView(R.id.ll_recipe_widget_ingredient_list, rvIngredient);
//
//        }
//
//
//
//        appWidgetManager.updateAppWidget(appWidgetId, views);
//
//    }
//
//
//
//    public static void updateRecipeWidgets(Context context, AppWidgetManager appWidgetManager,
//
//                                           Recipe recipe, int[] appWidgetIds) {
//
//        for (int appWidgetId : appWidgetIds) {
//
//            updateAppWidget(context, appWidgetManager, recipe, appWidgetId);
//
//        }
//
//    }
//
//
//
//    @Override
//
//    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
//
//    }
//
//
//
//    @Override
//
//    public void onDeleted(Context context, int[] appWidgetIds) {
//
//    }
//
//
//
//    @Override
//
//    public void onEnabled(Context context) {
//
//    }
//
//
//
//    @Override
//
//    public void onDisabled(Context context) {
//
//    }

}
