package com.tigran.springcourse.controllers.client;

import com.tigran.springcourse.dao.BarberDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("client")
public class ChooseBarberController {
    private BarberDAO barberDAO;

    public ChooseBarberController(BarberDAO barberDAO) {
        this.barberDAO = barberDAO;
    }

    @GetMapping("/choose_barber")
    public String chooseBarber(Model model){
        model.addAttribute("barberList",barberDAO.getAllBarbers());
        return "client/chooseBarberList";
    }
}
