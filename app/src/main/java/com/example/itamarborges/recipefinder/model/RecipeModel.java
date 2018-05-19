package com.example.itamarborges.recipefinder.model;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.example.itamarborges.recipefinder.data.RecipeFinderContract;
import com.example.itamarborges.recipefinder.pojo.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itamarborges on 21/02/18.
 */

public class RecipeModel {

    public Uri insert(Context context, String label, String urlImage, String source, String url, Double calories) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(RecipeFinderContract.FavoriteEntry.COLUMN_LABEL, label);
        contentValues.put(RecipeFinderContract.FavoriteEntry.COLUMN_URL_IMAGE, urlImage);
        contentValues.put(RecipeFinderContract.FavoriteEntry.COLUMN_SOURCE, source);
        contentValues.put(RecipeFinderContract.FavoriteEntry.COLUMN_URL, url);
        contentValues.put(RecipeFinderContract.FavoriteEntry.COLUMN_CALORIES, calories);

        Uri uri = context.getContentResolver().insert(RecipeFinderContract.FavoriteEntry.CONTENT_URI, contentValues);

        return uri;
    }

    public int delete(Context context, int id) {

        Uri uriToDelete = ContentUris.withAppendedId(RecipeFinderContract.FavoriteEntry.CONTENT_URI, id);

        int deletedRows = context.getContentResolver().delete(uriToDelete, null, null);

        return deletedRows;
    }

    public List<Recipe> listRecipesFromCursor(Cursor cursor) {

        int idCol = cursor.getColumnIndex(RecipeFinderContract.FavoriteEntry._ID);
        int labelCol = cursor.getColumnIndex(RecipeFinderContract.FavoriteEntry.COLUMN_LABEL);
        int urlImageCol = cursor.getColumnIndex(RecipeFinderContract.FavoriteEntry.COLUMN_URL_IMAGE);
        int sourceCol = cursor.getColumnIndex(RecipeFinderContract.FavoriteEntry.COLUMN_SOURCE);
        int urlCol = cursor.getColumnIndex(RecipeFinderContract.FavoriteEntry.COLUMN_URL);
        int caloriesCol = cursor.getColumnIndex(RecipeFinderContract.FavoriteEntry.COLUMN_CALORIES);

        List<Recipe> mRecipes = new ArrayList<>();

        while (cursor.moveToNext()) {
            Recipe recipe = new Recipe(
                    cursor.getInt(idCol),
                    cursor.getString(labelCol),
                    cursor.getString(urlImageCol),
                    cursor.getString(sourceCol),
                    cursor.getString(urlCol),
                    cursor.getDouble(caloriesCol));
            mRecipes.add(recipe);
        }
        return mRecipes;
    }

    public boolean isFavorite(Context context, int id) {

        Uri uriToSelect = ContentUris.withAppendedId(RecipeFinderContract.FavoriteEntry.CONTENT_URI, id);

        Cursor c = context.getContentResolver().query(RecipeFinderContract.FavoriteEntry.CONTENT_URI,
                null,
                null,
                null,
                null);

        return c.moveToNext();
    }
}
