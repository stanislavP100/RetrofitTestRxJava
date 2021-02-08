package com.example.retrofittestrxjava;

import android.graphics.Bitmap;

public class Product {


    private long id;

    private String name;

    private Double price;

    private String image;

    private String description;

    private String pidCategory;

    private Bitmap imageBitmap;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPidCategory() {
        return pidCategory;
    }

    public void setPidCategory(String pidCategory) {
        this.pidCategory = pidCategory;
    }

    Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    Product() {
    }

}