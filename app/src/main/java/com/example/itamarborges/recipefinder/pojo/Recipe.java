package com.example.itamarborges.recipefinder.pojo;

/**
 * Created by itamarborges on 12/05/18.
 */

public class Recipe {
    String label;
    String urlImage;
    String source;
    String url;
    Double calories;

    public Recipe(String label, String urlImage, String source, String url, Double calories) {
        this.label = label;
        this.urlImage = urlImage;
        this.source = source;
        this.url = url;
        this.calories = calories;
    }

    public String getLabel() {

        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

}
