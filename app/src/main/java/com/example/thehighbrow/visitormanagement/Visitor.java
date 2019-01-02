package com.example.thehighbrow.visitormanagement;

import android.net.Uri;

public class Visitor {

    String name;
    String contact;
    String host;
    String photoUrl;
    Uri photouri;
    String time;
    String date;

    void Visitor(){

    }

    public Visitor(){}
    
    public Visitor(String name, String contact, String host, String photoUrl, String time, String date){
        this.name = name;
        this.contact = contact;
        this.host = host;
        this.photoUrl= photoUrl;
        this.time=time;
        this.date=date;
    }


    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getHost() {
        return host;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
