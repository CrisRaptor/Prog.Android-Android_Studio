/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.apicat.cat;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author CrisGC
 */
public class Cat {

    ArrayList<Breed> breeds = new ArrayList<Breed>();
//    ArrayList<Categories> categories = new ArrayList<>();
    private String id;
    private String url;
    private float width;
    private float height;

    // Getter Methods 
    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public ArrayList<Breed> getBreeds() {
        return breeds;
    }

//    public ArrayList<Categories> getCategories() {
//        return categories;
//    }

    // Setter Methods
    public void setId(String id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setBreeds(ArrayList<Breed> breeds) {
        this.breeds = breeds;
    }

//    public void setCategories(ArrayList<Categories> categories) {
//        this.categories = categories;
//    }

    @Override
    public String toString() {
        return "Cat{" +
                "breeds=" + Arrays.toString(breeds.toArray())  +
//                ", categories=" + Arrays.toString(categories.toArray()) +
                ", id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
