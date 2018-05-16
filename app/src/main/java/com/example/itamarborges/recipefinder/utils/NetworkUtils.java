package com.example.itamarborges.recipefinder.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;

import com.example.itamarborges.recipefinder.BuildConfig;
import com.example.itamarborges.recipefinder.pojo.Ingredient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * Created by itamarborges on 29/12/17.
 */

public class NetworkUtils {

    final static String EDAMAM_API_URL =
            "https://api.edamam.com/";

    public final static String EDAMAM_API_SEARCH = "search";


    public final static String EDAMAM_API_QUERY = "q";
    public final static String THE_MOVIE_DB_REVIEWS = "reviews";

    final static String EDAMAM_API_KEY = "app_key";
    final static String EDAMAM_API_ID = "app_id";

    final static String API_KEY = BuildConfig.API_KEY;
    final static String API_ID = BuildConfig.API_ID;


    public static URL buildUrlSearchEdamam(List<Ingredient> ingredients) {

        StringBuffer sbIngredients = new StringBuffer();
        for (Ingredient ing : ingredients){
            sbIngredients.append(ing.getName()).append(",");
        }
        sbIngredients.deleteCharAt(sbIngredients.lastIndexOf(","));

        String ingredientsComma = TextUtils.join(",", ingredients);

         Uri builtUri = Uri.parse(EDAMAM_API_URL).buildUpon()
                .appendEncodedPath(EDAMAM_API_SEARCH)
                .appendQueryParameter(EDAMAM_API_QUERY, sbIngredients.toString())
                .appendQueryParameter(EDAMAM_API_ID, API_ID)
                .appendQueryParameter(EDAMAM_API_KEY, API_KEY)
                 .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static URL buildUrlAditionalPath(String path, String aditionalPath) {
////        Uri builtUri = Uri.parse(THE_MOVIE_DB_BASE_URL).buildUpon()
//                .appendPath(path)
//                .appendPath(aditionalPath)
//                .appendQueryParameter(THE_MOVIE_DB_API_KEY, API_KEY)
//                .build();

        URL url = null;
//        try {
//            url = new URL(builtUri.toString());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    static public boolean isNetworkAvailable(Context c) {
        ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
