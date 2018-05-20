package com.example.itamarborges.recipefinder.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by itamarborges on 21/02/18.
 */

public class RecipeFinderContract {

    public static final String AUTHORITY = "com.example.itamarborges.recipefinder";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final String PATH_FAVORITE = "favorite";

    public static final class FavoriteEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FAVORITE).build();

        public static final String TABLE_NAME = "favorite";

        public static final String COLUMN_URI = "uri";
        public static final String COLUMN_LABEL = "label";
        public static final String COLUMN_URL_IMAGE = "urlImage";
        public static final String COLUMN_SOURCE = "source";
        public static final String COLUMN_URL = "url";
        public static final String COLUMN_CALORIES = "calories";

    }
}
