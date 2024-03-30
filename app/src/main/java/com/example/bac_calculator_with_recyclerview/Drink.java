package com.example.bac_calculator_with_recyclerview;

import java.util.Date;

public class Drink {
    private double alcohol, size;
    private Date addedOn;

    public Drink(double alcohol, double size) {
        this.alcohol = alcohol;
        this.size = size;
        addedOn = new Date();
    }

    public Drink() {
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
