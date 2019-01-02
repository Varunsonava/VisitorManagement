package com.example.thehighbrow.visitormanagement;

import android.net.Uri;

public class Lead {
    String name;
    String contact;
    String email;
    String photoUrl;
    String reach;
    String time;
    String date;
    Uri photouri;

    void Lead(){

    }

    public Lead(){}
    public Lead(String name, String contact, String email,String reach, String photoUrl,String time, String date){
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.photoUrl= photoUrl;
        this.reach= reach;
        this.time = time;
        this.date = date;


    }


    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }


    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }


    public String getReach() {
        return reach;
    }


    public void setReach(String reach) {
        this.reach = reach;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
