package com.tigran.springcourse.validator;

import com.tigran.springcourse.models.Appointment;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AppointmentValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Appointment.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Appointment appointment = (Appointment)target;
        errors.rejectValue("fullName","NotEmpty.appointment.fullName","Full name cannot be empty");
        if (appointment.getFullName().matches("^[A-Za-zА-Яа-яЁё\\s]+$")){
            errors.rejectValue("fullName","Invalid.fullName","Full name cannot contain digits");
        }

    }
}
