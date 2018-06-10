package com.example.itamarborges.recipefinder;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.example.itamarborges.recipefinder.pojo.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeFavoriteWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, List<Recipe> recipes,
                                int appWidgetId) {

        RemoteViews views;

        // Construct the RemoteViews object
        if (recipes == null || recipes.size() < 1) {
            views = new RemoteViews(context.getPackageName(), R.layout.recipe_favorite_widget);
        } else {
            views = new RemoteViews(context.getPackageName(), R.layout.recipe_favorite_widget_with_list);

            Intent intent = new Intent(context, RecipeFavoriteWidgetService.class);

            ArrayList<String> favoriteRecipes = new ArrayList<>();

            for (Recipe r : recipes) {
                String label = r.getLabel();
                favoriteRecipes.add(label);
            }

            intent.putStringArrayListExtra(RecipeFavoriteWidgetService.FAVORITES_RECIPE_INGREDIENTS, favoriteRecipes);

            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            intent.setData(Uri.fromParts("content", String.valueOf(appWidgetId), favoriteRecipes.toString()));

            views.setRemoteAdapter(R.id.favorite_recipes_list, intent);

            Intent homeIntent = new Intent(context, RecipeFavoriteListActivity.class);
            homeIntent.setData(Uri.parse(homeIntent.toUri(Intent.URI_INTENT_SCHEME)));
            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent homePendingIntent = PendingIntent.getActivity(context, 0, homeIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.widget_layout, homePendingIntent);
            views.setPendingIntentTemplate(R.id.favorite_recipes_list, homePendingIntent);
        }
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    public static void updateFromActivity(Context mContext, AppWidgetManager appWidgetManager, int[] appWidgetIds, List<Recipe> recipes) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(mContext, appWidgetManager, recipes, appWidgetId);
        }

    }
}

