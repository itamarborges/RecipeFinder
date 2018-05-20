package com.example.itamarborges.recipefinder;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.itamarborges.recipefinder.adapter.IngredientsSummaryAdapter;
import com.example.itamarborges.recipefinder.adapter.RecipeAdapter;
import com.example.itamarborges.recipefinder.model.RecipeModel;
import com.example.itamarborges.recipefinder.pojo.Ingredient;
import com.example.itamarborges.recipefinder.pojo.Recipe;
import com.example.itamarborges.recipefinder.utils.NetworkUtils;
import com.example.itamarborges.recipefinder.utils.RecipesJsonUtils;
import com.example.itamarborges.recipefinder.utils.Utils;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeFavoriteListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Object>{

    private static final int FAVORITE_RECIPES_LOADER = 0;

    @BindView(R.id.rv_favorite_recipes) RecyclerView mRecyclerRecipes;

    List<Recipe> mRecipes;
    RecipeAdapter mRecipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_favorite_list);
        ButterKnife.bind(this);

        mRecipeAdapter = new RecipeAdapter(mRecipes);

        GridLayoutManager mLayoutManager = new GridLayoutManager(this, Utils.numberOfColumns(getWindowManager()));
        mRecyclerRecipes.setLayoutManager(mLayoutManager);
        mRecyclerRecipes.setAdapter(mRecipeAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getSupportLoaderManager().restartLoader(FAVORITE_RECIPES_LOADER, null, this);
    }

    @NonNull
    @Override
    public Loader<Object> onCreateLoader(int id, @Nullable Bundle args) {
        return new AsyncTaskLoader<Object>(this) {

            List<Recipe> mRecipes = null;

            @Override
            protected void onStartLoading() {
                if (mRecipes != null) {
                    deliverResult(mRecipes);
                } else {
                    forceLoad();
                }
            }

            @Override
            public List<Recipe> loadInBackground() {

                List<Recipe> mRecipes = new ArrayList<>();
                try {
                    RecipeModel mRecipeModel = new RecipeModel();
                    mRecipes = mRecipeModel.listFavoriteRecipes(getContext());

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return mRecipes;
            }

            // deliverResult sends the result of the load, a Cursor, to the registered listener
            public void deliverResult(List<Recipe> data) {
                mRecipes = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Object> loader, Object data) {
        mRecipeAdapter.setRecipes((List<Recipe>)data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Object> loader) {
        loader = null;
    }
}