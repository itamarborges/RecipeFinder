package com.example.itamarborges.recipefinder.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.itamarborges.recipefinder.pojo.Ingredient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by itamarborges on 20/05/18.
 */

public class IngredientModel {

    public static final String INGREDIENTS_LIST_INDEX = "ingredientsListIndex";

    public static void setArrayListToSharedPreferences(Context context, String key, List<Ingredient> list) {

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();

        Set<String> s = new HashSet<>();

        for (Ingredient ing : list) {
            s.add(ing.getName());
        }

        editor.putStringSet(key, s);
        editor.apply();
    }


    public static ArrayList<Ingredient> getArrayListFromSharedPreferences(Context context, String key) {

        ArrayList<Ingredient> mIngredients = new ArrayList<>();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        Set<String> set = sp.getStringSet(key, null);

        if (set != null) {
            for (String s : set) {
                mIngredients.add(new Ingredient(s));
            }
        }
        return mIngredients;
    }

    public static class IngredientComparator implements Comparator<Ingredient> {
        @Override
        public int compare(Ingredient o1, Ingredient o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }
}
