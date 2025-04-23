package com.tigran.springcourse.models;

import jakarta.validation.constraints.*;

public class Barber {
    private int id;

    @NotEmpty(message = "Full name should not be empty")
    @Size(min = 2,max = 80, message = "Full name size should be between 2 and 80")
    private String fullName;
    @NotNull(message = "Haircut Price should not be empty")
    @Min(value = 0,message = "Hair cut price should be between 0 and 1000")
    @Max(value = 1000,message = "Hair cut price should be between 0 and 1000")
    private Integer hairCutPrice;
    @NotNull(message = "Shaving price should not be empty")
    @Min(value = 0,message = "Shaving price should be between 0 and 1000")
    @Max(value = 1000,message = "Shaving price should be between 0 and 1000")
    private Integer shavingPrice;
    private String photopath;

    public Barber(String fullName, Integer hairCutPrice, Integer shavingPrice) {
        this.fullName = fullName;
        this.hairCutPrice = hairCutPrice;
        this.shavingPrice = shavingPrice;
    }
    public Barber(){

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getHairCutPrice() {
        return hairCutPrice;
    }

    public void setHairCutPrice(Integer hairCutPrice) {
        this.hairCutPrice = hairCutPrice;
    }

    public Integer getShavingPrice() {
        return shavingPrice;
    }

    public void setShavingPrice(Integer shavingPrice) {
        this.shavingPrice = shavingPrice;
    }

    public String getPhotopath() {
        return photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }
}
