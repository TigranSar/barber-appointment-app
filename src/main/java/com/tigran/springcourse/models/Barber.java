package com.tigran.springcourse.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Barber {
    private int id;
    private String fullName;
    private int hairCutPrice;
    private int shavingPrice;
    private String photopath;

    public Barber(String fullName, int hairCutPrice, int shavingPrice) {
        this.fullName = fullName;
        this.hairCutPrice = hairCutPrice;
        this.shavingPrice = shavingPrice;
    }
    public Barber(){

    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public int getHairCutPrice() {
        return hairCutPrice;
    }

    public void setHairCutPrice(int hairCutPrice) {
        this.hairCutPrice = hairCutPrice;
    }

    public int getShavingPrice() {
        return shavingPrice;
    }

    public void setShavingPrice(int shavingPrice) {
        this.shavingPrice = shavingPrice;
    }

    public String getPhotopath() {
        return photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
