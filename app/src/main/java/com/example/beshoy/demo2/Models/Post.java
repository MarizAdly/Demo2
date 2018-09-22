package com.example.beshoy.demo2.Models;

import android.net.Uri;
import android.opengl.Matrix;

import java.io.Serializable;
import java.net.URI;
import java.net.URL;

public class Post implements Serializable {
    private String DisplayName;
    private String postText;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    private String userID;

    public URL getUserImage() {
        return UserImage;
    }

    public void setUserImage(URL userImage) {
        UserImage = userImage;
    }

    public URL getPostPhoto() {
        return postPhoto;
    }

    public void setPostPhoto(URL postPhoto) {
        this.postPhoto = postPhoto;
    }

    private URL UserImage, postPhoto;

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

}