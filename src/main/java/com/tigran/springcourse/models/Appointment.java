package com.tigran.springcourse.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public class Appointment {
    private int id;
    private int barberId;
    @Size(min = 2,max = 80, message = "Full name size should be between 2 and 80")
    private String fullName;
    private boolean hairCut;
    private boolean shaving;
    @Size(min = 5, max = 30, message = "Phone number size should be between 5 and 30")
    private String phoneNumber;
    @NotNull(message = "DateTime should not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime appointmentDateTime;
    public Appointment(){

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

    public boolean isHairCut() {
        return hairCut;
    }

    public void setHairCut(boolean hairCut) {
        this.hairCut = hairCut;
    }

    public boolean isShaving() {
        return shaving;
    }

    public void setShaving(boolean shaving) {
        this.shaving = shaving;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public int getBarberId() {
        return barberId;
    }

    public void setBarberId(int barberId) {
        this.barberId = barberId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
