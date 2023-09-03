package com.example.playandroid.Data;

public class MessageData {
//      "category": 1,
//              "date": 1691049807000,
//              "fromUser": "136013903@qq.com",
//              "fromUserId": 71433,
//              "fullLink": "https://wanandroid.com/wenda/show/26673",
//              "id": 761277,
//              "isRead": 1,
//              "link": "/wenda/show/26673",
//              "message": "海信的狗系统也是,,.,用着用着,...总是提示内存不足,还老是重启...最后刷机好了...",
//              "niceDate": "2023-08-03 16:03",
//              "tag": "评论回复",
//              "title": "回复了@xujiafeng",
//              "userId": 150613
    private String title;
    private String niceDate;
    private String link;
    private String desc;
    private String fromUser;
    private String message;
    private String tag;
    private int id;
    private int originId;

    public MessageData(String title, String niceDate, String link, String desc, String fromUser, String message, String tag, int id, int originId) {
        this.title = title;
        this.niceDate = niceDate;
        this.link = link;
        this.desc = desc;
        this.fromUser = fromUser;
        this.message = message;
        this.tag = tag;
        this.id = id;
        this.originId = originId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOriginId() {
        return originId;
    }

    public void setOriginId(int originId) {
        this.originId = originId;
    }

    public String getNiceDate() {
        return niceDate;
    }

    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
