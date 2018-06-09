package com.example.itamarborges.recipefinder;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.itamarborges.recipefinder.data.RecipeFinderContract;
import com.example.itamarborges.recipefinder.model.RecipeModel;
import com.example.itamarborges.recipefinder.pojo.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Object>{

    private static final String TAG = RecipeDetailActivity.class.getSimpleName();

    public static final String RECIPE_INDEX = "recipeIndex";

    private static final int RECIPE_FAVORITE_LOADER_ID = 0;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.web_view_recipe) WebView webView;

    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        if (intent != null) {
            recipe = (Recipe) intent.getSerializableExtra(RECIPE_INDEX);
            if (savedInstanceState != null) {
                webView.restoreState(savedInstanceState);
            } else {
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(recipe.getUrl());
            }


            setTitle(recipe.getLabel());
        }

        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecipeModel recipeModel = new RecipeModel();
                fab.setActivated(!fab.isActivated());

                if (fab.isActivated()) {
                    Uri uri = recipeModel.insert(getApplicationContext(), recipe.getUri(), recipe.getLabel(), recipe.getUrlImage(), recipe.getSource(), recipe.getUrl(), recipe.getCalories());
                } else {
                    recipeModel.delete(getApplicationContext(), recipe.getUri());
                }
                recipeModel.updateWidget(getApplicationContext());
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportLoaderManager().initLoader(RECIPE_FAVORITE_LOADER_ID, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportLoaderManager().restartLoader(RECIPE_FAVORITE_LOADER_ID, null, this);
    }

    @NonNull
    @Override
    public Loader<Object> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader<Object>(this) {

            Cursor mRecipeData = null;

            @Override
            protected void onStartLoading() {
                if (mRecipeData != null) {
                    deliverResult(mRecipeData);
                } else {
                    forceLoad();
                }
            }

            @Override
            public Cursor loadInBackground() {
                Uri uriToSelect = RecipeFinderContract.FavoriteEntry.CONTENT_URI.buildUpon().appendPath(recipe.getUri()).build();

                try {
                    return getContentResolver().query(uriToSelect,
                            null,
                            null,
                            null,
                            null);

                } catch (Exception e) {
                    Log.e(TAG, "Failed to asynchronously load data.");
                    e.printStackTrace();
                    return null;
                }
            }

            // deliverResult sends the result of the load, a Cursor, to the registered listener
            public void deliverResult(Cursor data) {
                mRecipeData = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Object> loader, Object data) {
        boolean isFavorite = ((Cursor) data).moveToFirst();
        fab.setActivated(isFavorite);
        fab.setContentDescription(isFavorite ? getString(R.string.add_this_recipe_to_my_favorite_recipes) : getString(R.string.delete_from_favorite_recipes));

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Object> loader) {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        webView.saveState(outState);
    }
}
