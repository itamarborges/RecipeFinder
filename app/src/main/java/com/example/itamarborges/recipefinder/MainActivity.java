package com.example.itamarborges.recipefinder;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.itamarborges.recipefinder.pojo.Ingredient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String INGREDIENTS_LIST_INDEX = "ingredientsListIndex";

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.input_ingredient)TextInputEditText textInputIngredient;
    @BindView(R.id.rv_ingredients_list) RecyclerView mRecyclerIngredients;

    List<Ingredient> mIngredientsList;
    IngredientsListAdapter mIngredientsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mIngredientsList = new ArrayList<>();

        if (savedInstanceState != null) {
            mIngredientsList = (ArrayList) savedInstanceState.getParcelableArrayList(INGREDIENTS_LIST_INDEX);
        }

        mIngredientsListAdapter = new IngredientsListAdapter(mIngredientsList);

        LinearLayoutManager layoutManagerIngredients = new LinearLayoutManager(this);
        mRecyclerIngredients.setAdapter(mIngredientsListAdapter);
        mRecyclerIngredients.setHasFixedSize(false);
        mRecyclerIngredients.setNestedScrollingEnabled(false);
        mRecyclerIngredients.setLayoutManager(layoutManagerIngredients);

        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_add_ingredient)
    public void btnAddIngredient() {

        if (textInputIngredient.getText().toString().isEmpty()) {
            Snackbar.make(mRecyclerIngredients, "Ingredient field must be filled first.", Snackbar.LENGTH_LONG).show();
            return;
        }

        mIngredientsList.add(new Ingredient(textInputIngredient.getText().toString()));
        mIngredientsListAdapter.setIngredientsList(mIngredientsList);
        textInputIngredient.getText().clear();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(INGREDIENTS_LIST_INDEX, (ArrayList) mIngredientsList);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
