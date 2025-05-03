package com.tigran.springcourse.controllers.admin;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AppointmentsPageController {
    @GetMapping("/appointments")
    public String mainPage(HttpSession httpSession){
        String session = (String)httpSession.getAttribute("admin");
        if (session != null){
            httpSession.setMaxInactiveInterval(15*60);
            return "appointmentsListPage";
        }
        return "redirect:/admin/login_page";
    }
}
