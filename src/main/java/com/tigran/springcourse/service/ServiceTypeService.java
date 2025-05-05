package com.tigran.springcourse.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceTypeService {
    public String DetermineServiceType(boolean isHaircut, boolean isShaving){
        if (isHaircut && isShaving){
            return "Haircut and Shaving";
        }else if(isHaircut){
            return "Haircut";
        }
        return "Shaving";
    }
}
