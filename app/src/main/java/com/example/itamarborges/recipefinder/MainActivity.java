package com.example.itamarborges.recipefinder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.example.itamarborges.recipefinder.adapter.IngredientsListAdapter;
import com.example.itamarborges.recipefinder.model.IngredientModel;
import com.example.itamarborges.recipefinder.model.RecipeModel;
import com.example.itamarborges.recipefinder.pojo.Ingredient;
import com.example.itamarborges.recipefinder.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_MAIN_ACTIVITY_RV_POSITION = "rvMainActivityPosition";
    private static final String KEY_INPUT_INGREDIENTS = "inputIngredients";
    private static final String KEY_INPUT_SELECTION_START = "inputIngredientsStart";
    private static final String KEY_INPUT_SELECTION_END = "inputIngredientsEnd";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.input_ingredient)
    TextInputEditText textInputIngredient;
    @BindView(R.id.rv_ingredients_list)
    RecyclerView mRecyclerIngredients;

    List<Ingredient> mIngredientsList;
    IngredientsListAdapter mIngredientsListAdapter;
    LinearLayoutManager mLayoutManagerIngredients;

    private Parcelable mLayoutManagerSavedState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mIngredientsList = new ArrayList<>();

        mIngredientsListAdapter = new IngredientsListAdapter(mIngredientsList);

        mLayoutManagerIngredients = new LinearLayoutManager(this);
        mRecyclerIngredients.setAdapter(mIngredientsListAdapter);
        mRecyclerIngredients.setHasFixedSize(false);
        mRecyclerIngredients.setNestedScrollingEnabled(false);
        mRecyclerIngredients.setLayoutManager(mLayoutManagerIngredients);

        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIngredientsList = IngredientModel.getArrayListFromSharedPreferences(getApplicationContext(), IngredientModel.INGREDIENTS_LIST_INDEX);
                if (mIngredientsList.size() < 1) {
                    Snackbar.make(view, R.string.add_some_ingredient, Snackbar.LENGTH_LONG).show();
                } else {
                    if (!NetworkUtils.isNetworkAvailable(getBaseContext())) {
                        Snackbar.make(view, R.string.no_internet_connection_message, Snackbar.LENGTH_LONG).show();
                    } else {
                        Intent intent = new Intent(getApplicationContext(), RecipesListActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(KEY_MAIN_ACTIVITY_RV_POSITION)) {
                mLayoutManagerSavedState = savedInstanceState.getParcelable(KEY_MAIN_ACTIVITY_RV_POSITION);
            }
        }

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        RecipeModel recipeModel = new RecipeModel();
        recipeModel.updateWidget(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(getApplicationContext(), RecipeFavoriteListActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_add_ingredient)
    public void btnAddIngredient() {

        if (textInputIngredient.getText().toString().isEmpty()) {
            Snackbar.make(mRecyclerIngredients, getString(R.string.ingredient_must_be_filled), Snackbar.LENGTH_LONG).show();
            return;
        }

        Ingredient ingredient = new Ingredient(textInputIngredient.getText().toString());

        if (mIngredientsList.contains(ingredient)) {
            Snackbar.make(mRecyclerIngredients, getString(R.string.ingredient_on_the_list), Snackbar.LENGTH_LONG).show();
            return;
        }

        mIngredientsList.add(ingredient);
        mIngredientsListAdapter.setIngredientsList(mIngredientsList);
        textInputIngredient.getText().clear();

        IngredientModel.setArrayListToSharedPreferences(getApplicationContext(), IngredientModel.INGREDIENTS_LIST_INDEX, mIngredientsList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIngredientsList = IngredientModel.getArrayListFromSharedPreferences(getApplicationContext(), IngredientModel.INGREDIENTS_LIST_INDEX);

        if (mIngredientsList != null) {
            mIngredientsListAdapter.setIngredientsList(mIngredientsList);
        }

        if (mLayoutManagerSavedState != null) {
            mLayoutManagerIngredients.onRestoreInstanceState(mLayoutManagerSavedState);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelable(KEY_MAIN_ACTIVITY_RV_POSITION, mLayoutManagerIngredients.onSaveInstanceState());
        outState.putString(KEY_INPUT_INGREDIENTS, textInputIngredient.getText().toString());
        outState.putInt(KEY_INPUT_SELECTION_START, textInputIngredient.getSelectionStart());
        outState.putInt(KEY_INPUT_SELECTION_END, textInputIngredient.getSelectionEnd());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        int selectionStart = 0;
        int selectionEnd = 0;

        if (savedInstanceState.containsKey(KEY_MAIN_ACTIVITY_RV_POSITION)) {
            mLayoutManagerSavedState = savedInstanceState.getParcelable(KEY_MAIN_ACTIVITY_RV_POSITION);
        }

        if (savedInstanceState.containsKey(KEY_INPUT_INGREDIENTS)) {
            textInputIngredient.setText(savedInstanceState.getString(KEY_INPUT_INGREDIENTS));
        }

        if (savedInstanceState.containsKey(KEY_INPUT_SELECTION_START)) {
            selectionStart = savedInstanceState.getInt(KEY_INPUT_SELECTION_START, 0);
        }

        if (savedInstanceState.containsKey(KEY_INPUT_SELECTION_END)) {
            selectionEnd = savedInstanceState.getInt(KEY_INPUT_SELECTION_END, 0);
        }

        textInputIngredient.setSelection(selectionStart, selectionEnd);

        mIngredientsList = IngredientModel.getArrayListFromSharedPreferences(getApplicationContext(), IngredientModel.INGREDIENTS_LIST_INDEX);
        if (mIngredientsList != null) {
            mIngredientsListAdapter.setIngredientsList(mIngredientsList);
        }
    }
}
