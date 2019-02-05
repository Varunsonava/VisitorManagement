package com.example.thehighbrow.visitormanagement;

import android.net.Uri;

public class Visitor {

    String name;
    String contact;
    String host;
    String photoUrl;
    Uri photouri;
    String time;
    String outtime;
    String date;
    String companion;
    String id;


    void Visitor(){

    }

    public Visitor(){}
    
    public Visitor(String name, String contact, String host, String photoUrl, String time, String outtime,String date, String companion,     String id){
        this.name = name;
        this.contact = contact;
        this.host = host;
        this.photoUrl= photoUrl;
        this.time=time;
        this.outtime=outtime;
        this.date=date;
        this.companion=companion;
        this.id=id;

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

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanion() {
        return companion;
    }

    public void setCompanion(String companion) {
        this.companion = companion;
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
