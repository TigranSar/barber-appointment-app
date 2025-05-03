package com.tigran.springcourse.service;

import org.springframework.stereotype.Service;

@Service
public class TimeManagementService {
    public int serviceAmountMinutes(boolean hairCut, boolean shaving){
        if (hairCut && shaving){
            return 45;
        }else if (shaving){
            return 20;
        }
        return 30;
    }

}
