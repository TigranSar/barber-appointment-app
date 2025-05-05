package com.tigran.springcourse.controllers.admin;

import com.tigran.springcourse.dao.BarberDAO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

@Controller
@RequestMapping("/admin")
public class DeleteBarberController {
    private BarberDAO barberDAO;

    public DeleteBarberController(BarberDAO barberDAO) {
        this.barberDAO = barberDAO;
    }

    @DeleteMapping("/delete_barber/{id}")
    public String deleteBarber(@PathVariable("id") int id, HttpSession httpSession){
        String session = (String)httpSession.getAttribute("admin");
        if (session != null) {
            new File("C:/Users/benja/IdeaProjects/SpringLesson"+barberDAO.getBarberById(id).getPhotopath()).delete();
            barberDAO.deleteBarber(id);
            return "redirect:/admin/barberList";
        }
        return "redirect:/admin/login_page";
    }
}
