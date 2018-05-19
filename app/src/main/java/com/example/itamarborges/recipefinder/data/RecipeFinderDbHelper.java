package com.example.itamarborges.recipefinder.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by itamarborges on 21/02/18.
 */

public class RecipeFinderDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "recipeFinderDb.db";

    private static final int VERSION = 1;

    public RecipeFinderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create tasks table (careful to follow SQL formatting rules)
        final String CREATE_TABLE = "CREATE TABLE "  + RecipeFinderContract.FavoriteEntry.TABLE_NAME + " (" +
                RecipeFinderContract.FavoriteEntry._ID                + " INTEGER PRIMARY KEY, " +
                RecipeFinderContract.FavoriteEntry.COLUMN_LABEL + " TEXT NOT NULL, " +
                RecipeFinderContract.FavoriteEntry.COLUMN_URL_IMAGE + " TEXT NOT NULL, " +
                RecipeFinderContract.FavoriteEntry.COLUMN_SOURCE + " TEXT NOT NULL, " +
                RecipeFinderContract.FavoriteEntry.COLUMN_URL + " TEXT NOT NULL, " +
                RecipeFinderContract.FavoriteEntry.COLUMN_CALORIES    + " REAL NOT NULL);";

        db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RecipeFinderContract.FavoriteEntry.TABLE_NAME);
        onCreate(db);
    }
}
