package com.example.itamarborges.recipefinder.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by itamarborges on 12/05/18.
 */

public class Recipe implements Serializable, Parcelable {

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


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(uri);
        dest.writeString(label);
        dest.writeString(urlImage);
        dest.writeString(source);
        dest.writeString(url);
        if (calories == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeDouble(calories);
        }
    }

    protected Recipe(Parcel in) {
        id = in.readInt();
        uri = in.readString();
        label = in.readString();
        urlImage = in.readString();
        source = in.readString();
        url = in.readString();
        calories = in.readByte() == 0x00 ? null : in.readDouble();
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
