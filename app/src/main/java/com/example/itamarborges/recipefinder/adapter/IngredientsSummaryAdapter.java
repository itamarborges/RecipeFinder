package com.example.itamarborges.recipefinder.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.itamarborges.recipefinder.R;
import com.example.itamarborges.recipefinder.pojo.Ingredient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by itamarborges on 07/05/18.
 */

public class IngredientsSummaryAdapter extends RecyclerView.Adapter<IngredientsSummaryAdapter.IngredientsListViewHolder> {

    private static final String TAG = IngredientsSummaryAdapter.class.getSimpleName();

    public IngredientsSummaryAdapter(List<Ingredient> mIngredientsList) {
        this.mIngredientsList = mIngredientsList;
    }

    public List<Ingredient> getIngredientsList() {
        return mIngredientsList;
    }

    public void setIngredientsList(List<Ingredient> mIngredientsList) {
        this.mIngredientsList = mIngredientsList;
        this.notifyDataSetChanged();
    }

    private List<Ingredient> mIngredientsList;

    @NonNull
    @Override
    public IngredientsListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.ingredients_summary;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        return new IngredientsListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsListViewHolder holder, int position) {
        holder.bind(mIngredientsList.get(position));
    }

    @Override
    public int getItemCount() {
        return (mIngredientsList == null) ? 0 : mIngredientsList.size();
    }

    public class IngredientsListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textIngredientSummary)
        TextView mTxtIngredientName;

        public IngredientsListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Ingredient ingredient) {
            mTxtIngredientName.setText(ingredient.getName());
        }
    }
}
