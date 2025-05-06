package com.tigran.springcourse.dto;

import java.time.LocalDateTime;

public class AppointmentInfoDTO {
    private long appointmentId;
    private String barberFullName;
    private String barberPhotoPath;
    private String clientFullName;
    private String clientPhoneNumber;
    private LocalDateTime appointmentTime;
    private boolean hariCut;
    private boolean shaving;

    public AppointmentInfoDTO() {

    }

    public AppointmentInfoDTO(long appointmentId, String barberFullName, String barberPhotoPath, String clientFullName, String clientPhoneNumber, LocalDateTime appointmentTime, boolean hariCut, boolean shaving) {
        this.appointmentId = appointmentId;
        this.barberFullName = barberFullName;
        this.barberPhotoPath = barberPhotoPath;
        this.clientFullName = clientFullName;
        this.clientPhoneNumber = clientPhoneNumber;
        this.appointmentTime = appointmentTime;
        this.hariCut = hariCut;
        this.shaving = shaving;
    }

    public long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getBarberFullName() {
        return barberFullName;
    }

    public void setBarberFullName(String barberFullName) {
        this.barberFullName = barberFullName;
    }

    public String getBarberPhotoPath() {
        return barberPhotoPath;
    }

    public void setBarberPhotoPath(String barberPhotoPath) {
        this.barberPhotoPath = barberPhotoPath;
    }

    public String getClientFullName() {
        return clientFullName;
    }

    public void setClientFullName(String clientFullName) {
        this.clientFullName = clientFullName;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public boolean isHariCut() {
        return hariCut;
    }

    public void setHariCut(boolean hariCut) {
        this.hariCut = hariCut;
    }

    public boolean isShaving() {
        return shaving;
    }

    public void setShaving(boolean shaving) {
        this.shaving = shaving;
    }
}