package com.ad0820e.appbanhang.model;

public class Slider {
    private int id;
    private String url;
    private int categoryId;
    private String description;

    public Slider(int id, String url, int categoryId, String description) {
        this.id = id;
        this.url = url;
        this.categoryId = categoryId;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
