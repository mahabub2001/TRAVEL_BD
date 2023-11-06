package com.example.ourtravelapp;

public class Resort_booking_helper_class {
    private  String resortname;
    private  String resortaddress;
    private  String customerusername;
    private  String acornonac;
    private  String numberofpersons;
    private  String roomtype;
    private  String resortowner;

    private  String numberofrooms;
    private  String checkindate;
    private  String checkoutdate;
    private  String customerphone;
    private  String cutomermail;

    public Resort_booking_helper_class(){

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

    public String getCustomerusername() {
        return customerusername;
    }

    public void setCustomerusername(String customerusername) {
        this.customerusername = customerusername;
    }

    public String getAcornonac() {
        return acornonac;
    }

    public void setAcornonac(String acornonac) {
        this.acornonac = acornonac;
    }

    public String getNumberofpersons() {
        return numberofpersons;
    }

    public void setNumberofpersons(String numberofpersons) {
        this.numberofpersons = numberofpersons;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getCheckindate() {
        return checkindate;
    }

    public void setCheckindate(String checkindate) {
        this.checkindate = checkindate;
    }

    public String getCheckoutdate() {
        return checkoutdate;
    }

    public void setCheckoutdate(String checkoutdate) {
        this.checkoutdate = checkoutdate;
    }

    public String getCustomerphone() {
        return customerphone;
    }

    public void setCustomerphone(String customerphone) {
        this.customerphone = customerphone;
    }

    public String getCutomermail() {
        return cutomermail;
    }

    public void setCutomermail(String cutomermail) {
        this.cutomermail = cutomermail;
    }

    public String getResortowner() {
        return resortowner;
    }

    public void setResortowner(String resortowner) {
        this.resortowner = resortowner;
    }

    public String getNumberofrooms() {
        return numberofrooms;
    }

    public void setNumberofrooms(String numberofrooms) {
        this.numberofrooms = numberofrooms;
    }

    public Resort_booking_helper_class(String resortname, String resortaddress, String customerusername, String acornonac, String numberofpersons, String roomtype,String numberofrooms, String checkindate, String checkoutdate, String customerphone, String cutomermail,String resortowner) {
        this.resortname = resortname;
        this.resortaddress = resortaddress;
        this.customerusername = customerusername;
        this.acornonac = acornonac;
        this.numberofpersons = numberofpersons;
        this.roomtype = roomtype;
        this.numberofrooms=numberofrooms;
        this.checkindate = checkindate;
        this.checkoutdate = checkoutdate;
        this.customerphone = customerphone;
        this.cutomermail = cutomermail;
        this.resortowner=resortowner;
    }
}
