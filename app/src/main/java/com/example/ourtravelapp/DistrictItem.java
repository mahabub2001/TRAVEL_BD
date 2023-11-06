package com.example.ourtravelapp;

public class DistrictItem extends DistrictActivity{
    private String districtName;
    private int imageResource;

    public DistrictItem(String districtName, int imageResource) {
        this.districtName = districtName;
        this.imageResource = imageResource;
    }

    public String getDistrictName() {
        return districtName;
    }

    public int getImageResource() {
        return imageResource;
    }
}
