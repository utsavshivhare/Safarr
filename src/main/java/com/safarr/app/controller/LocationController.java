package com.safarr.app.controller;

import com.safarr.app.entity.Location;
import com.safarr.app.repository.LocationRepository;
import com.safarr.app.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationService locationService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/location")
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        return ResponseEntity.ok(locationService.saveLocation(location));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/location/{mapId}")
    public ResponseEntity<List<Location>> getLocationsByMapId(@PathVariable Long mapId) {
        return ResponseEntity.ok(locationRepository.findAllByMapId(mapId));
    }
}


