package com.safarr.app.controller;

import com.safarr.app.entity.Map; // Ensure you import your custom Map class
import com.safarr.app.repository.MapRepository;
import com.safarr.app.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MapController {

    @Autowired
    private MapRepository mapRepository;

    @Autowired
    private MapService mapService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/maps")
    public ResponseEntity<Map> createMap(@RequestBody Map map) {
        return ResponseEntity.ok(mapService.saveMap(map));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{userId}")
    public ResponseEntity<List<Map>> getMapsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(mapRepository.findAllByUserId(userId));
    }
}
