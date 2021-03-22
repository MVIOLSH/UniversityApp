package com.example.universityapp;

public class BookReview {
    private String uName, ISBN, text, date, title;



    public BookReview(String uName, String ISBN, String text, String date, String title) {
        this.uName = uName;
        this.ISBN = ISBN;
        this.text = text;
        this.date = date;
        this.title = title;

    }

    public BookReview(){}

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
