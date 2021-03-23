package com.example.universityapp;

public class Activity
{
    String id;
    String date;
    String location;
    String time;
    String description;

    public Activity(String id, String date, String location, String time, String description) {
        this.id = id;
        this.date = date;
        this.location = location;
        this.time = time;
        this.description = description;
    }

    public Activity(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
