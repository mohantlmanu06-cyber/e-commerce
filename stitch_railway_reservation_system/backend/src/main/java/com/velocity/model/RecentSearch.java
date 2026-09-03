package com.velocity.model;

public class RecentSearch {
    private String fromCode;
    private String fromName;
    private String toCode;
    private String toName;
    private String timeAgo;
    private String date;

    public RecentSearch() {}

    public RecentSearch(String fromCode, String fromName, String toCode, String toName, String timeAgo, String date) {
        this.fromCode = fromCode;
        this.fromName = fromName;
        this.toCode = toCode;
        this.toName = toName;
        this.timeAgo = timeAgo;
        this.date = date;
    }

    public String getFromCode() {
        return fromCode;
    }

    public void setFromCode(String fromCode) {
        this.fromCode = fromCode;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToCode() {
        return toCode;
    }

    public void setToCode(String toCode) {
        this.toCode = toCode;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
