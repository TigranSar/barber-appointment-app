package com.tigran.springcourse.validator;

import com.tigran.springcourse.models.Appointment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AppointmentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Appointment.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Appointment appointment = (Appointment)target;
        String fullName = appointment.getFullName();
        String phoneNumber = appointment.getPhoneNumber();
        errors.rejectValue("fullName","NotEmpty.appointment.fullName","Full name cannot be empty");
        if (fullName != null) {
            if (appointment.getFullName().matches("^[A-Za-zА-Яа-яЁё\\s]+$")) {
                errors.rejectValue("fullName", "Invalid.fullName", "Full name cannot contain digits");
            }
            if (fullName.length() < 2 || fullName.length() > 80){
                errors.rejectValue("fullName","Invalid.size.fullName", "Full name size should be between 2 and 8");
            }
        }
        if (phoneNumber != null){
            if (!phoneNumber.matches("^\\+?\\d+$") || phoneNumber.length() < 5 || phoneNumber.length() > 30){
                errors.rejectValue("phoneNumber","Invalid.phoneNumber","Invalid phone number");
            }
        }
    }
}
