package com.example.thehighbrow.visitormanagement;

class Courier {
    String name;
    String contact;
    String deliverto;
    String photoUrl;
    String time;
    String date;
    String id;

    public Courier(){}
    void Courier(){}



    public Courier(String name, String contact, String deliverto, String photoUrl, String time, String date,String id){
        this.name = name;
        this.contact = contact;
        this.deliverto = deliverto;
        this.photoUrl= photoUrl;
        this.time = time;
        this.date = date;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getDeliverto() {
        return deliverto;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setDeliverto(String deliverto) {
        this.deliverto = deliverto;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}



