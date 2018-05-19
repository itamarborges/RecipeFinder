package com.example.itamarborges.recipefinder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.itamarborges.recipefinder.model.RecipeModel;
import com.example.itamarborges.recipefinder.pojo.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeDetailActivity extends AppCompatActivity {

    public static final String RECIPE_INDEX = "recipeIndex";

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
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(recipe.getUrl());

            setTitle(recipe.getLabel());
        }

        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecipeModel RecipeModel = new RecipeModel();
                fab.setActivated(!fab.isActivated());

                if (fab.isActivated()) {
                    Uri uri = RecipeModel.insert(getApplicationContext(), recipe.getLabel(), recipe.getUrlImage(), recipe.getSource(), recipe.getUrl(), recipe.getCalories());
                } else {
                    RecipeModel.delete(getApplicationContext(), recipe.getId());
                }
                
                
                
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
