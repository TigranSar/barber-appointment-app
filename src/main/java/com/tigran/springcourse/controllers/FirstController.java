package com.tigran.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
public class FirstController {
    @GetMapping("")
    public String index(){
        return "index";
    }

    @PostMapping("/upload")
    public String showImage(@RequestParam(value = "file", required = false)
                                MultipartFile file,
                            Model model) throws IOException{
//        String uploadDir = "C:/Users/benja/IdeaProjects/SpringLesson/uploads";
//        String photoPath = "";
//        if(file != null) {
//            File uploadPath = new File(uploadDir);
//            String fileName = file.getOriginalFilename();
//            String fileType = fileName.substring(fileName.lastIndexOf("."));
//            String savedFileName = "photo_" + UUID.randomUUID() + fileType;
//            Path path = Paths.get(uploadDir, savedFileName);
//            photoPath = "uploads/" + savedFileName;
//            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//        }
//        model.addAttribute("photoPath", photoPath);
//        System.out.println("photopath :"+photoPath);
        return "imagePage";
    }
}
