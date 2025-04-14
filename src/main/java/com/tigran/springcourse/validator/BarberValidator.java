package com.tigran.springcourse.validator;

import com.tigran.springcourse.models.Barber;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class BarberValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Barber.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Barber barber = (Barber)target;
        ValidationUtils.rejectIfEmpty(errors,
                "fullName","NotEmpty.barber.fullName","Full name cannot be empty");
        if (!barber.getFullName().matches("^[A-Za-zА-Яа-яЁё\\s]+$")){
            errors.rejectValue("fullName","Invalid.fullName","Full name cannot contain digits");
        }
    }
}
