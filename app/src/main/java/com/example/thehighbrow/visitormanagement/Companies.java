package com.example.thehighbrow.visitormanagement;

class Companies {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return name;
    }
}
