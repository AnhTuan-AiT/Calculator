package com.example.listviewexamples.models;

public class GmailModel {

    private boolean isReaded;

    private String sender;

    private String title;

    private String content;

    private String time;

    private boolean iStarred;

    public GmailModel(boolean isReaded, String sender, String title, String content, String time, boolean iStarred) {
        this.isReaded = isReaded;
        this.sender = sender;
        this.title = title;
        this.content = content;
        this.time = time;
        this.iStarred = iStarred;
    }

    public boolean isReaded() {
        return isReaded;
    }

    public void setReaded(boolean readed) {
        isReaded = readed;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isStarred() {
        return iStarred;
    }

    public void setiStarred(boolean iStarred) {
        this.iStarred = iStarred;
    }
}
