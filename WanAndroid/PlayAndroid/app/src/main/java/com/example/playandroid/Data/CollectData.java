package com.example.playandroid.Data;

public class CollectData {

    private String title;
    private String niceDate;
    private String link;
    private String desc;
    private String chapterName;
    private String superChapterName;
    private int id;
    private int originId;

    public CollectData(String title, String niceDate, String link, String desc, String chapterName, String superChapterName, int id,int originId) {
        this.title = title;
        this.niceDate = niceDate;
        this.link = link;
        this.desc = desc;
        this.chapterName = chapterName;
        this.superChapterName = superChapterName;
        this.id = id;
        this.originId = originId;
    }

    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getSuperChapterName() {
        return superChapterName;
    }

    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
