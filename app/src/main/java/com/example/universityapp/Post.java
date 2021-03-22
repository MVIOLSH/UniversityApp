package com.example.universityapp;

import java.util.Date;

public class Post
{
    private String uName, email, title, postText, date, comment;

    public Post(String uName, String email, String title, String postText, String date, String comment) {
        this.uName = uName;
        this.email = email;
        this.title = title;
        this.postText = postText;
        this.date = date;
        this.comment = comment;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date.toString();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
