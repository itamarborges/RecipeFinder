package com.example.itamarborges.recipefinder.adapter;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itamarborges.recipefinder.R;
import com.example.itamarborges.recipefinder.RecipeDetailActivity;
import com.example.itamarborges.recipefinder.pojo.Recipe;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by itamarborges on 16/05/18.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeHolder> {

    private static final String TAG = RecipeAdapter.class.getSimpleName();

    public List<Recipe> getRecipes() {
        return mRecipes;
    }

    public void setRecipes(List<Recipe> mRecipes) {
        this.mRecipes = mRecipes;
        this.notifyDataSetChanged();
    }

    private List<Recipe> mRecipes;

    public RecipeAdapter(List<Recipe> recipes) {
        mRecipes = recipes;
    }

    @Override
    public RecipeAdapter.RecipeHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.card_view_recipe;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        RecipeHolder viewHolder = new RecipeHolder(view, context);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecipeAdapter.RecipeHolder holder, int position) {
        holder.bind(mRecipes.get(position));

    }

    @Override
    public int getItemCount() {
        return (mRecipes == null) ? 0 : mRecipes.size();
    }

    class RecipeHolder extends RecyclerView.ViewHolder {

        Context mContext;

        @BindView(R.id.recipe_name) TextView mRecipeName;
        @BindView(R.id.recipe_source) TextView mRecipeSource;
        @BindView(R.id.recipe_calories) TextView mRecipeCalories;
        @BindView(R.id.cv_recipe) CardView mCvRecipe;
        @BindView(R.id.img_recipe) ImageView mImage;

        void bind(final Recipe recipe) {

            if (recipe.getUrlImage().isEmpty()) {
                mImage.setImageResource(R.drawable.cooking);
            } else {
                Picasso.with(mContext)
                        .load(recipe.getUrlImage())
                        .error(R.drawable.cooking)
                        .placeholder(R.drawable.cooking)
                        .into(mImage);
            }

            mRecipeName.setText(recipe.getLabel());
            mRecipeSource.setText("Source: ".concat(String.valueOf(recipe.getSource())));
            mRecipeCalories.setText("Calories: ".concat(String.format("%.2f", recipe.getCalories())));

            mCvRecipe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(mContext, RecipeDetailActivity.class);
                    intent.putExtra(RecipeDetailActivity.RECIPE_INDEX, (Serializable) recipe);

                    mContext.startActivity(intent);
                }
            });
        }

        public RecipeHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = context;
        }
    }
}
