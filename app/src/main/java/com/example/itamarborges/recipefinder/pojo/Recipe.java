package com.example.itamarborges.recipefinder.pojo;

import java.io.Serializable;

/**
 * Created by itamarborges on 12/05/18.
 */

public class Recipe implements Serializable {

    int id;
    String uri;
    String label;
    String urlImage;
    String source;
    String url;
    Double calories;

    public Recipe(int id, String uri, String label, String urlImage, String source, String url, Double calories) {
        this.id = id;
        this.uri = uri;
        this.label = label;
        this.urlImage = urlImage;
        this.source = source;
        this.url = url;
        this.calories = calories;
    }


    public String getUri() { return uri; }

    public void setUri(String uri) { this.uri = uri; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() { return label; }

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
