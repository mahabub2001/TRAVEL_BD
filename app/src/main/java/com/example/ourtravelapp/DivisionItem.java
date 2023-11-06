package com.example.ourtravelapp;

public class DivisionItem extends DivisionActivity{

    private int image;
    private String division;

    public DivisionItem(int image, String division) {
        this.image = image;
        this.division = division;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

}
