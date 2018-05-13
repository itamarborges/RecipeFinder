package com.example.itamarborges.recipefinder.pojo;

import android.text.Editable;

import java.io.Serializable;

/**
 * Created by itamarborges on 07/05/18.
 */

public class Ingredient implements Serializable {
    String name;

    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        Ingredient ing = (Ingredient) obj;
        return this.name.equals(ing.getName());
    }
}
