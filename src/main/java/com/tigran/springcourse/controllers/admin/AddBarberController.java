package com.tigran.springcourse.controllers.admin;

import com.tigran.springcourse.DAO.BarberDAO;
import com.tigran.springcourse.models.Barber;
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

    public AddBarberController(BarberDAO barberDAO) {
        this.barberDAO = barberDAO;
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
            String uploadDir = "C:/Users/benja/IdeaProjects/SpringLesson/uploads";
            String photoPath = "/images/noPhoto.png";
            if (file != null && !file.isEmpty()) {
                File uploadPath = new File(uploadDir);
                String fileName = file.getOriginalFilename();
                int dotIndex = fileName.lastIndexOf(".");
                if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
                    String fileType = fileName.substring(dotIndex);
                    String savedFileName = "photo_" + UUID.randomUUID() + fileType;
                    Path path = Paths.get(uploadDir, savedFileName);
                    photoPath = "/uploads/" + savedFileName;
                    barber.setPhotopath(photoPath);
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                } else {
                    barber.setPhotopath(photoPath);
                }
            } else {
                barber.setPhotopath(photoPath);
            }
            barberDAO.addBarber(barber);
            return "redirect:barberList";
        }
        return "redirect:login_page";
    }
}
