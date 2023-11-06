package com.example.ourtravelapp;

public class BusTicketHelperClass {
    String name,nid,from ,to,ticketnumber;
    public BusTicketHelperClass(){

    }
    public BusTicketHelperClass(String name, String nid, String from, String to, String ticketnumber) {
        this.name = name;
        this.nid = nid;
        this.from = from;
        this.to = to;
        this.ticketnumber = ticketnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTicketnumber() {
        return ticketnumber;
    }

    public void setTicketnumber(String ticketnumber) {
        this.ticketnumber = ticketnumber;
    }
}
