package com.tigran.springcourse.controllers;

import com.tigran.springcourse.DAO.BarberDAO;
import com.tigran.springcourse.models.Barber;
import com.tigran.springcourse.validator.BarberValidator;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
public class AdminController {
    private BarberDAO barberDAO;
    private BarberValidator barberValidator;

    public AdminController(BarberValidator barberValidator, BarberDAO barberDAO) {
        this.barberValidator = barberValidator;
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

    @GetMapping("/login_page")
    public String loginPage(){
        return "loginPage";
    }

    @PostMapping("/login")
    public String login(@RequestParam("login") String login,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession httpSession){
        boolean hasErrors = false;
        if (!login.equals("admin")){
            hasErrors = true;
            model.addAttribute("wrong_login","Wrong login");
        }
        if (!password.equals("admin")){
            hasErrors = true;
            model.addAttribute("wrong_password","Wrong password");
        }
        if (hasErrors){
            model.addAttribute("hasErrors",hasErrors);
            return "loginPage";
        }
        httpSession.setAttribute("admin","admin12345");
        return "redirect:/admin/admin";
    }

    @PostMapping("/add_barber")
    public String addBarber(@ModelAttribute("barber") Barber barber,
                            @RequestParam(value = "photo", required = false) MultipartFile file,
                            RedirectAttributes redirectAttributes,
                            HttpSession httpSession) throws IOException {
        String session = (String)httpSession.getAttribute("admin");
        if (session != null) {
            String uploadDir = "C:/Users/benja/IdeaProjects/SpringLesson/uploads";
            String photoPath = "/uploads/nophoto.png";
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

    @GetMapping("/admin")
    public String adminPage(HttpSession httpSession){
        String session = (String)httpSession.getAttribute("admin");
        if (session != null){
            httpSession.setMaxInactiveInterval(15*60);
            return "adminPage";
        }
        return "redirect:/admin/login_page";
    }
    @GetMapping("/logout")
    public String logOut(HttpSession httpSession){
        String session = (String)httpSession.getAttribute("admin");
        if (session != null){
            httpSession.invalidate();
            return "redirect:/admin/login_page";
        }
        return "redirect:/";
    }
    @GetMapping("/barberList")
    public String barberList(Model model, HttpSession httpSession){
        String session = (String)httpSession.getAttribute("admin");
        model.addAttribute("barberList",barberDAO.getAllBarbers());
        boolean hasSession = session != null;
        model.addAttribute("hasSession",hasSession);
        return "barberList";
    }
}
