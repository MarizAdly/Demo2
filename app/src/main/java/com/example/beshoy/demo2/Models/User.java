package com.example.beshoy.demo2.Models;

import java.io.Serializable;
import java.net.URL;

public class User implements Serializable {

    private String DisplayName;
    private String BirthDate;
    private String EmailAddress;
    private String PhoneNo;
    private String UID;
    private String Address;
    private String UserImage;
    private double uLat;
    private double ulng;

    public User (){

    }

    public User ( String displayName, String birthDate, String emailAddress, String phoneNo, String UID, String address, String userImage, double uLat, double ulng ) {
        this.DisplayName = displayName;
        this.BirthDate = birthDate;
        this.EmailAddress = emailAddress;
        this.PhoneNo = phoneNo;
        this.UID = UID;
        this.Address = address;
        this.UserImage = userImage;
        this.uLat = uLat;
        this.ulng = ulng;
    }


    public String getAddress ( ) {
        return Address;
    }

    public void setAddress ( String address ) {
        Address = address;
    }

    public String getUserImage() {
        return UserImage;
    }

    public void setUserImage(String userImage) {
        UserImage = userImage;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public double getuLat() {
        return uLat;
    }

    public void setuLat(double uLat) {
        this.uLat = uLat;
    }

    public double getUlng() {
        return ulng;
    }

    public void setUlng(double ulng) {
        this.ulng = ulng;
    }


}
