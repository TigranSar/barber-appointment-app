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
        String fullName= barber.getFullName();
        int hairCutPrice = barber.getHairCutPrice();
        int shavingPrice = barber.getShavingPrice();
        ValidationUtils.rejectIfEmpty(errors,
                "fullName","NotEmpty.barber.fullName","Full name cannot be empty");
        if (fullName != null){
            if (!barber.getFullName().matches("^[A-Za-zА-Яа-яЁё\\s]+$")){
            errors.rejectValue("fullName","Invalid.fullName","Full name cannot contain digits");
            }
            if (fullName.length() < 2 || fullName.length() > 80){
                errors.rejectValue("fullName","Invalid.size.fullName","Full name length should be between 2 and 80");
            }
        }
        if (hairCutPrice > 100 || hairCutPrice < 1){
//            errors.rejectValue("hair");;
        }
    }
}
