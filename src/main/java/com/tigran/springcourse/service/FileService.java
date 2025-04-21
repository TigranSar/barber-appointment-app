package com.tigran.springcourse.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileService {
    private final String uploadDir = "C:/Users/benja/IdeaProjects/SpringLesson/uploads";
    private final String defaultPhoto = "/image/noPhoto.png";
    public String saveImage(MultipartFile file)throws IOException {
        if (file != null && !file.isEmpty()){
            File uploadPath = new File(uploadDir);
            String fileName = file.getOriginalFilename();
            int dotIndex = fileName.lastIndexOf(".");
            if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
                String fileType = fileName.substring(dotIndex);
                String savedFileName = "photo_" + UUID.randomUUID() + fileType;
                Path path = Paths.get(uploadDir, savedFileName);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                return "/uploads/" + savedFileName;
            }
        }
        return null;
    }
    public String updateImage(MultipartFile file, String currentPhotoPath) throws IOException{
        if (file != null && !file.isEmpty()){
            if (!currentPhotoPath.equals("/images/noPhoto.png")){
                new File("C:/Users/benja/IdeaProjects/SpringLesson"+currentPhotoPath).delete();
            }
            File uploadPath = new File(uploadDir);
            String fileName = file.getOriginalFilename();
            int dotIndex = fileName.lastIndexOf(".");
            if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
                String fileType = fileName.substring(dotIndex);
                String savedFileName = "photo_" + UUID.randomUUID() + fileType;
                Path path = Paths.get(uploadDir, savedFileName);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                return "/uploads/" + savedFileName;
            }
        }
        return null;
    }
}
