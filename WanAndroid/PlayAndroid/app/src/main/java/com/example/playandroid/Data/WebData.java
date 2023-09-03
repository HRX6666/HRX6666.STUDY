package com.example.playandroid.Data;

public class WebData {
    private String category;
    private String link;
    private String name;

    public WebData(String category, String link, String name) {
        this.category = category;
        this.link = link;
        this.name = name;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
