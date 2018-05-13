package com.example.itamarborges.recipefinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.itamarborges.recipefinder.adapter.IngredientsListAdapter;
import com.example.itamarborges.recipefinder.adapter.IngredientsSummaryAdapter;
import com.example.itamarborges.recipefinder.pojo.Ingredient;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipesListActivity extends AppCompatActivity {

    public static final String INGREDIENTS_INDEX = "ingredientsIndex";

    @BindView(R.id.rv_choosen_ingredients) RecyclerView mRecyclerIngredients;

    List<Ingredient> mIngredientsList;
    IngredientsSummaryAdapter mIngredientsSummaryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            mIngredientsList = (ArrayList) getIntent().getParcelableArrayListExtra(INGREDIENTS_INDEX);
        }

        mIngredientsSummaryAdapter = new IngredientsSummaryAdapter(mIngredientsList);

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(getBaseContext());
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);


        mRecyclerIngredients.setAdapter(mIngredientsSummaryAdapter);
        mRecyclerIngredients.setHasFixedSize(true);
        mRecyclerIngredients.setNestedScrollingEnabled(false);
        mRecyclerIngredients.setLayoutManager(layoutManager);


    }
}
