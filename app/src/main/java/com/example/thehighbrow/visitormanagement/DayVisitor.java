package com.example.thehighbrow.visitormanagement;

import android.net.Uri;

public class DayVisitor {
    String name;
    String contact;
    String email;
    String photoUrl;
    String gst;
    String invoice;
    String time;
    String id;
    String date;
    String outtime;
    void dayVisitor(){

    }

    public DayVisitor(){}

    public DayVisitor(String name, String contact, String email,String gst, String invoice, String photoUrl, String time, String date, String id, String outtime){
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.photoUrl= photoUrl;
        this.gst= gst;
        this.invoice= invoice;
        this.time = time;
        this.date = date;
        this.id=id;
        this.outtime=outtime;
    }


    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
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

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getGst() {
        return gst;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvoice() {
        return invoice;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setHost(String host) {
        this.email = host;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
