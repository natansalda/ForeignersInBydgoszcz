package pl.nataliana.foreignersinbydgoszcz.widget;

import android.app.IntentService;

//public class WidgetService extends IntentService {
//
//
//
//    public static final String RECIPE_WIDGET_ACTION_UPDATE = "pl.nataliana.bakingapp.action.update";
//
//    private static final String BUNDLE_RECIPE_WIDGET_DATA = "pl.nataliana.bakingapp.recipe_widget_data";
//
//
//
//    public WidgetService() {
//
//        super("WidgetService");
//
//    }
//
//
//
//    public static void startActionUpdateRecipeWidgets(Context context, Recipe recipe) {
//
//        Intent intent = new Intent(context, WidgetService.class);
//
//        intent.setAction(RECIPE_WIDGET_ACTION_UPDATE);
//
//        intent.putExtra(BUNDLE_RECIPE_WIDGET_DATA, recipe);
//
//        context.startService(intent);
//
//    }
//
//
//
//    @Override
//
//    protected void onHandleIntent(Intent intent) {
//
//        if (intent != null) {
//
//            final String action = intent.getAction();
//
//            if (RECIPE_WIDGET_ACTION_UPDATE.equals(action) &&
//
//                    intent.getParcelableExtra(BUNDLE_RECIPE_WIDGET_DATA) != null) {
//
//                handleActionUpdateWidgets((Recipe)intent.getParcelableExtra(BUNDLE_RECIPE_WIDGET_DATA));
//
//            }
//
//        }
//
//    }
//
//
//
//    private void handleActionUpdateWidgets(Recipe recipe) {
//
//        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
//
//        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, WidgetProvider.class));
//
//        WidgetProvider.updateRecipeWidgets(this, appWidgetManager, recipe, appWidgetIds);
//
//    }
//
//}
