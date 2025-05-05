package com.tigran.springcourse.validator;

import com.tigran.springcourse.dao.AppointmentDAO;
import com.tigran.springcourse.models.Appointment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@Component
public class AppointmentValidator implements Validator {
    private AppointmentDAO appointmentDAO;

    public AppointmentValidator(AppointmentDAO appointmentDAO) {
        this.appointmentDAO = appointmentDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Appointment.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Appointment appointment = (Appointment)target;
        String fullName = appointment.getFullName();
        String phoneNumber = appointment.getPhoneNumber();
        if (fullName != null) {
            if (!appointment.getFullName().matches("^[A-Za-zА-Яа-яЁё\\s]+$")) {
                errors.rejectValue("fullName", "Invalid.fullName", "Full name cannot contain digits");
            }
        }
        if (phoneNumber != null){
            if (!phoneNumber.matches("^\\+?\\d+$")){
                errors.rejectValue("phoneNumber","Invalid.phoneNumber","Invalid phone number");
            }
        }
        if(appointment.getAppointmentDateTime() != null) {
            if (appointment.getAppointmentDateTime().isBefore(LocalDateTime.now().minusMinutes(1))) {
                errors.rejectValue("appointmentDateTime", "","Date and time cannot be in the past");
            }
        }
        if (!appointmentDAO.isTimeAvailable(appointment.getAppointmentDateTime(),appointment.getBarberId())){
            errors.rejectValue("appointmentDateTime","","Time is already taken");
        }
        if (!appointment.isHairCut() && !appointment.isShaving()){
            errors.rejectValue("shaving","","Enter the service type");
        }

    }
}
