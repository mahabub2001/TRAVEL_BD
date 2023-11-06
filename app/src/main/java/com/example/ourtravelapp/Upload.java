package com.example.ourtravelapp;

public class Upload {
    private  String imageurl;
    private String resortname;
    private String resortaddress;
    private String resortphone;
    private String resortmail;
    private  String owner_username;




    public Upload(){

    }

    public Upload(String imageurl, String resortname, String resortaddress, String resortphone, String resortmail,String owner_username) {
        this.imageurl = imageurl;
        this.resortname = resortname;
        this.resortaddress = resortaddress;
        this.resortphone = resortphone;
        this.resortmail = resortmail;
        this.owner_username=owner_username;
    }


    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getResortname() {
        return resortname;
    }

    public void setResortname(String resortname) {
        this.resortname = resortname;
    }

    public String getResortaddress() {
        return resortaddress;
    }

    public void setResortaddress(String resortaddress) {
        this.resortaddress = resortaddress;
    }

    public String getResortphone() {
        return resortphone;
    }

    public void setResortphone(String resortphone) {
        this.resortphone = resortphone;
    }

    public String getResortmail() {
        return resortmail;
    }

    public void setResortmail(String resortmail) {
        this.resortmail = resortmail;
    }

    public String getOwner_username() {
        return owner_username;
    }

    public void setOwner_username(String owner_username) {
        this.owner_username = owner_username;
    }
}
