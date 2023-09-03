package com.example.playandroid.Data;

public class BannerData {
    // desc, id, imagePath, isVisible, order,title ,type, url
    private String desc;//name
    private String imagePath;
    private String title;
    private String url;//link
    private int id;
    private int isVisible;
    private int order;
    private int type;

    public BannerData(String desc, int id, String imagePath, int isVisible, int order, String title, int type, String url) {
        this.desc=desc;
        this.imagePath=imagePath;
        this.title=title;
        this.url=url;
        this.id=id;
        this.isVisible=isVisible;
        this.order=order;
        this.type=type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
