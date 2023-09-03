package com.example.playandroid.Data;

public class CoinData {
    private String desc;
    private String reason;
    private String coinCount;
    private String level;
    private String username;
    private String rank;

    public CoinData(String desc, String reason, String coinCount, String level, String username, String rank) {
        this.desc = desc;
        this.reason = reason;
        this.coinCount = coinCount;
        this.level = level;
        this.username = username;
        this.rank = rank;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(String coinCount) {
        this.coinCount = coinCount;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
