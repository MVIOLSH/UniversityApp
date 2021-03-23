package com.example.universityapp;

import android.os.Parcel;
import android.os.Parcelable;

public class ForumTopic implements Parcelable
{
    String id, uName, title, content, parentPost, category;

    Boolean isAReply;

    public ForumTopic(String id, String uName, String title, String content, String parentPost, String category, Boolean isAReply) {
        this.id = id;
        this.uName = uName;
        this.title = title;
        this.content = content;
        this.parentPost = parentPost;
        this.category = category;

        this.isAReply = isAReply;
    }
    public ForumTopic(){}

    protected ForumTopic(Parcel in) {
        id = in.readString();
        uName = in.readString();
        title = in.readString();
        content = in.readString();
        parentPost = in.readString();
        category = in.readString();

        byte tmpIsAReply = in.readByte();
        isAReply = tmpIsAReply == 0 ? null : tmpIsAReply == 1;
    }

    public static final Creator<ForumTopic> CREATOR = new Creator<ForumTopic>() {
        @Override
        public ForumTopic createFromParcel(Parcel in) {
            return new ForumTopic(in);
        }

        @Override
        public ForumTopic[] newArray(int size) {
            return new ForumTopic[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
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

    public String getParentPost() {
        return parentPost;
    }

    public void setParentPost(String parentPost) {
        this.parentPost = parentPost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public Boolean getAReply() {
        return isAReply;
    }

    public void setAReply(Boolean AReply) {
        isAReply = AReply;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(uName);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(parentPost);
        dest.writeString(category);
        dest.writeByte((byte) (isAReply == null ? 0 : isAReply ? 1 : 2));
    }
}
