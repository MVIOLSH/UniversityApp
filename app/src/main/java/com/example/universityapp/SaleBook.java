package com.example.universityapp;

public class SaleBook
{
    private String title, author, uName, seller_email,  description;


    public SaleBook(String title, String author, String description, String seller, String seller_email, int edition) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.uName = seller;
        this.seller_email = seller_email;

    }

    public SaleBook(){}

    public String getTitle() {  return title;   }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSeller() {
        return uName;
    }

    public void setSeller(String seller) {
        this.uName = seller;
    }

    public String getSeller_email() {
        return seller_email;
    }

    public void setSeller_email(String seller_email) {
        this.seller_email = seller_email;
    }

      public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
