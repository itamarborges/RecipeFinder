package com.example.itamarborges.recipefinder;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

/**
 * Created by itamarborges on 20/03/18.
 */


public class RecipeFavoriteWidgetService extends RemoteViewsService {

    public static final String FAVORITES_RECIPE_INGREDIENTS = "ingredients";

    ArrayList<String> mFavoriteRecipes;
    String testExtra;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        mFavoriteRecipes = (ArrayList) intent.getStringArrayListExtra(FAVORITES_RECIPE_INGREDIENTS);
        return new BakingRemoteViewsFactory(this.getApplicationContext(), mFavoriteRecipes);
    }
}

class BakingRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    Context mContext;
    ArrayList<String> mListRecipes = new ArrayList<>();

public BakingRemoteViewsFactory(Context applicationContext, ArrayList<String> listRecipes) {
        mContext = applicationContext;
        mListRecipes = listRecipes;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mListRecipes == null ? 0 : mListRecipes.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        if (mListRecipes == null || mListRecipes.size() == 0) return null;

        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.recipe_favorite_widget_item);
        rv.setTextViewText(R.id.appwidget_recipe_favorite_name, mListRecipes.get(i));

        Intent fillInIntent = new Intent();
        rv.setOnClickFillInIntent(R.id.widget_item_layout, fillInIntent);

        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
