package com.safarr.app.controller;

import com.safarr.app.entity.Image;
import com.safarr.app.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/locations/{locationId}/images")
    public ResponseEntity<Image> uploadImage(@PathVariable Long locationId, @RequestParam("file") MultipartFile file) throws IOException {
        // Save the file to the server
        Path path = Paths.get("uploads/" + file.getOriginalFilename());
        Files.write(path, file.getBytes());

        // Save the image record to the database
        Image image = new Image();
        image.setUrl(path.toString());
        // Set the locationId appropriately
        // image.setLocation(locationService.findById(locationId)); // Implement this method in LocationService
        return ResponseEntity.ok(imageService.saveImage(image));
    }
}