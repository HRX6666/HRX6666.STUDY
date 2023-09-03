package com.example.playandroid.Data;

public class SubilsData {
    private int id;
    private String name;
    private String cover;
    private String desc;
    private String author;
    private Boolean aBoolean;
    private String link;



    public  SubilsData(int id, String name,String cover,String desc,String author) {
        this.id = id;
        this.name = name;
        this.cover=cover;
        this.desc=desc;
        this.author=author;
        //this.link=link;,String link
    }
    public String getCover() {
        return cover;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
