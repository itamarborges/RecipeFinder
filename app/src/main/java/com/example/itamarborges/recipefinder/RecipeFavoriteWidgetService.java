package com.example.itamarborges.recipefinder;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.itamarborges.recipefinder.pojo.Recipe;

import java.util.ArrayList;

/**
 * Created by itamarborges on 20/03/18.
 */


public class RecipeFavoriteWidgetService extends RemoteViewsService {

    public static final String FAVORITES_RECIPE_INGREDIENTS = "ingredients";

    ArrayList<Recipe> mFavoriteRecipesIngredients;
    ArrayList<String> mFavoriteRecipes;
    String testExtra;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
//        mFavoriteRecipesIngredients = (ArrayList) intent.getParcelableArrayListExtra(FAVORITES_RECIPE_INGREDIENTS);
//        intent.setExtrasClassLoader(Recipe.class.getClassLoader());
        mFavoriteRecipes = (ArrayList) intent.getStringArrayListExtra(FAVORITES_RECIPE_INGREDIENTS);

//        testExtra = intent.getStringExtra("teste");
//        return new BakingRemoteViewsFactory(this.getApplicationContext(), mFavoriteRecipesIngredients);
//        return new BakingRemoteViewsFactory(this.getApplicationContext(), testExtra);
        return new BakingRemoteViewsFactory(this.getApplicationContext(), mFavoriteRecipes);
    }
}

class BakingRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    Context mContext;
    ArrayList<Recipe> mList = new ArrayList<>();
    ArrayList<String> mListRecipes = new ArrayList<>();
    String mtestExtra;

//    public BakingRemoteViewsFactory(Context applicationContext, ArrayList<Recipe> list) {
//    public BakingRemoteViewsFactory(Context applicationContext, String testExtra) {
public BakingRemoteViewsFactory(Context applicationContext, ArrayList<String> listRecipes) {
        mContext = applicationContext;
//        mtestExtra = testExtra;
//        mList = list;
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

//    @Override
//    public int getCount() {
//        return mList == null ? 0 : mList.size();
//    }
//    @Override
//    public int getCount() {
//        return 2;
//    }

    @Override
    public int getCount() {
        return mListRecipes == null ? 0 : mListRecipes.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
//        if (mList == null || mList.size() == 0) return null;
        if (mListRecipes == null || mListRecipes.size() == 0) return null;

        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.recipe_favorite_widget_item);
//        rv.setTextViewText(R.id.appwidget_recipe_favorite_name, mtestExtra);
        rv.setTextViewText(R.id.appwidget_recipe_favorite_name, mListRecipes.get(i));

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
