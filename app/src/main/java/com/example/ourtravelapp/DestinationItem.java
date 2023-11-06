package com.example.ourtravelapp;

public class DestinationItem extends DestinationActivity{

    private String destinationName;
    private int imageResource;

    public DestinationItem(String destinationName,int imageResource)
    {
        this.destinationName=destinationName;
        this.imageResource=imageResource;

    }
    public String getDestinationName() {
        return destinationName;
    }

    public int getImageResource() {
        return imageResource;
    }

}
