package com.example.itamarborges.recipefinder;

import android.os.Parcelable;
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
import com.example.itamarborges.recipefinder.model.IngredientModel;
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

public class RecipesListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Object>{

    private static final String KEY_RECIPES_RV_POSITION = "rvRecipesPosition";

    private static final int RECIPES_LOADER = 0;

    @BindView(R.id.rv_choosen_ingredients) RecyclerView mRecyclerIngredients;
    @BindView(R.id.rv_recipes) RecyclerView mRecyclerRecipes;

    List<Ingredient> mIngredientsList;
    List<Recipe> mRecipes;
    IngredientsSummaryAdapter mIngredientsSummaryAdapter;
    RecipeAdapter mRecipeAdapter;

    GridLayoutManager mLayoutManager;
    private Parcelable mLayoutManagerSavedState;

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(KEY_RECIPES_RV_POSITION)) {
            mLayoutManagerSavedState = savedInstanceState.getParcelable(KEY_RECIPES_RV_POSITION);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(KEY_RECIPES_RV_POSITION, mLayoutManager.onSaveInstanceState());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);
        ButterKnife.bind(this);

        mIngredientsSummaryAdapter = new IngredientsSummaryAdapter(mIngredientsList);

        FlexboxLayoutManager mGridLayoutManager = new FlexboxLayoutManager(getBaseContext());
        mGridLayoutManager.setFlexDirection(FlexDirection.ROW);
        mGridLayoutManager.setFlexWrap(FlexWrap.WRAP);
        mGridLayoutManager.setJustifyContent(JustifyContent.FLEX_START);

        mRecyclerIngredients.setAdapter(mIngredientsSummaryAdapter);
        mRecyclerIngredients.setHasFixedSize(true);
        mRecyclerIngredients.setNestedScrollingEnabled(false);
        mRecyclerIngredients.setLayoutManager(mGridLayoutManager);

        mRecipeAdapter = new RecipeAdapter(mRecipes);

        mLayoutManager = new GridLayoutManager(this, Utils.numberOfColumns(getWindowManager()));
        mRecyclerRecipes.setLayoutManager(mLayoutManager);
        mRecyclerRecipes.setAdapter(mRecipeAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIngredientsList = IngredientModel.getArrayListFromSharedPreferences(getApplicationContext(), IngredientModel.INGREDIENTS_LIST_INDEX);
        mIngredientsSummaryAdapter.setIngredientsList(mIngredientsList);
        getSupportLoaderManager().restartLoader(RECIPES_LOADER, null, this);
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

                    URL searchUrl = NetworkUtils.buildUrlSearchEdamam(mIngredientsList);
                    List<Recipe> mRecipes = new ArrayList<>();
                    String queryResult = null;
                    try {
//                        queryResult = NetworkUtils.getResponseFromHttpUrl(searchUrl);
                        queryResult = NetworkUtils.getResponseFromHttpUrl("teste");

                        mRecipes = RecipesJsonUtils.getRecipes(queryResult);

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

        if (mLayoutManagerSavedState != null) {
            mLayoutManager.onRestoreInstanceState(mLayoutManagerSavedState);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Object> loader) {
        loader = null;
    }
}
