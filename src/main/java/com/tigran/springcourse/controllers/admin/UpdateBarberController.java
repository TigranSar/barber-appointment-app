package com.tigran.springcourse.controllers.admin;

import com.tigran.springcourse.dao.BarberDAO;
import com.tigran.springcourse.models.Barber;
import com.tigran.springcourse.service.FileService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class UpdateBarberController {
    private BarberDAO barberDAO;
    private FileService fileService;

    public UpdateBarberController(BarberDAO barberDAO, FileService fileService) {
        this.barberDAO = barberDAO;
        this.fileService = fileService;
    }

    @GetMapping("update_barber_page/{id}")
    public String updateBarberPage(@PathVariable("id") int id, Model model, HttpSession httpSession){
        String session = (String)httpSession.getAttribute("admin");
        if (session != null) {
            model.addAttribute("barber", barberDAO.getBarberById(id));
            return "admin/updateBarber";
        }
        return "redirect:/admin/login_page";
    }

    @PostMapping("/delete_barber_photo/{id}")
    public String deleteBarberPhoto(@PathVariable("id") int id, HttpSession httpSession){
        String session = (String)httpSession.getAttribute("admin");
        if (session != null) {
            Barber barber = barberDAO.getBarberById(id);
            if (!barber.getPhotopath().equals("/images/noPhoto.png")) {
                String uploadDir = "C:/Users/benja/IdeaProjects/SpringLesson";
                File file = new File(uploadDir + barber.getPhotopath());
                file.delete();
                barber.setPhotopath("/images/noPhoto.png");
                barberDAO.updateBarber(barber);
            }
            return "redirect:/admin/update_barber_page/" + id;
        }
        return "redirect:/admin/login_page";
    }
    @PostMapping("update_barber")
    public String updateBarber(@ModelAttribute("barber") Barber barber,
                               @RequestParam(value = "imageFile", required = false) MultipartFile file,
                               HttpSession httpSession) throws IOException {
        String session = (String)httpSession.getAttribute("admin");
        if (session != null){
            String uploadedPath = fileService.updateImage(file,barber.getPhotopath());
            if (uploadedPath != null){
                barber.setPhotopath(uploadedPath);
            }
            barberDAO.updateBarber(barber);
            return "redirect:/admin/barberList";
        }
        return "redirect:/admin/login_page";
    }
}
