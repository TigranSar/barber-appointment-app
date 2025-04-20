package com.tigran.springcourse.controllers.admin;

import com.tigran.springcourse.DAO.BarberDAO;
import com.tigran.springcourse.models.Barber;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class UpdateBarberController {
    private BarberDAO barberDAO;

    public UpdateBarberController(BarberDAO barberDAO) {
        this.barberDAO = barberDAO;
    }

    @GetMapping("update_barber_page/{id}")
    public String updateBarberPage(@PathVariable("id") int id, Model model, HttpSession httpSession){
        String session = (String)httpSession.getAttribute("admin");
        if (session != null) {
            model.addAttribute("barber", barberDAO.getBarberById(id));
            return "updateBarber";
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
            if (file != null && !file.isEmpty()) {
                if (!barber.getPhotopath().equals("/images/noPhoto.png")){
                    new File("C:/Users/benja/IdeaProjects/SpringLesson"+barber.getPhotopath()).delete();
                }
                String uploadDir = "C:/Users/benja/IdeaProjects/SpringLesson/uploads";
                File uploadPath = new File(uploadDir);
                String fileName = file.getOriginalFilename();
                int dotIndex = fileName.lastIndexOf(".");
                if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
                    String fileType = fileName.substring(dotIndex);
                    String savedFileName = "photo_" + UUID.randomUUID() + fileType;
                    Path path = Paths.get(uploadDir, savedFileName);
                    barber.setPhotopath("/uploads/" + savedFileName);
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                }
            }
            barberDAO.updateBarber(barber);
            return "redirect:/admin/barberList";
        }
        return "redirect:/admin/login_page";
    }
}
