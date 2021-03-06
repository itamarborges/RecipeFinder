package com.example.itamarborges.recipefinder.model;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.itamarborges.recipefinder.RecipeFavoriteWidget;
import com.example.itamarborges.recipefinder.data.RecipeFinderContract;
import com.example.itamarborges.recipefinder.pojo.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itamarborges on 21/02/18.
 */

public class RecipeModel {

    public Uri insert(Context context, String uri, String label, String urlImage, String source, String url, Double calories) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(RecipeFinderContract.FavoriteEntry.COLUMN_URI, uri);
        contentValues.put(RecipeFinderContract.FavoriteEntry.COLUMN_LABEL, label);
        contentValues.put(RecipeFinderContract.FavoriteEntry.COLUMN_URL_IMAGE, urlImage);
        contentValues.put(RecipeFinderContract.FavoriteEntry.COLUMN_SOURCE, source);
        contentValues.put(RecipeFinderContract.FavoriteEntry.COLUMN_URL, url);
        contentValues.put(RecipeFinderContract.FavoriteEntry.COLUMN_CALORIES, calories);

        return context.getContentResolver().insert(RecipeFinderContract.FavoriteEntry.CONTENT_URI, contentValues);
    }

    public int delete(Context context, String uri) {

        return context.getContentResolver().delete(RecipeFinderContract.FavoriteEntry.CONTENT_URI.buildUpon().appendPath(uri).build(), null, null);
    }

    public List<Recipe> listFavoriteRecipes(Context context) {

        List<Recipe> mRecipes = null;

        try {

            Cursor cursor = context.getContentResolver().query(RecipeFinderContract.FavoriteEntry.CONTENT_URI,
                    null,
                    null,
                    null,
                    null);

            int idCol = cursor.getColumnIndex(RecipeFinderContract.FavoriteEntry._ID);
            int uriCol = cursor.getColumnIndex(RecipeFinderContract.FavoriteEntry.COLUMN_URI);
            int labelCol = cursor.getColumnIndex(RecipeFinderContract.FavoriteEntry.COLUMN_LABEL);
            int urlImageCol = cursor.getColumnIndex(RecipeFinderContract.FavoriteEntry.COLUMN_URL_IMAGE);
            int sourceCol = cursor.getColumnIndex(RecipeFinderContract.FavoriteEntry.COLUMN_SOURCE);
            int urlCol = cursor.getColumnIndex(RecipeFinderContract.FavoriteEntry.COLUMN_URL);
            int caloriesCol = cursor.getColumnIndex(RecipeFinderContract.FavoriteEntry.COLUMN_CALORIES);

            mRecipes = new ArrayList<>();

            while (cursor.moveToNext()) {
                Recipe recipe = new Recipe(
                        cursor.getInt(idCol),
                        cursor.getString(uriCol),
                        cursor.getString(labelCol),
                        cursor.getString(urlImageCol),
                        cursor.getString(sourceCol),
                        cursor.getString(urlCol),
                        cursor.getDouble(caloriesCol));
                mRecipes.add(recipe);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mRecipes;
    }

    public boolean isFavorite(Context context, String uri) {

        Cursor c = context.getContentResolver().query(RecipeFinderContract.FavoriteEntry.CONTENT_URI.buildUpon().appendPath(uri).build(),
                null,
                null,
                null,
                null);

        return c.moveToNext();
    }

    public void updateWidget(Context context) {

        List<Recipe> mRecipes = listFavoriteRecipes(context);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, RecipeFavoriteWidget.class));
        RecipeFavoriteWidget.updateFromActivity(context, appWidgetManager, appWidgetIds, mRecipes);


    }
}
