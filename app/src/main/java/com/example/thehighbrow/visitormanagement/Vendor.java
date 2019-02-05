package com.example.thehighbrow.visitormanagement;

import android.net.Uri;

class Vendor {

    String name;
    String contact;
    String host;
    String photoUrl;
    String time;
    String date;
    String outtime;
    String id;


    void Vendor() {

    }

    public Vendor() {
    }

    public Vendor(String name, String contact, String host, String photoUrl, String time, String date, String id, String outtime){
        this.name = name;
        this.contact = contact;
        this.host = host;
        this.photoUrl = photoUrl;
        this.time = time;
        this.date = date;
        this.id=id;
        this.outtime = outtime;

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

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
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
}
