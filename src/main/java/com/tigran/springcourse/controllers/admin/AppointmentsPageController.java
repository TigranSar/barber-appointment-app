package com.tigran.springcourse.controllers.admin;

import com.tigran.springcourse.dao.AppointmentDAO;
import com.tigran.springcourse.dao.BarberDAO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AppointmentsPageController {
    private AppointmentDAO appointmentDAO;
    private BarberDAO barberDAO;
    @Autowired
    public AppointmentsPageController(AppointmentDAO appointmentDAO, BarberDAO barberDAO) {
        this.appointmentDAO = appointmentDAO;
        this.barberDAO = barberDAO;
    }

    @GetMapping("/appointments")
    public String mainPage(HttpSession httpSession, Model model){
        String session = (String)httpSession.getAttribute("admin");
        if (session != null){
            model.addAttribute("appointmentInfo",appointmentDAO.getAllAppointmentInfo());
            httpSession.setMaxInactiveInterval(15*60);
            return "appointmentsListPage";
        }
        return "redirect:/admin/login_page";
    }
}
