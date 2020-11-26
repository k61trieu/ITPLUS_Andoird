package com.example.sellingapp08.util;

public class Slider {
    private int id;
    private String url;
    private String categoryid;
    private String description;

    public Slider() {
    }

    public Slider(int id, String url, String categoryid, String description) {
        this.id = id;
        this.url = url;
        this.categoryid = categoryid;
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

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
