package com.example.universityapp;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class LibraryBook implements Parcelable
{
    private String  title, author, isbn, category, imgUrl, sId, bookId;
    private boolean isReserved = false;


    public LibraryBook(String title, String author, String isbn, String category , boolean isReserved, String imgUrl, String sId, String bookId) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.isReserved = isReserved;
        this.imgUrl = imgUrl;
        this.sId = sId;
        this.bookId = bookId;

    }
    public LibraryBook(){}

    protected LibraryBook(Parcel in) {
        title = in.readString();
        author = in.readString();
        isbn = in.readString();
        category = in.readString();
        imgUrl = in.readString();
        sId = in.readString();
        bookId = in.readString();
        isReserved = in.readByte() != 0;
    }

    public static final Creator<LibraryBook> CREATOR = new Creator<LibraryBook>() {
        @Override
        public LibraryBook createFromParcel(Parcel in) {
            return new LibraryBook(in);
        }

        @Override
        public LibraryBook[] newArray(int size) {
            return new LibraryBook[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(isbn);
        dest.writeString(category);
        dest.writeString(imgUrl);
        dest.writeString(sId);
        dest.writeString(bookId);
        dest.writeByte((byte) (isReserved ? 1 : 0));
    }
}
