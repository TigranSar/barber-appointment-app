package com.tigran.springcourse.validator;

import com.tigran.springcourse.models.Barber;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BarberValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Barber.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Barber barber = (Barber)target;
        String fullName= barber.getFullName();
        if (fullName != null){
            if (!barber.getFullName().matches("^[A-Za-zА-Яа-яЁё\\s]+$")){
            errors.rejectValue("fullName","","Full name cannot contain digits or symbols");
            }
        }
    }

}
