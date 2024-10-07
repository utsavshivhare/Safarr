package com.safarr.app.controller;

import com.safarr.app.model.Image;
import com.safarr.app.model.Location;
import com.safarr.app.repository.ImageRepository;
import com.safarr.app.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ImageRepository imageRepository;

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        return ResponseEntity.ok(locationRepository.save(location));
    }

    @PostMapping("/{locationId}/images")
    public ResponseEntity<Image> addImage(@PathVariable Long locationId, @RequestParam("file") MultipartFile file) {
        try {
            Image image = new Image();
            image.setLocation(locationRepository.findById(locationId).orElseThrow());
            image.setImageData(file.getBytes());  // Store the image data as a byte array
            return ResponseEntity.ok(imageRepository.save(image));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{mapId}")
    public ResponseEntity<List<Location>> getLocationsByMapId(@PathVariable Long mapId) {
        return ResponseEntity.ok(locationRepository.findAllByMapId(mapId));
    }
}


