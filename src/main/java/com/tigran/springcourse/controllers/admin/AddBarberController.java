package com.tigran.springcourse.controllers.admin;

import com.tigran.springcourse.DAO.BarberDAO;
import com.tigran.springcourse.models.Barber;
import com.tigran.springcourse.service.FileService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AddBarberController {
    private BarberDAO barberDAO;
    private FileService fileService;

    public AddBarberController(BarberDAO barberDAO, FileService fileService) {
        this.barberDAO = barberDAO;
        this.fileService = fileService;
    }

    @GetMapping("/add_barber_page")
    public String addBarberPage(HttpSession httpSession, Model model){
        String session = (String)httpSession.getAttribute("admin");
        if (session == null){
            return "redirect:/admin/login_page";
        }
        model.addAttribute("barber",new Barber());
        return "addBarberPage";
    }

    @PostMapping("/add_barber")
    public String addBarber(@ModelAttribute("barber") Barber barber,
                            @RequestParam(value = "photo", required = false) MultipartFile file,
                            RedirectAttributes redirectAttributes,
                            HttpSession httpSession) throws IOException {
        String session = (String)httpSession.getAttribute("admin");
        if (session != null) {
            String photoPath = fileService.saveImage(file);
            if (photoPath != null){
                barber.setPhotopath(photoPath);
            }else {
                barber.setPhotopath("/images/noPhoto.png");
            }
            barberDAO.addBarber(barber);
            return "redirect:barberList";
        }
        return "redirect:login_page";
    }
}
