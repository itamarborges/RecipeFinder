package com.example.itamarborges.recipefinder.utils;

import android.util.Log;

import com.example.itamarborges.recipefinder.pojo.Recipe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itamarborges on 29/12/17.
 */

public final class RecipesJsonUtils {

//    public static List<MovieCover> getMoviesCoverFromJson(String moviesJsonStr)
//            throws JSONException {
//
//        final String MOVIES_RESULTS = "results";
//
//        final String MOVIE_ID = "id";
//        final String MOVIE_TITLE = "title";
//        final String MOVIES_POSTER_PATH = "poster_path";
//
//        final String urlBaseCover = "http://image.tmdb.org/t/p/w185";
//
//        List<MovieCover> mMoviesCover = new ArrayList<MovieCover>();
//
//        JSONObject moviesJson = new JSONObject(moviesJsonStr);
//
//        JSONArray moviesArray = moviesJson.getJSONArray(MOVIES_RESULTS);
//
//        for (int i = 0; i < moviesArray.length(); i++) {
//            int idMovie;
//            String pathMovieCover;
//            String titleMovie;
//
//            JSONObject movieItem = moviesArray.getJSONObject(i);
//            idMovie = movieItem.getInt(MOVIE_ID);
//            pathMovieCover = urlBaseCover.concat(movieItem.getString(MOVIES_POSTER_PATH));
//            titleMovie = movieItem.getString(MOVIE_TITLE);
//
//            MovieCover movieCover = new MovieCover(idMovie, pathMovieCover, titleMovie);
//
//            mMoviesCover.add(movieCover);
//
//        }
//        return mMoviesCover;
//    }

//    public static MovieDetails getMovieDetails(String movieJsonStr)
//            throws JSONException {
//
//        final String MOVIE_TITLE = "title";
//        final String MOVIE_RELEASE_DATE = "release_date";
//        final String MOVIE_VOTE_AVERAGE = "vote_average";
//        final String MOVIE_PLOT = "overview";
//        final String MOVIES_POSTER_PATH = "poster_path";
//        final String MOVIE_RUNTIME = "runtime";
//        final String MOVIE_TAGLINE = "tagline";
//
//
//        final String urlBaseCover = "http://image.tmdb.org/t/p/w185";
//
//        JSONObject movieJson = new JSONObject(movieJsonStr);
//
//        String movieTitle;
//        String movieReleaseDate;
//        Double movieVoteAverage;
//        String moviePlot;
//        String pathMovieCover;
//        Double movieRuntime;
//        String movieTagline;
//
//        movieTitle = movieJson.getString(MOVIE_TITLE);
//        movieReleaseDate = movieJson.getString(MOVIE_RELEASE_DATE);
//        movieVoteAverage = movieJson.getDouble(MOVIE_VOTE_AVERAGE);
//        moviePlot = movieJson.getString(MOVIE_PLOT);
//        movieRuntime = movieJson.getDouble(MOVIE_RUNTIME);
//        movieTagline = movieJson.getString(MOVIE_TAGLINE);
//
//        pathMovieCover = urlBaseCover.concat(movieJson.getString(MOVIES_POSTER_PATH));
//
//        MovieDetails movieDetails = new MovieDetails(movieTitle, movieReleaseDate, movieVoteAverage, moviePlot, pathMovieCover, movieRuntime, movieTagline);
//
//        return movieDetails;
//    }

//    public static List<MovieVideo> getMovieVideos(String movieJsonStr)
//            throws JSONException {
//
//        List<MovieVideo> movieVideos = new ArrayList<>();
//
//        final String MOVIE_VIDEO_SITE_YOUTUBE = "YouTube";
//
//        final String MOVIE_VIDEO_KEY = "key";
//        final String MOVIE_VIDEO_NAME = "name";
//        final String MOVIE_VIDEO_SITE = "site";
//        final String MOVIE_VIDEO_RESULTS = "results";
//
//        JSONObject movieVideosJson = new JSONObject(movieJsonStr);
//        JSONArray videosArray = movieVideosJson.getJSONArray(MOVIE_VIDEO_RESULTS);
//
//        for (int i = 0; i < videosArray.length(); i++) {
//
//            String key;
//            String name;
//
//            // Get the JSON object representing the one video
//            JSONObject videoJson = videosArray.getJSONObject(i);
//
//            if (videoJson.getString(MOVIE_VIDEO_SITE).equals(MOVIE_VIDEO_SITE_YOUTUBE)) {
//                key = videoJson.getString(MOVIE_VIDEO_KEY);
//                name = videoJson.getString(MOVIE_VIDEO_NAME);
//
//                MovieVideo movieVideo = new MovieVideo(key, name);
//
//                movieVideos.add(movieVideo);
//            }
//        }
//        return movieVideos;
//    }

    public static List<Recipe> getRecipes(String recipesJsonStr)
            throws JSONException {

        final String RECIPES_HITS = "hits";
        final String RECIPES_RECIPE = "recipe";

        final String RECIPE_LABEL = "label";
        final String RECIPE_URL_IMAGE = "image";
        final String RECIPE_SOURCE = "source";
        final String RECIPE_URL = "url";
        final String RECIPE_CALORIES = "calories";

        List<Recipe> recipes = new ArrayList<>();

        JSONObject recipesJson = new JSONObject(recipesJsonStr);
        JSONArray hitsArray = recipesJson.getJSONArray(RECIPES_HITS);

        for (int i = 0; i < hitsArray.length(); i++) {

            JSONObject recipeJson = hitsArray.getJSONObject(i).getJSONObject(RECIPES_RECIPE);

            String label = recipeJson.getString(RECIPE_LABEL);
            String urlImage = recipeJson.getString(RECIPE_URL_IMAGE);
            String source = recipeJson.getString(RECIPE_SOURCE);
            String url = recipeJson.getString(RECIPE_URL);
            Double calories = recipeJson.getDouble(RECIPE_CALORIES);

            Recipe recipe = new Recipe(label, urlImage, source, url, calories);

            recipes.add(recipe);
        }
        return recipes;
    }
}
