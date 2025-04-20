package com.tigran.springcourse.controllers.admin;

import com.tigran.springcourse.DAO.BarberDAO;
import com.tigran.springcourse.models.Barber;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BarberListController {
    private BarberDAO barberDAO;

    public BarberListController(BarberDAO barberDAO) {
        this.barberDAO = barberDAO;
    }

    @GetMapping("/barberList")
    public String barberList(Model model, HttpSession httpSession){
        String session = (String)httpSession.getAttribute("admin");
        model.addAttribute("barberList",barberDAO.getAllBarbers());
        if (session == null){
            return "redirect:login_page";
        }
        return "barberList";
    }
}
