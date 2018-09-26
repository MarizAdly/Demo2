package com.example.beshoy.demo2.Models;

import java.io.Serializable;
import java.net.URL;

public class Post implements Serializable {


    private String postPhoto, userPhoto;
    private String postText;

    public String getUserPhoto ( ) {
        return userPhoto;
    }

    public void setUserPhoto ( String userPhoto ) {
        this.userPhoto = userPhoto;
    }

    public String getUserName ( ) {
        return userName;
    }

    public void setUserName ( String displayName ) {
        this.userName = userName;
    }

    private String userName;
    private String userID;

    public Post(){

    }

    public Post ( String postPhoto, String postText, String userName, String userID ) {
        this.postPhoto = postPhoto;
        this.postText = postText;
        this.userName = userName;
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPostPhoto ( ) {
        return postPhoto;
    }

    public void setPostPhoto ( String postPhoto ) {
        this.postPhoto = postPhoto;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

}