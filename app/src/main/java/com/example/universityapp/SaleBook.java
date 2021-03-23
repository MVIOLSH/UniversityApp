package com.example.universityapp;

import android.os.Parcel;
import android.os.Parcelable;

public class SaleBook implements Parcelable
{
    private String title, author, uName, seller_email,  description, price, saleId;


    public SaleBook(String title, String author, String description, String seller, String seller_email, String price, String saleId) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.uName = seller;
        this.seller_email = seller_email;
        this.price = price;
        this.saleId = saleId;

    }

    public SaleBook(){}


    protected SaleBook(Parcel in) {
        title = in.readString();
        author = in.readString();
        uName = in.readString();
        seller_email = in.readString();
        description = in.readString();
        price = in.readString();
        saleId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(uName);
        dest.writeString(seller_email);
        dest.writeString(description);
        dest.writeString(price);
        dest.writeString(saleId);
    }

    public static final Creator<SaleBook> CREATOR = new Creator<SaleBook>() {
        @Override
        public SaleBook createFromParcel(Parcel in) {
            return new SaleBook(in);
        }

        @Override
        public SaleBook[] newArray(int size) {
            return new SaleBook[size];
        }
    };

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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getsaleId() {
        return saleId;
    }

    public void setuId(String saleId) {
        this.saleId = saleId;
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
